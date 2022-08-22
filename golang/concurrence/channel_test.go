package concurrence

import (
	"fmt"
	"testing"
	"time"
)

func TestChannel(t *testing.T) {
	fmt.Println("ch1...")
	ch1()
	fmt.Println("ch2...")
	ch2()
}

func ch1() {
	ch := make(chan int)
	// 默认channel是同步且无缓冲的，发送和接收端必须都准备好才能发收
	// 比如你主的地方没有快递柜（缓冲区），快递员（发送端）把东西直接送到你手上（接收端）
	// 不用协程直接执行的话会报错：fatal error: all goroutines are asleep - deadlock!
	go func() {
		ch <- 10
		close(ch)
		//ch <- 20 //给已关闭通道再发送值会panic
	}()
	go func() {
		fmt.Println(<-ch)
	}()
	time.Sleep(time.Second)
}

func ch2() {
	// make第二个参数，设置缓存区
	ch := make(chan int, 2)
	ch <- 10
	ch <- 20
	//ch <- 30 //缓冲区超出大小同样会造成死锁

	for val := range ch {
		fmt.Println(val)
	}
}

/*-------------------------------------------------*/
//判断通道关闭

func TestChannelQuit(t *testing.T) {
	ch1 := make(chan int)
	ch2 := make(chan int)

	go func() {
		for i := 0; i < 5; i++ {
			ch1 <- i
		}
		close(ch1)
	}()
	go func() {
		for true {
			j, ok := <-ch1
			if !ok {
				break
			}
			ch2 <- j + 1
		}
		close(ch2)
	}()
	//time.Sleep(time.Second)

	//for i := 0; i < 1; i++ {
	//	fmt.Println("===>", <-ch2)
	//}

	for val := range ch2 {
		fmt.Println(val)
	}
}

/*-------------------------------------------------*/
//for和range取值区别

func TestForRange(t *testing.T) {
	ch := make(chan int)
	go func() {
		for i := 0; i < 3; i++ {
			ch <- i
		}
		close(ch)
	}()

	// 关闭channel之后再从channel获取值都是0，如果注释掉close(ch)则是报deadlock错误
	//for i := 0; i < 5; i++ {
	//	fmt.Println(<-ch)
	//}

	// 对于range没下标控制，输出值直到channel关闭就自然退出，如果没有close(ch)，继续取值，就会报deadlock错误
	for i := range ch {
		fmt.Println(i)
	}
}

/*-------------------------------------------------*/
//单向通道
//chan<- 只能写入（发送）数据的通道
//<-chan 只能读取（接收）数据的通道

func TestUnidirectional(t *testing.T) {
	ch1 := make(chan int)
	ch2 := make(chan int)

	//数据写入成ch1
	go channelIn(ch1)
	//读取ch1，写入ch2
	go channelOut(ch1, ch2)

	for i := range ch2 {
		fmt.Println(i)
	}
}

func channelIn(out chan<- int) {
	//fmt.Println(<-out) //编译错误，只能写入不能读取
	for i := 0; i < 3; i++ {
		out <- i
	}
	close(out)
}

func channelOut(in <-chan int, out chan<- int) {
	for i := range in {
		out <- i * i
	}
	close(out)
}

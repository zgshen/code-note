package concurrence

import (
	"fmt"
	"testing"
	"time"
)

func TestMulti(t *testing.T) {
	ch1 := make(chan int)
	ch2 := make(chan int)
	go func() {
		for i := 0; i < 3; i++ {
			ch1 <- i
		}
		close(ch1)
	}()
	go func() {
		for i := 0; i < 2; i++ {
			ch2 <- i
		}
		close(ch2)
	}()
	//接收多个通道数据，知道所有通道都关闭再退出
	for true {
		c1, k1 := <-ch1
		c2, k2 := <-ch2

		fmt.Println(c1, c2)
		if !k1 && !k2 {
			break
		}
	}
	fmt.Println("complete...")
}

//多路复用
func TestSelect(t *testing.T) {
	ch1 := make(chan int)
	ch2 := make(chan int)

	//如果有一个或多个IO操作可以完成，则Go运行时系统会随机的选择一个执行，否则的话，如果有default分支，则执行default分支语句，
	//如果连default都没有，则select语句会一直阻塞，直到至少有一个IO操作可以进行
	//	for i := 0; i < 5; i++ {
	go func() {
		for true {
			select {
			case n := <-ch1:
				fmt.Println("read ch1:", n) // 如果读到数据，则执行此case语句
			case ch2 <- 10:
				fmt.Println("write ch2.") // 如果成功写入数据，则执行此case语句
			default:
				// 如果以上判断都没成功，进入default流程
				fmt.Println("default.")
			}
			time.Sleep(time.Millisecond * 500)
		}
	}()

	go func() {
		for i := 0; i < 3; i++ {
			ch1 <- i
		}
		close(ch1)
	}()

	for n := range ch2 {
		fmt.Println("ch2 val:", n)
	}
}

//判断通道是否满了
func TestFull(t *testing.T) {
	// 创建管道，缓冲区5
	output1 := make(chan string, 5)
	// 子协程写数据
	go write(output1)
	// 取数据
	i := 0
	for s := range output1 {
		i++
		fmt.Println("read val:", s, i)
		time.Sleep(time.Second)
	}
}

func write(ch chan string) {
	for {
		select {
		// 写数据
		case ch <- "hello":
			fmt.Println("write hello")
		default:
			fmt.Println("channel has full")
		}
		time.Sleep(time.Millisecond * 500)
	}
}

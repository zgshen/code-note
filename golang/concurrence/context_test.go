package concurrence

import (
	"context"
	"fmt"
	"testing"
	"time"
)

//WithTimeout
func TestContextTimeout(t *testing.T) {
	ctx, cancel := context.WithTimeout(context.Background(), 3*time.Second)
	wg.Add(1)
	defer func() {
		cancel()
		fmt.Println("cancel.")
	}()
	go watch(ctx, "first")
	go watch(ctx, "second")
	wg.Wait()
}

func watch(ctx context.Context, str string) {
	for {
		select {
		case <-ctx.Done():
			wg.Done()
			fmt.Println("quit, monitor:", str)
			return
		default:
			time.Sleep(1 * time.Second)
			fmt.Println(str, "monitoring...")
		}
	}
}

//WithTimeout simulation
func TestAsyncCall(t *testing.T) {
	//超时800ms
	ctx, cancel := context.WithTimeout(context.Background(), time.Millisecond*800)
	defer func() {
		cancel()
		fmt.Println("finally cancel!")
	}()
	go func(ctx context.Context) {
		// 模拟发送HTTP请求，300ms
		time.Sleep(time.Millisecond * 300)
		//time.Sleep(time.Millisecond * 600) //timeout!!!
		//业务执行完cancel，不cancel就要等800ms在defer cancel
		//cancel()
		fmt.Println("biz cancel!")
	}(ctx)

	select {
	case <-ctx.Done():
		fmt.Println("call successfully!!!")
		return
	case <-time.After(time.Duration(time.Millisecond * 500)):
		//有时context ctx是通过参数传进来的，不知道是800ms，就需要设置time.After()作为本函数超时时间
		//比如这里的500ms，模拟http请求超过500ms就会打印下面timeout
		//这里改成900ms，比800ms还大，那就ctx.done()结束，这里timeout永远进不来
		fmt.Println("timeout!!!")
		return
	}
}

//WithCancel
func TestContextCancel(t *testing.T) {
	ctx, cancel := context.WithCancel(context.Background())
	wg.Add(1)
	go watchCancel(ctx, "first")
	//	go watchCancel(ctx, "second")

	time.Sleep(3 * time.Second)
	cancel()
	wg.Wait()
}

func watchCancel(ctx context.Context, str string) {
	for {
		select {
		case <-ctx.Done():
			wg.Done()
			fmt.Println("quit, monitor:", str)
			return
		case <-time.After(5 * time.Second): //cancel()关闭通道，所以延时不起作用，直接退出。并发程序中time.After()更常用
			//default:
			//time.Sleep(5 * time.Second) //如果用sleep的话，cancel()之后还要等5秒延时结束
			fmt.Println(str, "monitoring...")
		}
	}
}

//WithDeadline
func TestContextDeadline(t *testing.T) {
	ctx, cancel := context.WithDeadline(context.Background(), time.Now().Add(time.Second*3))
	now := time.Now()
	go deadWatch(ctx, "monitor1", now)
	time.Sleep(time.Second * 7)
	cancel()
}

func deadWatch(ctx context.Context, name string, cur time.Time) {
	//	time.Sleep(time.Millisecond * 50)
	for {
		select {
		case <-ctx.Done():
			fmt.Println(name, "收到信号，监控退出，耗时：", time.Since(cur))
			return //这里多加cancel()就不用等7秒了
		default:
			fmt.Println(name, "持续监控，耗时：", time.Since(cur))
			time.Sleep(1 * time.Second)
		}
	}
}

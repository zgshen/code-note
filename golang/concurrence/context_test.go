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

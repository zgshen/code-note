package coroutine

import (
	"fmt"
	"testing"
	"time"
)

/**
Go协程，用户态线程，是Go天然支持高并发的原因。
相比于Java，协程的代码写起来简洁了非常多。
*/
func TestGoroutine(t *testing.T) {
	go task1()
	// 可以调用函数，也可以直接用匿名函数
	go func() {
		time.Sleep(50 * time.Millisecond)
		fmt.Println("task2 start.")
		fmt.Println("task2 complete.")
	}()
	time.Sleep(3 * time.Second)
}

func task1() {
	fmt.Println("task1 start.")
	// 堵塞模拟
	time.Sleep(2 * time.Second)
	fmt.Println("task1 complete.")
}

package coroutine

import (
	"fmt"
	"sync"
	"sync/atomic"
	"testing"
	"time"
)

// sync_test.go duplicate
// var wg sync.WaitGroup

// 普通版
func TestAddCommon(t *testing.T) {
	n := 0
	wg.Add(100000)
	for i := 0; i < 100000; i++ {
		go func() {
			defer wg.Done()
			n++
		}()
	}
	wg.Wait()
	fmt.Println(n) //一般最后n的值是小于100000的
}

// 使用互斥锁
func TestAddLock(t *testing.T) {
	start := time.Now()
	var m sync.Mutex
	n := 0
	wg.Add(100000)
	for i := 0; i < 100000; i++ {
		go func() {
			defer wg.Done()
			m.Lock()
			n++
			m.Unlock()
		}()
	}
	wg.Wait()
	fmt.Println("value:", n, "cost time:", time.Since(start))
}

// 原子操作
func TestAddAtomic(t *testing.T) {
	var n int64 = 0
	wg.Add(100000)
	for i := 0; i < 100000; i++ {
		go func() {
			defer wg.Done()
			atomic.AddInt64(&n, 1)
		}()
	}
	wg.Wait()
	fmt.Println(n)
}

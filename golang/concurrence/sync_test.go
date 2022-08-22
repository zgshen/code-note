package concurrence

import (
	"fmt"
	"strconv"
	"sync"
	"testing"
	"time"
)

/*
goroutine_test.go中用sleep只是临时看下效果,
实际还得用sync包的sync.WaitGroup来控制并发任务,
sync.WaitGroup的用途和Java中的CountDownLatch
*/

var wg sync.WaitGroup

func TestConcurrence(t *testing.T) {
	for i := 1; i <= 3; i++ {
		wg.Add(1) //计数器加1
		go syncTask(fmt.Sprintf("%s%d", "task", i))
	}
	wg.Wait() //阻塞直到计数器变为0
	fmt.Println("all task done.")
}

func syncTask[T any](t T) {
	defer wg.Done() //计数器减1
	fmt.Println(t, " run...")
}

/*-------------------------------------------------*/
// 并发锁

type user struct {
	name string
	age  int
	mtx  sync.Mutex // 互斥锁
}

func (u *user) update(name string) {
	defer wg.Done()
	u.mtx.Lock() //后面的协程要等待已获得锁的协程解锁后才能竞争锁
	fmt.Println("acquire lock, modify name:", name)
	time.Sleep(2 * time.Second)
	u.name = name
	u.mtx.Unlock()
	fmt.Println("unlock, name:", name)
}

func TestUserUpdate(t *testing.T) {
	u := &user{name: "a", age: 18}
	wg.Add(2)
	go func() {
		u.update("b")
	}()
	go func() {
		u.update("c")
	}()
	wg.Wait()
	fmt.Println(u)

	fmt.Printf("user=%v\n", u)  //%v只输出所有的值
	fmt.Printf("user=%+v\n", u) //%+v 先输出字段名字，再输出该字段的值
	fmt.Printf("user=%#v\n", u) //%#v 先输出结构体名字值，再输出结构体（字段名字+字段的值）
}

/*-------------------------------------------------*/
//sync.Map并发安全的map

//高并发下读写map会出现错误 fatal error: concurrent map read and map write
func TestCommonMap(t *testing.T) {
	m := make(map[string]int)
	go func() {
		for i := 0; i < 10000; i++ {
			m[strconv.Itoa(i)] = i
		}
	}()
	go func() {
		for {
			_ = m["1"]
		}
	}()
	for {
	}
}

func TestConcurrenceMap(t *testing.T) {
	var m sync.Map
	for i := 0; i < 10; i++ {
		//存储
		m.Store(strconv.Itoa(i), i+100)
	}

	//获取
	fmt.Println(m.Load("9"))

	//删除
	m.Delete("9")
	fmt.Println(m.Load("9"))

	//遍历
	m.Range(func(key, value any) bool {
		fmt.Println("key value:", key, value)
		return true
	})
}

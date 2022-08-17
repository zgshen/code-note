package makenew

import (
	"fmt"
	"testing"
	"time"
)

func TestDefaultValue(t *testing.T) {
	// 变量如果没有赋值，默认值就是零值，int是0，string是空串
	var (
		i int
		s string
	)
	fmt.Println("i,s default value:", i, s)

	// 对于指针类型，指向的内存必须已创建，不然就会发生panic
	var ref *int
	//*ref = 100 // 直接赋值会panic
	j := 100
	ref = &j
	fmt.Println("j,*ref value", j, *ref)

	*ref = 200 // 已经指向存在的内存地址所以能修改值了
	fmt.Println("j,*ref value", j, *ref)

	var k *int
	k = new(int) // 也可以用new初始化内存，防止panic，返回的是指针类型
	*k = 50
	fmt.Println(*k)
}

func TestMakeNew(t *testing.T) {

	// new 用于类型的内存分配，并且内存置为零
	i := new(int)
	fmt.Println(*i)

	// make 只用于 slice、map 以及 channel 的初始化（非零值）
	// slice
	ints := make([]int, 3)
	fmt.Println(ints)
	ints[2] = 10
	fmt.Println(ints)

	// map
	m := make(map[string]string)
	m["a"] = "apple"
	m["b"] = "banana"
	m["o"] = "orange"
	fmt.Println(m)

	// channel
	c := make(chan int)
	// send goroutine
	go func() {
		c <- 111
		c <- 222
		c <- 333
	}()
	// receive routine
	go func() {
		for i2 := range c {
			fmt.Println(i2)
		}
	}()
	// sleep for receive routine output
	time.Sleep(3 * time.Second)
}

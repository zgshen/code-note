package slice

import (
	"bytes"
	"fmt"
	"testing"
)

/**
 * 动态数组的使用
 */

func TestSlice(t *testing.T) {
	s := make([]int, 5)
	s[0] = 10
	s[1] = 11
	fmt.Println(s)

	// 切片赋给as，s和as空间还是共享的
	as := s[1:3]
	// append添加元素，bs重新分配内存，和s不共享内存
	bs := append(s, 13)
	// as先切片但输出下标2会变成12，证明共享
	s[2] = 12
	fmt.Printf("%v, %v, %v \n", s, as, bs)
}

func TestSlice2(t *testing.T) {
	str := []byte("12345&abcde")
	// 要套一个string函数才能输出字符
	fmt.Println(string(str))

	i := bytes.IndexByte(str, '&')
	strA := str[:i]
	strB := str[i+1:]
	//如下使用Full Slice Expression（三参切片，cap不够强制生成
	//一个新的数组）就不会扩展到strB空间了，而是会重新分配内存
	//strB := str[:i:i]
	fmt.Printf("a:%s, b:%s \n", strA, strB)

	strAA := append(strA, "ZZZ"...)
	fmt.Println(string(strAA))
	//str，strA和strB共享内存，对strA做append会扩展到strB空间
	fmt.Printf("str:%s, a:%s, b:%s \n", str, strA, strB)
}

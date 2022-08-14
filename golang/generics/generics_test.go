package generics

import (
	"fmt"
	"testing"
)

// 泛型定义写在方法后面，用中括号
func printlnValue[T any](t T) {
	fmt.Println(t)
}

func TestRun(t *testing.T) {
	printlnValue(123)
	printlnValue("hello")
	printlnValue(20.22)
	printlnValue(true)
}

/*-------------------------------------------------*/
// 泛型应用，带逻辑判断的求和
// 有点像Java中的stream处理

var users []user

func init() {
	tom := user{"Tom", 12, 150.0}
	jane := user{"Jane", 16, 160.0}
	nathan := user{"Nathan", 10, 120.5}
	jack := user{"Jack", 15, 155.5}

	users = []user{tom, jane, nathan, jack}
}

func TestSum(t *testing.T) {
	total := sum(users, func(u user) float32 {
		if u.age < 15 {
			return u.height
		}
		return 0
	})
	fmt.Println(total)
}

type user struct {
	name   string
	age    int
	height float32
}

type SumAble interface {
	// 类型约束
	~int | ~int32 | ~int64 | ~float32 | ~float64
}

// 泛型应用，可传入函数的求和。参数分别为数组，逻辑处理函数
func sum[T any, U SumAble](arr []T, f func(T) U) U {
	var sum U
	for _, elem := range arr {
		sum = sum + f(elem)
	}
	return sum
}

/*-------------------------------------------------*/
// 大佬写的Go系列文章 https://coolshell.cn/articles/21615.html

// filter
func gFilter[T any](arr []T, in bool, f func(T) bool) []T {
	result := []T{}
	for _, elem := range arr {
		choose := f(elem)
		if (in && choose) || (!in && !choose) {
			result = append(result, elem)
		}
	}
	return result
}

func gFilterIn[T any](arr []T, f func(T) bool) []T {
	return gFilter(arr, true, f)
}

func gFilterOut[T any](arr []T, f func(T) bool) []T {
	return gFilter(arr, false, f)
}

// map
func gMap[T1 any, T2 any](arr []T1, f func(T1) T2) []T2 {
	result := make([]T2, len(arr))
	for i, elem := range arr {
		result[i] = f(elem)
	}
	return result
}

// reduce
func gReduce[T1 any, T2 any](arr []T1, init T2, f func(T2, T1) T2) T2 {
	result := init
	for _, elem := range arr {
		result = f(result, elem)
	}
	return result
}

func TestOperation(t *testing.T) {
	// 根据年龄过滤
	filter := gFilter(users, true, func(u user) bool {
		return u.age > 15
	})
	fmt.Println(filter)

	filterIn := gFilterOut(users, func(u user) bool {
		return u.age > 15
	})
	fmt.Println(filterIn)

	// 提取名字数组
	username := gMap(users, func(u user) string {
		return u.name
	})
	fmt.Println(username)

	// 身高总和
	reduce := gReduce(users, 0, func(res float32, u user) float32 {
		return res + u.height
	})
	fmt.Println(reduce / float32(len(users)))
}

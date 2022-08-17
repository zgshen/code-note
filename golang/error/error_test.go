package error

import (
	"errors"
	"fmt"
	"testing"
)

/**
 * Go 的异常和错误处理机制
 */

func TestDiv(t *testing.T) {
	i := divide(10, 0) // panic
	fmt.Println(i)
}

func divide(a, b int) int {
	return a / b
}

/*-------------------------------------------------*/
// 使用defer和recover来处理panic
// 正常情况不应该这样做，而是尽量完善避免panic，该panic的情况就panic

func TestDivideErr(t *testing.T) {
	i := divideErr(10, 0)
	fmt.Println("i value:", i)

	fmt.Println("normal process:", divideErr(10, 5))
}

func divideErr(a, b int) (res int) {
	// recover 用于从panic场景恢复
	defer func() {
		if err := recover(); err != nil {
			// 函数体上的返回变量，panic的时候可以在这里设置一个默认返回
			res = -1
			fmt.Println("error message:[", err, "]")
		}
	}()
	res = a / b
	return res
}

/*-------------------------------------------------*/
// 自定义处理错误 errors.New()
// fmt.Errorf() 包装错误信息

func divideZeroErr(a, b int) (int, error) {
	if b == 0 {
		// 返回默认值和错误信息
		//return 0, errors.New("division by zero")

		// 对错误信息做进一步包装
		err := errors.New("division by zero")
		return 0, fmt.Errorf("something failed: %v", err)
	}
	return a / b, nil
}

func TestDivideZeroErr(t *testing.T) {
	val, err := divideZeroErr(10, 0)
	if err != nil {
		fmt.Println(err)
	}
	fmt.Println(val)
}

// 扩展资料推荐 https://coolshell.cn/articles/21140.html

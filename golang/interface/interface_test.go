package _interface

import (
	"fmt"
	"reflect"
	"testing"
)

type animal interface {
	run()
	voice()
}

type dog struct {
	name string
}

func (dog) run() {
	fmt.Println("dog run")
}

// 需要访问结构体内部属性，放一个引用
func (d dog) voice() {
	fmt.Println(d.name, "bark bark...")
}

// 如果一只动物吃饭跑路的样子都像狗，那它就是一条狗
// 不同Java需要显式用implement关键字，Go只要实现了接口的全部方法就是接口的类型

func TestAnimal(t *testing.T) {
	var d animal = dog{"Spike"}
	d.run()
	d.voice()
}

// 由此推断，Go的所有类型都是属于空接口的类型，看以下代码
/*-------------------------------------------------*/

// 空接口能接收任何类型
type empty interface {
}

//func allType(i interface{}) {
func allType(i empty) {
	fmt.Println(i)
}

func TestAllType(t *testing.T) {
	allType(100)
	allType("hello world.")
	allType(true)
}

/*-------------------------------------------------*/
// 如果接受者类型是指针，那使用的时候也必须使用指针类型

type cat struct {
	name string
}

// 接受者类型是指针
func (*cat) run() {
	fmt.Println("cat run")
}
func (c *cat) voice() {
	fmt.Println(c.name, "moe moe...")
}

func TestPoint(t *testing.T) {
	// 接受者类型是指针，所以这里也要用指针类型
	var c animal = &cat{name: "Tom"}
	c.run()
	c.voice()
}

/*-------------------------------------------------*/
// 接口作用，抽象和多态

// 实现了animal接口的dog和cat都能传进来
func animalVoice(a animal) {
	a.voice()
}

func TestAnimalInterface(t *testing.T) {
	d := dog{"Spike"}
	c := cat{"Tom"}
	animalVoice(d)
	animalVoice(&c)
}

/*-------------------------------------------------*/
// 类型判断，可以用断言和反射来判断

type numbers interface {
}

// 断言语法格式 value, ok := x.(T)
func TestInferAssert(t *testing.T) {
	var num numbers
	num = "20.22"
	//判断是否是string类型
	i, k := num.(string)
	//如果是string类型返回num的值，不是string类型就返回string默认值，空串
	fmt.Println(i)
	//是string类型返回true，不是返回false
	fmt.Println(k)

	fmt.Println("========split========")

	var a animal = dog{"Jack"}
	d := a.(dog)
	fmt.Println(d)

	c, b := a.(*cat)
	// 如果是cat指针类型就返回a的值，不是则返回默认值
	fmt.Println(c)
	// 是cat指针类型返回true，不是返回false
	fmt.Println(b)

	fmt.Println("========split========")

	c2 := a.(*cat)
	// 不写第二个参数，断言失败的话就会发生panic
	fmt.Println(c2)
}

func inferSwitch(a interface{}) {
	switch a.(type) {
	case dog:
		fmt.Println("dog is animal")
	case *cat:
		fmt.Println("cat is animal")
	default:
		fmt.Println("unknown type")
	}
}

// 做switch判断
func TestInferSwitch(t *testing.T) {
	d := dog{"Spike"}
	c := cat{"Tom"}
	inferSwitch(d)
	inferSwitch(c)
	// 因为上面cat实现的是指针类型，所以要传入指针才能识别
	inferSwitch(&c)
}

// 反射
func TestInferReflect(t *testing.T) {
	var str = "Hello"
	typeOf := reflect.TypeOf(str)
	fmt.Println(typeOf)

	var a animal = dog{}
	aType := reflect.TypeOf(a)
	fmt.Println(aType)
}

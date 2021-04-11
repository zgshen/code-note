<!-- TOC -->

- [1. Spring 模块组成](#1-spring-模块组成)
- [2. AOP面向切面编程](#2-aop面向切面编程)
- [3. IoC控制反转](#3-ioc控制反转)
- [4. 三种依赖注入的方式](#4-三种依赖注入的方式)
    - [4.1. 构造方法注入](#41-构造方法注入)
    - [4.2. setter 方法注入](#42-setter-方法注入)
    - [4.3. 接口注入](#43-接口注入)
- [5. Spring 使用的注入方式](#5-spring-使用的注入方式)
- [6. 用到哪些设计模式](#6-用到哪些设计模式)
- [7. @Transactional 注解哪些情况下会失效？](#7-transactional-注解哪些情况下会失效)
- [8. 常用注解](#8-常用注解)
- [9. bean循环引用如何解决](#9-bean循环引用如何解决)
- [10. 动态代理是什么？应用场景？如何实现](#10-动态代理是什么应用场景如何实现)

<!-- /TOC -->

### 1. Spring 模块组成
- Spring Core 框架核心，提供 IOC 容器，管理 bean 对象
- Spring Context 提供上下文信息
- Spring Dao 提供 JDBC 抽象层
- Spring ORM 提供“对象/关系”映射 APIs 的集成层
- Spring AOP 切面编程功能
- Spring Web 提供 web 开发的上下文信息
- Spring Web MVC 提供了 web 应用的 model-view-controller 实现

### 2. AOP面向切面编程
- 不改变原逻辑增加额外功能，将多个类公共行为封装为可重用模块，降低系统耦合度
- Spring 注解 @Aspect，应用于拦截器，认证、日志、同一异常处理（@ControllerAdvice）等等
- 实现方式
	- 动态代理技术 （JDK 动态代理、CGLib 动态代理）
	- 静态织入方式

### 3. IoC控制反转
- 把创建和查找依赖对象的控制权交给 IoC 容器
- DI 依赖注入是IOC容器装配和注入对象的一种方式
- 作用
	- 松耦合
	- 资源集中管理
	- 功能可复用

### 4. 三种依赖注入的方式
#### 4.1. 构造方法注入
将被依赖对象通过构造函数的参数注入给依赖对象，并且在初始化对象的时候注入。

优点： 对象初始化完成后便可获得可使用的对象。  
缺点： 当需要注入的对象很多时，构造器参数列表将会很长；不够灵活。若有多种注入方式，每种方式只需注入指定几个依赖，那么就需要提供多个重载的构造函数。

#### 4.2. setter 方法注入
IoC Service Provider 通过调用成员变量提供的setter函数将被依赖对象注入给依赖类。

优点： 灵活，可以选择性地注入需要的对象。  
缺点： 依赖对象初始化完成后由于尚未注入被依赖对象，因此还不能使用。

#### 4.3. 接口注入
依赖类必须要实现指定的接口，然后实现该接口中的一个函数，该函数就是用于依赖注入，该函数的参数就是要注入的对象。

优点： 接口注入中，接口的名字、函数的名字都不重要，只要保证函数的参数是要注入的对象类型即可。  
缺点： 侵入行太强，很少使用。

### 5. Spring 使用的注入方式
Spring 的注入方式只有两种，分别是 setter 注入和构造方法注入。  
通常使用的 @Autowried 注解实际是 setter 注入的一种变体。

Spring 的注入模式有四种：
- no
- byType
- byName
- constructor

### 6. 用到哪些设计模式
- 工厂模式：Spring 使用工厂模式可以通过 `BeanFactory` 或 `ApplicationContext` 创建 bean 对象；
- 单例模式：Spring 中 bean 的默认作用域就是 singleton(单例)的；
- 代理模式：Spring AOP 就是基于动态代理的；
- 观察者模式：Spring 事件驱动模型就是观察者模式很经典的一个应用。`ApplicationListener` 监听器；
- 适配器模式：Spring AOP 的增强或通知(Advice)使用到了适配器模式、spring MVC 中也是用到了适配器模式适配 Controller；
- 模版方法模式：Spring 中 jdbcTemplate、hibernateTemplate 等以 Template 结尾的对数据库操作的类，它们就使用到了模板模式；
- 装饰者模式

### 7. @Transactional 注解哪些情况下会失效？
- 作用在非 public 方法上
- 方法异常被捕获
- 数据库不支持事务（例如 MySQL 的 MyISAM）
- 没开启事务注解
- 同一类中加 @Transactional 方法被无 @Transactional 的方法调用，事务失效

### 8. 常用注解
- bean定义注解
	- @component 描述Spring框架中的bean
	- @Repository 用于对DAO实现类进行标注
	- @Service 用于对业务类进行标注
	- @Controller 用于对控制类进行标注
- Spring属性注入
	- @Autowired():自动注入
	- @Autowired(required=true):找到匹配的Bean
	- @Qualifier():可指定Bean的名称
	- @Resource()和Autowired()功能相似
- 其他输入
	- @PostConStruct():初始化
	- @PreDestory():销毁
	- @Scope()指定作用域
    - @Profile()指定环境bean生效

### 9. bean循环引用如何解决
Spring Bean 的循环依赖问题，是指类A通过构造函数注入类B的实例（或者B中声明的Bean），而类B通过构造函数注入类A的实例（或者A中声明的 Bean），即将类A和类B的bean配置为相互注入，则 Spring IoC 容器会在运行时检测到此循环引用，并引发一个 BeanCurrentlyInCreationException。
- 延迟加载 @Lazy，例如
	```java
	@Component
	public class CircularDependencyA {
	
		private CircularDependencyB circB;
	
		@Autowired
		public CircularDependencyA(@Lazy CircularDependencyB circB) {
			this.circB = circB;
		}
	}
	```
- 在实例变量上使用 @Autowired 注解，让 Spring 决定在合适的时机注入，而非在初始化类的时候就注入。
- 用基于 setter 方法的依赖注入取代基于构造函数的依赖注入来解决循环依赖。


### 10. 动态代理是什么？应用场景？如何实现
动态代理：在运行时，创建目标类，可以调用和扩展目标类的方法。  

应用场景：
- 统计每个 api 的请求耗时
- 统一的日志输出
- 校验被调用的 api 是否已经登录和权限鉴定
- Spring的 AOP 功能模块就是采用动态代理的机制来实现切面编程

实现方法：
- JDK 动态代理
- CGLib 动态代理
- 使用 Spring Aop 模块完成动态代理功能
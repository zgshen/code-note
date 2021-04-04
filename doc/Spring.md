<!-- TOC -->

- [1. AOP面向切面编程](#1-aop面向切面编程)
- [2. IoC控制反转](#2-ioc控制反转)
- [3. Spring 模块组成](#3-spring-模块组成)
- [4. 用到哪些设计模式](#4-用到哪些设计模式)
- [5. @Transactional 注解哪些情况下会失效？](#5-transactional-注解哪些情况下会失效)
- [6. 常用注解](#6-常用注解)
- [7. bean循环引用如何解决](#7-bean循环引用如何解决)
- [8. 动态代理是什么？应用场景？如何实现](#8-动态代理是什么应用场景如何实现)

<!-- /TOC -->
### 1. AOP面向切面编程
- 不改变原逻辑增加额外功能，将多个类公共行为封装为可重用模块，降低系统耦合度
- Spring注解 @Aspect，应用于拦截器，认证、日志、同一异常处理（@ControllerAdvice）等等
- 实现方式
	- 动态代理技术 （jdk动态代理、cglib动态代理）
	- 静态织入方式

### 2. IoC控制反转
- 把创建和查找依赖对象的控制权交给IoC
- DI依赖注入是IOC容器装配和注入对象的一种方式
- 作用
	- 松耦合
	- 资源集中管理
	- 功能可复用

### 3. Spring 模块组成
- Spring Core 框架核心，提供 IOC 容器，管理 bean 对象
- Spring Context 提供上下文信息
- Spring Dao 提供 JDBC 抽象层
- Spring ORM 提供“对象/关系”映射 APIs 的集成层
- Spring AOP 切面编程功能
- Spring Web 提供 web 开发的上下文信息
- Spring Web MVC 提供了 web 应用的model-view-controller 实现

### 4. 用到哪些设计模式
- 工厂模式：Spring使用工厂模式可以通过 `BeanFactory` 或 `ApplicationContext` 创建 bean 对象；
- 单例模式：Spring中bean的默认作用域就是singleton(单例)的；
- 代理模式：Spring AOP就是基于动态代理的；
- 观察者模式：Spring 事件驱动模型就是观察者模式很经典的一个应用。`ApplicationListener` 监听器；
- 适配器模式：Spring AOP 的增强或通知(Advice)使用到了适配器模式、spring MVC 中也是用到了适配器模式适配Controller；
- 模版方法模式：Spring 中 jdbcTemplate、hibernateTemplate 等以 Template 结尾的对数据库操作的类，它们就使用到了模板模式；
- 装饰者模式

### 5. @Transactional 注解哪些情况下会失效？
- 作用在非public方法上
- 方法异常被捕获
- 数据库不支持事务（例如MySQL的MyiSAM）
- 没开启事务注解
- 同一类中加 @Transactional 方法被无 @Transactional 的方法调用，事务失效

### 6. 常用注解
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

### 7. bean循环引用如何解决
Spring Bean的循环依赖问题，是指类A通过构造函数注入类B的实例（或者B中声明的Bean），而类B通过构造函数注入类A的实例（或者A中声明的Bean），即将类A和类B的bean配置为相互注入，则Spring IoC容器会在运行时检测到此循环引用，并引发一个BeanCurrentlyInCreationException。
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
- 在实例变量上使用@Autowired注解，让Spring决定在合适的时机注入，而非在初始化类的时候就注入。
- 用基于setter方法的依赖注入取代基于构造函数的依赖注入来解决循环依赖。


### 8. 动态代理是什么？应用场景？如何实现
动态代理：在运行时，创建目标类，可以调用和扩展目标类的方法。  

Java 中实现动态的方式：
- JDK 中的动态代理 
- Java类库 CGLib

应用场景：
- 统计每个 api 的请求耗时
- 统一的日志输出
- 校验被调用的 api 是否已经登录和权限鉴定
- Spring的 AOP 功能模块就是采用动态代理的机制来实现切面编程

实现：
- JDK 动态代理
- CGLib 动态代理
- 使用 Spring aop 模块完成动态代理功能
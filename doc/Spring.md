
#### 1、AOP面向切面编程
- 不改变原逻辑增加额外功能，将多个类公共行为封装为可重用模块，降低系统耦合度
- Spring注解 @Aspect，应用于拦截器，认证、日志、同一异常处理（@ControllerAdvice）等等
- 实现方式
	- 动态代理技术 （jdk动态代理、cglib动态代理）
	- 静态织入方式

#### 2、IoC控制反转
- 把创建和查找依赖对象的控制权交给IoC
- DI依赖注入是IOC容器装配和注入对象的一种方式
- 作用
	- 松耦合
	- 资源集中管理
	- 功能可复用

#### 3、@Transactional 注解哪些情况下会失效？
- 作用在非public方法上
- 方法异常被捕获
- 数据库不支持事务（例如MySQL的MyiSAM）
- 没开启事务注解
- 同一类中加 @Transactional 方法被无 @Transactional 的方法调用，事务失效

#### 4、常用注解
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

#### 5、bean循环引用如何解决
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
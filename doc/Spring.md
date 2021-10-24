
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
	- 动态代理技术（JDK 动态代理、CGLib 动态代理）
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
IoC Service Provider 通过调用成员变量提供的 setter 函数将被依赖对象注入给依赖类。

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
	- @component 描述 Spring 框架中的 bean
	- @Repository 用于对 DAO 实现类进行标注
	- @Service 用于对业务类进行标注
	- @Controller 用于对控制类进行标注
- Spring属性注入
	- @Autowired() 自动注入
	- @Autowired(required=true) 找到匹配的Bean
	- @Qualifier() 可指定Bean的名称。一个接口有多个实现类可指定使用哪种实现
	- @Resource() 和 Autowired() 功能相似，@Resource() 是 JDk 自带注解
- 其他输入
	- @PostConStruct() 初始化
	- @PreDestory() 销毁
	- @Scope() 指定作用域
    - @Profile() 指定环境bean生效

### 9. bean循环引用如何解决
Spring Bean 的循环依赖问题，是指类 A 通过构造函数注入类 B 的实例（或者B中声明的 Bean），而类 B 通过构造函数注入类 A 的实例（或者A中声明的 Bean），即将类 A 和类 B 的 bean 配置为相互注入，则 Spring IoC 容器会在运行时检测到此循环引用，并引发一个 BeanCurrentlyInCreationException。
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
JDK 动态代理只提供接口的代理，不支持类的代理。核心 InvocationHandler 接口和 Proxy 类，InvocationHandler 通过 invoke() 方法反射来调用目标类中的代码，动态地将横切逻辑和业务编织在一起；接着，Proxy 利用 InvocationHandler 动态创建一个符合某一接口的的实例, 生成目标类的代理对象。

- CGLib 动态代理  
如果代理类没有实现 InvocationHandler 接口，那么 Spring AOP 会选择使用 CGLIB 来动态代理目标类。CGLIB（Code Generation Library），是一个代码生成的类库，可以在运行时动态的生成指定类的一个子类对象，并覆盖其中特定方法并添加增强代码，从而实现 AOP。CGLIB 是通过继承的方式做的动态代理，因此如果某个类被标记为 final，那么它是无法使用CGLIB做动态代理的。

### 11. Spring MVC
![](images/SpringMVC.jfif)

#### 11.1. 工作原理
1、 用户发送请求至前端控制器 DispatcherServlet。

2、 DispatcherServlet 收到请求调用 HandlerMapping 处理器映射器。

3、 处理器映射器找到具体的处理器(可以根据xml配置、注解进行查找)，生成处理器对象及处理器拦截器(如果有则生成)一并返回给 DispatcherServlet。

4、 DispatcherServlet 调用 HandlerAdapter 处理器适配器。

5、 HandlerAdapter 经过适配调用具体的处理器(Controller，也叫后端控制器)。

6、 Controller 执行完成返回 ModelAndView。

7、 HandlerAdapter 将 Controller 执行结果 ModelAndView 返回给 DispatcherServlet。

8、 DispatcherServlet 将 ModelAndView 传给 ViewReslover 视图解析器。

9、 ViewReslover 解析后返回具体视图 View。

10、DispatcherServlet 根据 View 进行渲染视图（即将模型数据填充至视图中）。

11、 DispatcherServlet 响应用户。

#### 11.2. 组件说明
DispatcherServlet：作为前端控制器，整个流程控制的中心，控制其它组件执行，统一调度，降低组件之间的耦合性，提高每个组件的扩展性。

HandlerMapping：通过扩展处理器映射器实现不同的映射方式，例如：配置文件方式，实现接口方式，注解方式等。

HandlAdapter：通过扩展处理器适配器，支持更多类型的处理器。

ViewResolver：通过扩展视图解析器，支持更多类型的视图解析，例如：jsp、freemarker、pdf、excel等。
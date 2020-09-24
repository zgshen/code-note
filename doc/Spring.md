
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

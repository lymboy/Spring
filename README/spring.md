# Spring

## spring快速入门

1. 引入jar包

![1537966762010](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1537966762010.png)

2. 书写配置文件

==applicationContext.xml== 

~~~ xml

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean name="user" class="bean.User"></bean>

</beans>

scope属性:
	singleton(默认值):单例对象.被标识为单例的对象在spring容器中只会存在一个实例
	prototype:多例原型.被标识为多例的对象,每次再获得才会创建.每次创建都是新的对象.整合struts2						时,ActionBean必须配置为多例的.
	request:web环境下.对象与request生命周期一致.
	session:web环境下,对象与session生命周期一致.
~~~





3. 编写bean和测试代码

==User.java==

```java
package bean;

/**
 * @author sairo
 * @since 2018/9/26 20:33
 */
public class User {
    private Integer age;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

```

==Demo.java==	

~~~java
package test;

import bean.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author sairo
 * @since 2018/9/26 20:24
 */
public class Demo01 {

    @Test
    public void test01() {
        //创建工厂类
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        //解析XML获取bean实例
        User user = (User) ac.getBean("user");
        user.setName("jack");
        System.out.println(user);
    }
}

~~~

## 相关概念

**IOC:**(*Inversion of Control*) 控制反转，将类的创建交由spring管理

**DI:**(*dependency injection*) 依赖注入，通过 <font color="red">*xml*</font> 文件的方式将<font color="red">*bean*</font>中的属性注入

**AOP:**(*Aspect Oriented Programming*)面向切面编程，横向重复，纵向抽取

**Joinpoint:** (*连接点*) 目标对象中所有可以增强的方法，连接点是指那些被拦截到的点。在 spring 中,这些点指的是方法,因为 <font color="red">*spring*</font> 只支持方法类型的连接点.

**Pointcut:** (*切入点*) 目标对象中已经增强的方法，即 对 Joinpoint 进行拦截的定义

**Advice:**  (*通知/增强*) 增强的代码

**Target:** (*目标对象*) 被代理对象

**Proxy: **(*代理*) 将通知织入到目标对象之后形成代理对象

**Weaving:** (*织入*) 将通知应用到织入点的过程

**aspect:** (*切面*) 切入点+通知



## 属性注入

1. set方式注入

   ~~~xml
   <!--
   注意：
   	bean标签中使用constructor-arg表示对带参数的构造方法的操作
   -->
   <bean name="user" class="bean.User">
           <property name="age" value="18" />
           <property name="name" value="Jack" />
   </bean>
   ~~~

2. 对象成员属性注入(set)

   ==User.java==

   ~~~java
   public class User {
       private Integer age;
       private String name;
       private Car car;
       ...
   }
   ~~~

   ~~~xml
   <!--
   注意：
   	bean标签中使用constructor-arg表示对带参数的构造方法的操作
   	ref 表示对某个bean的引用
   -->
   <bean name="user" class="bean.User">
       <property name="age" value="18" />
       <property name="name" value="Jack" />
       <property name="car" ref="car" />
   </bean>
   
   <bean name="car" class="bean.Car">
       <property name="name" value="BMW" />
       <property name="color" value="RED" />
   </bean>
   ~~~

   3. 构造函数注入

   ~~~xml
   <!--
   	bean标签中使用property表示对属性的操作
   -->
   <bean name="car" class="bean.Car">
           <property name="name" value="BMW" />
           <property name="color" value="RED" />
   </bean>
   
   <!--构造参数注入-->
   <!--
   注意：
   	bean标签中使用constructor-arg表示对带参数的构造方法的操作
   	ref 表示对某个bean的引用
   -->
   <bean name="user" class="bean.User" >
       <constructor-arg name="age" value="21" />
       <constructor-arg name="name" value="Tom" />
       <constructor-arg name="car" ref="car" />
   </bean>
   ~~~

   4. SpEL  的方式的属性注入:Spring3.x 之后提供

   ~~~xml
   <!--
   注意：
   	#{}表示引用外部的配置，用bean名.属性名填充值
   -->
   <bean name="user03" class="bean.User" >
       <property name="age" value="#{user01.age}" />
       <property name="name" value="#{user01.name}" />
       <property name="car" value="#{user02.car}" />
   </bean>
   ~~~

   5. 注入复杂类型

   ==CollectionBean.java==

   ~~~java
   package bean;
   
   import java.util.Arrays;
   import java.util.List;
   import java.util.Map;
   import java.util.Properties;
   
   /**
    * @author sairo
    * @since 2018/9/27 18:39
    */
   public class CollectionBean {
   
       private Object[] obj;
       private List list;
       private Map map;
       private Properties pro;
   
       public Object[] getObj() {
           return obj;
       }
   
       public void setObj(Object[] obj) {
           this.obj = obj;
       }
   
       public List getList() {
           return list;
       }
   
       public void setList(List list) {
           this.list = list;
       }
   
       public Map getMap() {
           return map;
       }
   
       public void setMap(Map map) {
           this.map = map;
       }
   
       public Properties getPro() {
           return pro;
       }
   
       public void setPro(Properties pro) {
           this.pro = pro;
       }
   
       @Override
       public String toString() {
           return "CollectionBean{" +
                   "obj=" + Arrays.toString(obj) +
                   ", list=" + list +
                   ", map=" + map +
                   ", pro=" + pro +
                   '}';
       }
   }
   
   ~~~

   ==配置==

   ~~~xml
   <!-- 复杂类型注入 -->
   <bean name="collectionBean" class="bean.CollectionBean" >
       <!-- 数组类型注入 -->
       <property name="obj" >
           <array>
               <value>Tom</value>
               <value>Jack</value>
               <value>Kitty</value>
           </array>
       </property>
       <!-- List类型注入 -->
       <property name="list">
           <list>
               <value>Cat</value>
               <value>Dog</value>
               <value>Pig</value>
           </list>
       </property>
       <!-- Map类型注入 -->
       <property name="map" >
           <map>
               <entry key="111" value="aaaa" />
               <entry key="222" value="bbbb" />
               <entry key="333" value="cccc" />
           </map>
       </property>
       <!-- Properties类型注入 -->
       <property name="pro" >
           <props>
               <prop key="username" >root</prop>
               <prop key="password" >123456</prop>
           </props>
       </property>
   </bean>
   ~~~

   ==测试==

   ~~~java
   @Test
   public void test03() {
       ApplicationContext ac = new 		     	ClassPathXmlApplicationContext("applicationContext.xml");
       CollectionBean collectionBean = (CollectionBean) ac.getBean("collectionBean");
   
       System.out.println(collectionBean);
   }
   ~~~

## Spring生成Bean大的三种方式

1. 无参数的构造参数的实例化

   ~~~xml
   <!-- 方式一：无参数的构造方法的实例化 -->
   <bean id="bean1" class="cn.itcast.spring.demo3.Bean1"></bean>
   ~~~

2. 静态工厂的实例化

   ==工厂类==

   ~~~java
   /**
    * 重点是创建一个静态方法，返回要创建的 Bean 类
    */
   public class Bean2Factory {
   	public static Bean2 getBean2(){
   		return new Bean2();
   	}
   }
   ~~~

   ==静态工厂实例化方式==

   ~~~xml
   <!-- 
   	在工厂类的配置中添加 factory-method 属性，赋予相关的静态方法 
   -->
   <bean  id="bean2"  class="cn.itcast.spring.demo3.Bean2Factory" 
         factory-method="getBean2"/>
   ~~~

3. 实例工厂实例化

   ==工厂类==

   ~~~java
   /**
    * 工厂中的方法不是静态的
    * 返回要创建的 Bean
    */
   public class Bean3Factory {
   	public Bean3 getBean3(){
   		return new Bean3();
   	}
   }
   ~~~

   ==工厂实例化方式==

   ~~~xml
   <!--
   	配置实例工厂
   	在真正的 Bean 中添加 factory-bean指向工厂类， factory-method指向工厂方法
   -->
   <bean id="bean3Factory" class="cn.itcast.spring.demo3.Bean3Factory"></bean>
   <bean id="bean3" factory-bean="bean3Factory" factory-method="getBean3"></bean>
   ~~~









## 配置文件相关

1. 分配置文件编写

   ==创建工厂类时导入==

   ~~~java
   ApplicationContext  applicationContext  =  new ClassPathXmlApplicationContext("applicationContext.xml","applicationContext2.xml");
   ~~~

   ==配置文件包含另一个文件==

   ~~~xml
    <import resource="applicationContext2.xml"></import>
   ~~~



## 使用注解

1. 导入<font color="red">*jar*</font>包

   ==4+2+spring-aop-4.2.4.RELEASE.jar==

   ![1538214685822](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1538214685822.png)

2. 在<font color="red">*applicationContext.xml*</font>中配置相关参数

   - 2.1 导入约束

     ~~~xml
     // 主要是导入context约束
     <beans xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 	xmlns="http://www.springframework.org/schema/beans" xmlns:context="http://www.springframework.org/schema/context" xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.2.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.2.xsd ">
         
     </beans>
     ~~~

   - 2.2 开启使用注解代理配置文件

     ~~~xml
     // base-package 属性会扫描 spring03.bean 下的所有子孙包
     // 只有配置了这个才能使用注解让spring管理对象
     <context:component-scan base-package="spring03.bean"/>
     ~~~

3. 配置注解 

   - 3.1 将对象注册到容器

     ![1538277922819](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1538277922819.png)

     ==解释：==

     ~~~text
     @Component("user") 注解的作用是将 User 注册到Spring中交由Spring管理，省去了bean的配置。
     	为了逻辑的清晰，@Component组件下衍生了三个子组件，用于配置不同层的类，但他们的功能是一样	的
             @Repository("user")     // dao层
             @Service("user")        // service层
             @Controller("user")     // web层
     ~~~

   - 3.2 修改对象的作用范围， <font color="red">*@Scope*</font>

     ![1538278843124](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1538278843124.png)

     ==解释：==

     ~~~text
     @Scope(scopeName = "prototype") 多例，会产生多个对象，与structs2整合时需要配置，在多例模式下 bean 中无法使用 init-method
     @Scope(scopeName = "singleton") 单例【默认设置】
     ~~~

   - 3.3 属性注入  <font color="red">*@Value*</font>,  <font color="red">*@Resource*</font>,   <font color="red">*@Autowired  @Qualifier*</font>,   <font color="red">@PostConstruct @PreDestroy</font>

     ==@Value==

     ![1538279516892](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1538279516892.png)

     ==解释：==

     ~~~text
     @Value(value = "xxx")用来注入简单类型，当一个注解只有一个value时value可以省略不写
     ~~~

   - 3.4 ==@Autowired, @Qualifier==  <font color="red">配合使用</font>

     ![1538295767786](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1538295767786.png)

     ==解释：==

     ~~~text
     @Autowired: 自动装配
     	问题：如果存在多个类型匹配的对象，将无法选择具体注入哪一个对象
     @Qualifier: 指定自动注入哪个名称的对象
     ~~~

   - 3.5 ==@Resource==

     ![1538296119687](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1538296119687.png)

     ==解释：==

     ~~~text
     @Resource: 相当于: @Autowired 和@Qualifier 一起使用.
     	注意：要加入 name 属性
     ~~~

   - 3.6  ==@PostConstruct， @PreDestroy==

     ![1538296741267](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1538296741267.png)

     ==解释：==

     ~~~text
     @PostConstruct :相当于 init-method
     @PreDestroy :相当于 destroy-method
     ~~~

   - 3.2 


## Spring融合Junit测试

1. 导入 <font color="red">*jar*</font>包 

   ==4+2+2（aop&test）==

   ![1538058457777](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1538058457777.png)
2. 编写代码

```java
// 帮助创建容器
// 用于测试，添加在测试类上
// 就是不用每次创建 ApplicationContext ac = new 	ClassPathXmlApplicationContext("applicationContext.xml");
@RunWith(SpringJUnit4ClassRunner.class)
// 创建容器时加载对应的配置文件
@ContextConfiguration("classpath:applicationContext.xml")
public class Demo02 {
	// 注意Resource中要写 name
    // @Resource 的作用是将配置文件中的属性注入到成员变量中，不用写getter和setter
    @Resource(name = "user01")
    private User u;

    @Test
    public void test02() {

        System.out.println(u);
    }
}
```

# AOP-通知织入

0. 相关概念

   ~~~text
   前置通知	
   	|-目标方法运行之前调用
   后置通知(如果出现异常不会调用)
   	|-在目标方法运行之后调用
   环绕通知
   	|-在目标方法之前和之后都调用
   异常拦截通知
   	|-如果出现异常,就会调用
   后置通知(无论是否出现 异常都会调用)
   	|-在目标方法运行之后调用
   ~~~

1. 导入 <font color="red">*jar*</font>包 

   ==4+2+2+2==

   ![1538300147626](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1538300147626.png)

2. 准备目标对象

   ~~~java
   package service.impl;
   
   import service.UserService;
   
   /**
    * @author sairo
    * @since 2018/9/30 17:38
    */
   public class UserServiceImpl implements UserService {
       @Override
       public void save() {
           System.out.println("Save User!!!");
       }
   
       @Override
       public void delete() {
           System.out.println("Delete User!!!");
       }
   
       @Override
       public void update() {
           System.out.println("Update User!!!");
       }
   
       @Override
       public void find() {
           System.out.println("Find User!!!");
       }
   }
   ~~~

3. 准备通知

   ~~~java
   package advice;
   
   import org.aspectj.lang.ProceedingJoinPoint;
   
   /**
    * @author sairo
    * @since 2018/9/30 17:52
    */
   public class MyAdvice {
       // 前置通知
       public void before() {
           System.out.println("前置通知");
       }
       // 后置通知, 如果出现异常不会调用
       public void afterReturning() {
           System.out.println("后置通知, 如果出现异常不会调用");
       }
       // 环绕通知
       public Object around(ProceedingJoinPoint pjp) throws Throwable {
           System.out.println("这是环绕通知之前的部分");
   
           Object proceed = pjp.proceed();     // 调用目标方法
   
           System.out.println("这是环绕通知之后的部分");
   
           return proceed;
       }
       // 异常通知
       public void afterException() {
           System.out.println("产生了异常！！！");
       }
       // 后置通知
       public void after() {
           System.out.println("后置通知, 出现异常也会调用");
       }
   }
   ~~~

4. 配置进行织入,将通知织入目标对象中

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://www.springframework.org/schema/beans" xmlns:context="http://www.springframework.org/schema/context" xmlns:aop="http://www.springframework.org/schema/aop" xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.2.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.2.xsd http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.2.xsd ">
   
       <!-- 准备工作: 导入aop(约束)命名空间 -->
       <!-- 1.配置目标对象 -->
       <bean name="userService" class="service.impl.UserServiceImpl" ></bean>
       <!-- 2.配置通知对象 -->
       <bean name="myAdvice" class="test.MyAdvice" ></bean>
       <bean name="as" class="test.MyAdvice" />
       <!-- 3.配置将通知织入目标对象 -->
       <aop:config>
           <!-- 配置切入点
   			1. public 可以省略
               public void cn.service.impl.UserServiceImpl.save()
               void cn.service.impl.UserServiceImpl.save()
   			2. 返回值 * 代表返回任意类型
               * cn.service.impl.UserServiceImpl.save()
   			3. 最后的 * 代表此对象下的任意方法
               * cn.service.impl.UserServiceImpl.*()
   			4. 第二个 * 代表任意以 ServiceImpl 结尾的对象
               * cn.itcast.service.*ServiceImpl.*(..)
   			5. 连续两个点代表包含了此包下的所有子孙包
               * cn.itcast.service..*ServiceImpl.*(..)
           -->
           <aop:pointcut expression="execution(* service..*ServiceImpl.*(..))" id="pc"/>
           <aop:aspect ref="myAdvice" >
               <!-- 指定名为before方法作为前置通知 -->
               <aop:before method="before" pointcut-ref="pc" />
               <!-- 后置 -->
               <aop:after-returning method="afterReturning" pointcut-ref="pc" />
               <!-- 环绕通知 -->
               <aop:around method="around" pointcut-ref="pc" />
               <!-- 异常拦截通知 -->
               <aop:after-throwing method="afterException" pointcut-ref="pc"/>
               <!-- 后置 -->
               <aop:after method="after" pointcut-ref="pc"/>
           </aop:aspect>
       </aop:config>
   </beans>
   
   ```

5. 测试

   ```java
   public class Demo {
   	@Resource(name="userService")
   	private UserService us;
   	
   	@Test
   	public void fun1(){
   		us.save();
   	}
   	
   }
   ```

   ![1538316636353](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1538316636353.png)



# Spring-JDBC

## AOP事务

### 思路

1. 依赖关系

![1538472203627](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1538472203627.png)

2. 事务的传播行为

   ![1538472271499](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1538472271499.png)

### 搭建环境

1. 导入 *<font color="red">jar</font>* 包

   ![1538471322410](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1538471322410.png)

![1538471356246](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1538471356246.png)

2. 准备 *<font color="red">sql</font>*

   ![1538471589720](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1538471589720.png)

   ~~~sql
   CREATE TABLE t_account(a_id INT PRIMARY KEY AUTO_INCREMENT,
   a_name VARCHAR(255) NOT NULL,
   a_money DOUBLE );
   ~~~

### 书写代码

1.  书写 *<font color="red">dao</font>* 层

   ~~~java
   public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {
       @Override
       public void increaseMoney(Integer id, Double money) {
           String sql = "UPDATE t_account SET a_money = a_money + ? WHERE a_id = ?";
           this.getJdbcTemplate().update(sql, money, id);
       }
   
       @Override
       public void decreaseMoney(Integer id, Double money) {
           String sql = "UPDATE t_account SET a_money = a_money - ? WHERE a_id = ?";
           this.getJdbcTemplate().update(sql, money, id);
       }
   }
   ~~~


2.  书写 *<font color="red">service</font>* 

   ~~~java
   public class AccountServiceImpl implements AccountService {
   
       private AccountDao ad;
       private TransactionTemplate tt;
   
       @Override
       public void transfer(Integer from, Integer to, Double money) {
           tt.execute(new TransactionCallbackWithoutResult() {
               @Override
               protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                   ad.decreaseMoney(from, money);
                   ad.increaseMoney(to, money);
               }
           });
       }
   
       ...  (setter & getter)
   }
   ~~~


3.  配置 *<font color="red">applicationContext.xml</font>*

   ~~~java
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://www.springframework.org/schema/beans" xmlns:context="http://www.springframework.org/schema/context" xmlns:aop="http://www.springframework.org/schema/aop" xmlns:tx="http://www.springframework.org/schema/tx" xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.2.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.2.xsd http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.2.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.2.xsd ">
   
       <!-- 指定spring读取db.properties配置 -->
       <context:property-placeholder location="classpath:db.properties"  />
   
       <!-- 事务核心管理器,封装了所有事务操作. 依赖于连接池 -->
       <bean name="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager" >
           <property name="dataSource" ref="dataSource" ></property>
       </bean>
       <!-- 事务模板对象 -->
       <bean name="transactionTemplate" class="org.springframework.transaction.support.TransactionTemplate" >
           <property name="transactionManager" ref="transactionManager" ></property>
       </bean>
   
       <!-- 开启使用注解管理aop事务 -->
       <tx:annotation-driven/>
   
       <!-- 1.将连接池 -->
       <bean name="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource" >
           <property name="jdbcUrl" value="${jdbc.jdbcUrl}" ></property>
           <property name="driverClass" value="${jdbc.driverClass}" ></property>
           <property name="user" value="${jdbc.user}" ></property>
           <property name="password" value="${jdbc.password}" ></property>
       </bean>
   
   
   
       <!-- 2.Dao-->
       <bean name="accountDao" class="dao.impl.AccountDaoImpl" >
           <property name="dataSource" ref="dataSource" ></property>
       </bean>
       <!-- 3.Service-->
       <bean name="accountService" class="service.impl.AccountServiceImpl" >
           <property name="ad" ref="accountDao" ></property>
           <property name="tt" ref="transactionTemplate" ></property>
       </bean>
   
   </beans>
   ~~~

### 测试

~~~java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test/applicationContext01.xml")
public class Demo03 {
    @Resource(name = "accountService")
    private AccountService as;

    @Test
    public void test01() {
        Integer from = 1;
        Integer to = 2;
        Double money = 100d;

        as.transfer(from, to, money);
    }
}
~~~

## 事务的管理方式

### 编码式

1. 将核心事务管理器配置到spring容器

   ~~~xml
   <!-- 事务核心管理器,封装了所有事务操作. 依赖于连接池 -->
       <bean name="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager" >
           <property name="dataSource" ref="dataSource" ></property>
       </bean>
   ~~~

2. 配置TransactionTemplate模板

   ~~~xml
   <!-- 事务模板对象 -->
       <bean name="transactionTemplate" class="org.springframework.transaction.support.TransactionTemplate" >
           <property name="transactionManager" ref="transactionManager" ></property>
       </bean>
   ~~~

3. 将事务模板注入Service

   ~~~xml
   <!-- 3.Service-->
       <bean name="accountService" class="service.impl.AccountServiceImpl" >
           <property name="ad" ref="accountDao" ></property>
           <property name="tt" ref="transactionTemplate" ></property>
       </bean>
   ~~~

4. 在Service中调用模板

### XML配置

1. 导包
   ​	![1538472805790](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1538472805790.png)

2. 添加约束

   ![1538472880719](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1538472880719.png)

   ~~~xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://www.springframework.org/schema/beans" xmlns:context="http://www.springframework.org/schema/context" xmlns:aop="http://www.springframework.org/schema/aop" xmlns:tx="http://www.springframework.org/schema/tx" xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.2.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.2.xsd http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.2.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.2.xsd ">
   
   </beans>
   ~~~

3.  配置通知

   ~~~xml
   <!-- 事务核心管理器,封装了所有事务操作. 依赖于连接池 -->
   <bean name="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager" >
   	<property name="dataSource" ref="dataSource" ></property>
   </bean>
   ~~~

   ~~~xml
   <!-- 配置事务通知 -->
   <tx:advice id="txAdvice" transaction-manager="transactionManager" >
   	<tx:attributes>
   		<!-- 以方法为单位,指定方法应用什么事务属性
   			isolation:隔离级别
   			propagation:传播行为
   			read-only:是否只读
   		 -->
   		<tx:method name="save*" isolation="REPEATABLE_READ" propagation="REQUIRED" read-only="false" />
   		<tx:method name="persist*" isolation="REPEATABLE_READ" propagation="REQUIRED" read-only="false" />
   		<tx:method name="update*" isolation="REPEATABLE_READ" propagation="REQUIRED" read-only="false" />
   		<tx:method name="modify*" isolation="REPEATABLE_READ" propagation="REQUIRED" read-only="false" />
   		<tx:method name="delete*" isolation="REPEATABLE_READ" propagation="REQUIRED" read-only="false" />
   		<tx:method name="remove*" isolation="REPEATABLE_READ" propagation="REQUIRED" read-only="false" />
   		<tx:method name="get*" isolation="REPEATABLE_READ" propagation="REQUIRED" read-only="true" />
   		<tx:method name="find*" isolation="REPEATABLE_READ" propagation="REQUIRED" read-only="true" />
   		<tx:method name="transfer" isolation="REPEATABLE_READ" propagation="REQUIRED" read-only="false" />
   	</tx:attributes>
   </tx:advice>
   ~~~

4.  将通知织入目标

   ~~~xml
   <!-- 配置织入 -->
   <aop:config  >
   	<!-- 配置切点表达式 -->
   	<aop:pointcut expression="execution(* cn.itcast.service.*ServiceImpl.*(..))" id="txPc"/>
   	<!-- 配置切面 : 通知+切点
   		 	advice-ref:通知的名称
   		 	pointcut-ref:切点的名称
   	 -->
   	<aop:advisor advice-ref="txAdvice" pointcut-ref="txPc" />
   </aop:config>
   ~~~

### 注解

​	==1~2同上==

3.  开启注解管理事务

   ~~~xml
   <!-- 开启使用注解管理aop事务 -->
   <tx:annotation-driven/>
   ~~~

4. 使用注解

   ![1538473419650](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1538473419650.png)

![1538473442138](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1538473442138.png)
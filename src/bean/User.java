package bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @author sairo
 * @since 2018/9/30 11:11
 */

//相当于 <bean name="user" class="bean.User" ></bean>
@Component("user")
/*
    @Repository("user")     // dao层
    @Service("user")        // service层
    @Controller("user")     // web层
*/
@Scope(scopeName = "singleton")
public class User {

    @Value("20")
    private Integer age;
    @Value("Jack")
    private String name;

//  @Autowired          //自动装配
//  @Qualifier("car")   //多个类型一致的对象存在时，使用@Qualifier指定装配具体的对象
    @Resource(name = "car")
    private Car car;

    @PostConstruct
    public void init() {
        System.out.println("init.....");
    }
    @PreDestroy
    public void destory() {
        System.out.println("destory.....");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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
                ", car=" + car +
                '}';
    }
}

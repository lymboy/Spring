package test;

import bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.UserService;

import javax.annotation.Resource;

/**
 * @author sairo
 * @since 2018/9/30 11:16
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test/applicationContext.xml")
public class Demo01 {

    @Resource(name = "userService")
    private UserService us;

    @Test
    public void test01() {

        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

        User user = (User) ac.getBean("user");

        System.out.println(user);
    }

    @Test
    public void test02() {
        us.save();
    }

}

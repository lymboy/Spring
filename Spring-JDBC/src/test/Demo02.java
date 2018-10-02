package test;

import bean.User;
import dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author sairo
 * @since 2018/10/1 21:19
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Demo02 {
    @Resource(name = "userDao")
    private UserDao userDao;

    @Test
    public void test01() {
        User u = new User();
        u.setId(1);
        u.setName("Linus");

        userDao.save(u);
    }
}

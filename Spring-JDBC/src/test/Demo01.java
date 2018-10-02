package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author sairo
 * @since 2018/10/1 15:24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test/applicationContext.xml")
public class Demo01 {

    public static void main(String[] args) {
        System.out.println("Main.......Main");
    }

    @Test
    public void test01() {

    }
}

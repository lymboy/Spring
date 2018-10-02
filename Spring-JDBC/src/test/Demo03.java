package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.AccountService;

import javax.annotation.Resource;

/**
 * @author sairo
 * @since 2018/10/2 16:41
 */

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

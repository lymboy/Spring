package dao.impl;

import dao.AccountDao;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * @author sairo
 * @since 2018/10/2 16:22
 */
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

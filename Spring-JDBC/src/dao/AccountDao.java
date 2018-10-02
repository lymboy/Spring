package dao;

/**
 * @author sairo
 * @since 2018/10/2 16:15
 */
public interface AccountDao {
    // 入账
    void increaseMoney(Integer id, Double money);

    // 付款
    void decreaseMoney(Integer id, Double money);
}

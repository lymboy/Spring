package service;

/**
 * @author sairo
 * @since 2018/10/2 16:32
 */
public interface AccountService {

    /**
     * 实现转账功能
     * @param from  付款账户
     * @param to    收款账户
     * @param money 交易金额
     */
    void transfer(Integer from, Integer to, Double money);
}

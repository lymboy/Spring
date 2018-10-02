package service.impl;

import dao.AccountDao;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import service.AccountService;

/**
 * @author sairo
 * @since 2018/10/2 16:34
 */
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

    public void setAd(AccountDao ad) {
        this.ad = ad;
    }

    public AccountDao getAd() {
        return ad;
    }

    public TransactionTemplate getTt() {
        return tt;
    }

    public void setTt(TransactionTemplate tt) {
        this.tt = tt;
    }
}

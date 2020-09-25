package com.agileengine.accounting.repository;

import com.agileengine.accounting.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class UserAccountingRepository implements AccountingRepository {

    private UserAccount account;

    @Autowired
    public UserAccountingRepository() {
        account = new UserAccount("UserName", new BigDecimal(1000));
    }

    public UserAccount getAccount() {
        return account;
    }

}

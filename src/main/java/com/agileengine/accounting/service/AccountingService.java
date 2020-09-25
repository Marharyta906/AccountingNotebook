package com.agileengine.accounting.service;

import static com.agileengine.accounting.model.TransactionType.*;

import com.agileengine.accounting.exception.LowBalanceException;
import com.agileengine.accounting.model.Transaction;
import com.agileengine.accounting.model.TransactionType;
import com.agileengine.accounting.model.UserAccount;
import com.agileengine.accounting.repository.AccountingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class AccountingService {

    private AccountingRepository accountingRepository;

    @Autowired
    public AccountingService(AccountingRepository accountingRepository) {
        this.accountingRepository = accountingRepository;
    }

    public List<Transaction> getAllTransactions() {
        UserAccount userAccount = accountingRepository.getAccount();
        return userAccount.getTransactionsList();
    }

    public Transaction createNewTransactionAndUpdateBalance(String type, BigDecimal amount) throws LowBalanceException {
        UserAccount userAccount = accountingRepository.getAccount();
        TransactionType transactionType = valueOf(type.toUpperCase());
        updateBalance(amount, userAccount, transactionType);
        Transaction transaction = new Transaction(transactionType, amount, new Date());
        userAccount.getTransactionsList().add(transaction);
        return transaction;
    }

    private void updateBalance(BigDecimal amount, UserAccount userAccount, TransactionType transactionType) throws LowBalanceException {
        BigDecimal updatedBalance = getUpdatedBalance(transactionType, userAccount.getAccountBalance(), amount);
        checkBalanceNotNegative(updatedBalance);
        userAccount.setAccountBalance(updatedBalance);
    }

    private BigDecimal getUpdatedBalance(TransactionType type, BigDecimal currentAmount, BigDecimal transactionalAmount) {
        BigDecimal resultAmount;
        if (type == CREDIT) {
            resultAmount = currentAmount.subtract(transactionalAmount);
        } else {
            resultAmount = currentAmount.add(transactionalAmount);
        }
        return resultAmount;
    }

    public Optional<Transaction> getTransaction(int id) {
        UserAccount userAccount = accountingRepository.getAccount();
        return userAccount.getTransactionsList().stream().filter(transaction -> transaction.getId() == id).findAny();
    }

    public void checkBalanceNotNegative(BigDecimal amount) throws LowBalanceException {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new LowBalanceException("Insufficient amount of funds on account balance");
        }
    }

    public BigDecimal getBalance() {
        return accountingRepository.getAccount().getAccountBalance();
    }


}

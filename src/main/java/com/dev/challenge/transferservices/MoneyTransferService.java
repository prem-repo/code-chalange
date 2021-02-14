package com.dev.challenge.transferservices;

import com.dev.challenge.domain.Account;
import com.dev.challenge.dto.TransferMoney;
import com.dev.challenge.dto.TransferredMoneyDetails;
import com.dev.challenge.exception.BaseException;
import com.dev.challenge.repository.AccountRepository;
import org.springframework.dao.DataAccessException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MoneyTransferService {

    private AccountRepository accountRepository;
    private TransferMoney transferMoney;


    public MoneyTransferService(TransferMoney transferMoney, AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.transferMoney = transferMoney;

    }

    public TransferredMoneyDetails transferMoneyFromOneAccountToOther() throws BaseException {
        TransferredMoneyDetails transferredMoneyDetails = new TransferredMoneyDetails();
        Account fromAccount = null;
        Account toAccount = null;
        try {
            fromAccount = accountRepository.findByAccountNumber(transferMoney.getFromAccount());
            if(fromAccount==null)throw new BaseException("Account# " + transferMoney.getFromAccount() + " not exist in the system", 400);
        } catch (DataAccessException doaEx) {
            throw new BaseException("Account# " + transferMoney.getFromAccount() + " not exist in the system", 400);
        }
        try {
            toAccount = accountRepository.findByAccountNumber(transferMoney.getToAccount());
            if(toAccount==null)throw new BaseException("Account# " + transferMoney.getToAccount() + " not exist in the system", 400);
        } catch (DataAccessException doaEx) {
            throw new BaseException("Account# " + transferMoney.getToAccount() + " not exist in the system", 400);
        }
        double transferAmount = transferMoney.getAmount();
        if ((fromAccount.getBalance() - transferAmount) >= 0) {
            double newBalanceOfToAccount = toAccount.getBalance() + transferAmount;
            toAccount.setBalance(newBalanceOfToAccount);
            double newBalanceOfFromAccount = fromAccount.getBalance() - transferAmount;
            fromAccount.setBalance(newBalanceOfFromAccount);
            transferredMoneyDetails.setAmountTransferred(transferAmount);
            transferredMoneyDetails.setMsg("Transaction successful!");
            transferredMoneyDetails.setTransferDate(LocalDateTime.now());
            List<Account> accounts = new ArrayList<>();
            accounts.add(fromAccount);
            accounts.add(toAccount);
            accountRepository.saveAll(accounts);

        } else {
            throw new BaseException("Insufficient amount in Account# " + fromAccount.getAccountNumber() + " not exist in the system", 400);
        }

        transferredMoneyDetails.setFromAccount(fromAccount.getAccountNumber());
        transferredMoneyDetails.setToAccount(toAccount.getAccountNumber());
        return transferredMoneyDetails;
    }

}


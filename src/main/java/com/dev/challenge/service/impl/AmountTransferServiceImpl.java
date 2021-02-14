package com.dev.challenge.service.impl;

import com.dev.challenge.dto.TransferMoney;
import com.dev.challenge.dto.TransferredMoneyDetails;
import com.dev.challenge.exception.BaseException;
import com.dev.challenge.repository.AccountRepository;
import com.dev.challenge.service.AmountTransferService;
import com.dev.challenge.transferservices.MoneyTransferService;
import com.dev.challenge.transferservices.TransferServiceRunner;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
@Service
public class AmountTransferServiceImpl implements AmountTransferService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public TransferredMoneyDetails amountTransfer(TransferMoney transferMoney){
        if (StringUtils.isBlank(transferMoney.getFromAccount())) throw new BaseException("From Account Number can not be null.", 400);
        if (StringUtils.isBlank(transferMoney.getToAccount())) throw new BaseException("To Account Number can not be null.", 400);
        if (transferMoney.getAmount() <= 0) throw new BaseException("Amount must be greater than 0", 400);

        TransferredMoneyDetails transferredMoneyDetails;
        try {
            MoneyTransferService moneyTransferService = new MoneyTransferService(transferMoney, accountRepository);
            TransferServiceRunner transferServiceRunner = new TransferServiceRunner(moneyTransferService);
            ExecutorService es = Executors.newSingleThreadExecutor();
            Future<TransferredMoneyDetails> futureTransferredMoneyDetails = es.submit(transferServiceRunner);
            transferredMoneyDetails = futureTransferredMoneyDetails.get();
        } catch (BaseException base) {
            throw new BaseException(base.getMessage(), base.getCode());
        } catch (Exception e) {
            throw new BaseException( e.getCause().getMessage(),  500);
        }
        return transferredMoneyDetails;
    }
}

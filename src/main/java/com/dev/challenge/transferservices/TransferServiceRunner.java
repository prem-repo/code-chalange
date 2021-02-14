package com.dev.challenge.transferservices;

import com.dev.challenge.dto.TransferredMoneyDetails;
import com.dev.challenge.exception.BaseException;

import java.util.concurrent.Callable;

public class TransferServiceRunner implements Callable<TransferredMoneyDetails> {

    private final MoneyTransferService moneyTransferService;

    public TransferServiceRunner(MoneyTransferService moneyTransferService) {
        this.moneyTransferService = moneyTransferService;
    }


    @Override
    public TransferredMoneyDetails call()  {
        TransferredMoneyDetails transferredMoneyDetails;
        try {
            transferredMoneyDetails= moneyTransferService.transferMoneyFromOneAccountToOther();
        }catch (BaseException base){
            throw new BaseException(base.getMessage(),base.getCode());
        }catch (Exception e){
            throw new BaseException(e.getMessage(),500);
        }
        return transferredMoneyDetails;
    }
}

package com.dev.challenge.service;

import com.dev.challenge.dto.TransferMoney;
import com.dev.challenge.dto.TransferredMoneyDetails;

public interface AmountTransferService {
    TransferredMoneyDetails amountTransfer(TransferMoney transferMoney) ;
}

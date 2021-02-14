package com.dev.challenge.dto;

import lombok.Data;

@Data
public class TransferMoney {
    private String fromAccount;
    private String toAccount;
    private double amount;

}

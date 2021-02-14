package com.dev.challenge.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransferredMoneyDetails {
    private String fromAccount;
    private String toAccount;
    private double amountTransferred;
    private LocalDateTime transferDate;
    private String msg;
}

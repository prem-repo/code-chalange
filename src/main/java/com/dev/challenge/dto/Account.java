package com.dev.challenge.dto;

import lombok.Data;

@Data
public class Account {
    private String accountNumber;
    private double amountInAccount;
    private String accountHolderName;
    private AccountDetails accountDetails;

    public Account(String accountNumber, double amountInAccount) {
        this.accountNumber = accountNumber;
        this.amountInAccount = amountInAccount;
    }

}

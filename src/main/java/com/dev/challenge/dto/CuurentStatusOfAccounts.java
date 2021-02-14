package com.dev.challenge.dto;

import lombok.Data;

@Data
public class CuurentStatusOfAccounts {
    private Account fromAccount;
    private Account toAccount;

    public CuurentStatusOfAccounts(Account fromAccount, Account toAccount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }


}

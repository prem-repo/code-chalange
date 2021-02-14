package com.dev.challenge.dto;

import lombok.Data;

@Data
public class PreviousStatueOfAccounts {
    private Account fromAccount;
    private Account toAccount;

    public PreviousStatueOfAccounts(Account fromAccount, Account toAccount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }


}

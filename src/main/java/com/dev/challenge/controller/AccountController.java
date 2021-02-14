package com.dev.challenge.controller;


import com.dev.challenge.dto.Account;
import com.dev.challenge.dto.OpenAccount;
import com.dev.challenge.dto.TransferMoney;
import com.dev.challenge.dto.TransferredMoneyDetails;
import com.dev.challenge.exception.BaseException;
import com.dev.challenge.exception.HttpException;
import com.dev.challenge.repository.AccountRepository;
import com.dev.challenge.service.AmountTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AccountController {
    @Autowired
    private AmountTransferService amountTransferService;

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/transfermoney")
    public ResponseEntity<TransferredMoneyDetails> transferAmountBetweenAccounts(@RequestBody TransferMoney transferMoney) throws Exception {
        ResponseEntity<TransferredMoneyDetails> responseEntity;
        try {
            responseEntity = new
                    ResponseEntity<>(amountTransferService.amountTransfer(transferMoney), HttpStatus.OK);
        } catch (BaseException ex) {
            throw new HttpException(ex.getMessage(), 500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("/openaccount")
    public ResponseEntity<Account> openAccount(@RequestBody OpenAccount openAccount) {
        com.dev.challenge.domain.Account accountDomain = new com.dev.challenge.domain.Account();
        accountDomain.setAccountHolderName(openAccount.getAccountHolderName());
        accountDomain.setBalance(openAccount.getOpeningAmount());
        accountDomain = accountRepository.save(accountDomain);
        String accNumber = "SBI" + accountDomain.getId();
        accountDomain.setAccountNumber(accNumber);
        accountDomain = accountRepository.save(accountDomain);
        return new ResponseEntity<>(mapAccount(accountDomain), HttpStatus.OK);
    }

    @GetMapping("/listaccount")
    public ResponseEntity<List<Account>> listAccounts() {
        List<com.dev.challenge.domain.Account> accounts = accountRepository.findAll();
        List<Account> accountDtos = accounts.stream().map(AccountController::mapAccount).collect(Collectors.toList());
        return new ResponseEntity<>(accountDtos, HttpStatus.OK);
    }

    @GetMapping("/account/{accid}")
    public ResponseEntity<Account> accountById(@PathVariable("accid") String accountNumber) {
        com.dev.challenge.domain.Account account = accountRepository.findByAccountNumber(accountNumber);
        if(account==null) {
            throw new HttpException("Account No.: "+accountNumber+" is not available in the system.",400, HttpStatus.BAD_REQUEST);
        }
        Account accountDto = AccountController.mapAccount(account);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    private static Account mapAccount(com.dev.challenge.domain.Account accountDomain) {
        Account account = new Account(accountDomain.getAccountNumber(), accountDomain.getBalance());
        account.setAccountHolderName(accountDomain.getAccountHolderName());
        return account;
    }

}

package com.dev.challenge.service.impl;


import com.dev.challenge.dto.TransferMoney;
import com.dev.challenge.exception.BaseException;
import com.dev.challenge.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
@RunWith(SpringJUnit4ClassRunner.class)
public class AmountTransferServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AmountTransferServiceImpl amountTransferServiceImpl;

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void amountTransferTest_transferAmount0() {
        TransferMoney transferMoney =new TransferMoney ();
        transferMoney.setAmount(0);
        transferMoney.setFromAccount("abcd");
        transferMoney.setToAccount("xyz");

        try{
            amountTransferServiceImpl. amountTransfer(transferMoney);
        }catch(BaseException ex){
            assertEquals("Amount must be greater than 0", ex.getMessage());
            assertEquals(Integer.valueOf(400), ex.getCode());
        }
    }

    @Test
    public void amountTransferTest_fromAmountNotNull() {
        TransferMoney transferMoney =new TransferMoney ();
        transferMoney.setAmount(0);
        transferMoney.setFromAccount(null);
        transferMoney.setToAccount("xyz");
        try{
            amountTransferServiceImpl. amountTransfer(transferMoney);
        }catch(BaseException ex){
            assertEquals("From Account Number can not be null.", ex.getMessage());
            assertEquals(Integer.valueOf(400), ex.getCode());
        }
    }

    @Test
    public void amountTransferTest_toAmountNotNull() {
        TransferMoney transferMoney =new TransferMoney ();
        transferMoney.setAmount(0);
        transferMoney.setFromAccount("abc");
        transferMoney.setToAccount(null);
        try{
            amountTransferServiceImpl. amountTransfer(transferMoney);
        }catch(BaseException ex){
            assertEquals("To Account Number can not be null.", ex.getMessage());
            assertEquals(Integer.valueOf(400), ex.getCode());
        }
    }

}
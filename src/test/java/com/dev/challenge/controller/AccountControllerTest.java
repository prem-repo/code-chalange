package com.dev.challenge.controller;

import com.dev.challenge.service.AmountTransferService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AmountTransferService amountTransferService;

    private MockMvc mvc;
    String transferMoneyJson = "{\n" +
            "\t\"fromAccount\":\"SBI34568\",\n" +
            "\t\"toAccount\":\"SBI10003\",\n" +
            "\t\"amount\":100\n" +
            "\t\n" +
            "}";

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    public void transferAmountBetweenAccountsTest() throws Exception {

        this.mvc.perform(post("/transfermoney").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(transferMoneyJson)
        ).
                andExpect(status().isOk());
    }


}

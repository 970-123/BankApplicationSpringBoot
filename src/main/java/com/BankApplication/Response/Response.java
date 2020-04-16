package com.BankApplication.Response;
import com.BankApplication.Entity.Account;
import com.BankApplication.Entity.CreditCard;
import com.BankApplication.Entity.Customer;
import com.BankApplication.Entity.DebitCard;

import java.util.List;

public class Response {
    public List<Customer> responseList;
    public List<Account>  accountResponseList;
    public List<DebitCard> debitCardList;
    public List<CreditCard> creditCardList;
}



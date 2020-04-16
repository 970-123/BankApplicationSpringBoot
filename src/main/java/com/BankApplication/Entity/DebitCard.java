package com.BankApplication.Entity;

import com.BankApplication.common.KeyedEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class DebitCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private Long cardNo;
    private int cvv;
    private String expiryDate;
    private String name;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private Double balance;

    public  DebitCard(){}

    public DebitCard(Long cardNo, int cvv, String expiryDate, String name, AccountType accountType, Double balance) {
        this.cardNo = cardNo;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
        this.name = name;
        this.accountType = accountType;
        this.balance = balance;
    }

    public Long getCardNo() {
        return cardNo;
    }

    public void setCardNo(Long cardNo) {
        this.cardNo = cardNo;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}

package com.BankApplication.Model;

import com.BankApplication.common.KeyedEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class AccountResponse extends KeyedEntity<Long> {

    // TODO: move this to a package named entity...

    private Long accountNo;
    private boolean status;
    @Enumerated(EnumType.STRING)
    private AccountTypeResponse accountTypeResponse;
    private String nominee;
    private Double balance;
    private String branch;

    public Long getAccountNo() {
        return accountNo;
    }
    public void setAccountNo(Long accountNo) {
        this.accountNo = accountNo;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public AccountTypeResponse getAccountTypeResponse() {
        return accountTypeResponse;
    }
    public void setAccountTypeResponse(AccountTypeResponse accountTypeResponse) {
        this.accountTypeResponse = accountTypeResponse; }
    public String getNominee() {
        return nominee;
    }
    public void setNominee(String nominee) {
        this.nominee = nominee;
    }
    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    public String getBranch() {
        return branch;
    }
    public void setBranch(String branch) {
        this.branch = branch;
    }
}

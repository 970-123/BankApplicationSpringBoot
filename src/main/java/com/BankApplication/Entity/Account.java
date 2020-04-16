package com.BankApplication.Entity;
import lombok.*;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
public class Account {

    // TODO: move this to a package named entity...
    @Id
    private Long id;

    private Long accountNo;

    private boolean status;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private String nominee;

    private Double balance;

    private String branch;

//    public Account(){}

//    public Account(Long id, Long accountNo, boolean status, AccountType accountType, String nominee, Double balance, String branch) {
//        this.id = id;
//        this.accountNo = accountNo;
//        this.status = status;
//        this.accountType = accountType;
//        this.nominee = nominee;
//        this.balance = balance;
//        this.branch = branch;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

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

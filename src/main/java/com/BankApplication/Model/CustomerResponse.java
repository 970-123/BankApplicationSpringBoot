package com.BankApplication.Model;
import com.BankApplication.Entity.Account;
import com.BankApplication.common.KeyedEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;


public class CustomerResponse extends KeyedEntity<Long> {

    @Embedded
    private AddressResponse addressResponse;
    private AccountResponse accountResponseId;
    private String customerName;
    private String customerEmail;
    private Long customerPhoneNo;
    private String fatherName;
    private String panNo;
    private Long aadharNo;

    @Enumerated(EnumType.STRING)
    private AccountTypeResponse accountTypeResponse;
    private Double annualIncome;

    public AddressResponse getAddress() {
        return addressResponse;
    }
    public void setAddress(AddressResponse addressResponse) {
        this.addressResponse = addressResponse;
    }
    public AccountResponse getAccountResponseId() {
        return accountResponseId;
    }
    public void setAccountResponseId(AccountResponse accountResponseId) {
        this.accountResponseId = accountResponseId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCustomerEmail() {
        return customerEmail;
    }
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    public Long getCustomerPhoneNo() {
        return customerPhoneNo;
    }
    public void setCustomerPhoneNo(Long customerPhoneNo) {
        this.customerPhoneNo = customerPhoneNo;
    }
    public String getFatherName() {
        return fatherName;
    }
    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }
    public String getPanNo() {
        return panNo;
    }
    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }
    public Long getAadharNo() {
        return aadharNo;
    }
    public void setAadharNo(Long aadharNo) {
        this.aadharNo = aadharNo;
    }
    public Double getAnnualIncome() {
        return annualIncome;
    }
    public void setAnnualIncome(Double annualIncome) {
        this.annualIncome = annualIncome;
    }
}

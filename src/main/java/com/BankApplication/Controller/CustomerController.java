package com.BankApplication.Controller;

import com.BankApplication.Entity.Customer;
import com.BankApplication.Response.CustomerResponseMethod;
import com.BankApplication.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/createAccount")
    public ResponseEntity<CustomerResponseMethod> createAccount(@RequestBody Customer customerAccount){
        return customerService.createAccount(customerAccount);
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<CustomerResponseMethod> getAll(){
        return customerService.getAllDetails();
    }



}

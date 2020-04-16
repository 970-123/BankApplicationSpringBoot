package com.BankApplication.Service;

import com.BankApplication.Entity.Account;
import com.BankApplication.Entity.Customer;
import com.BankApplication.Model.CustomerResponse;
import com.BankApplication.Repository.AccountRepository;
import com.BankApplication.Repository.CustomerRepository;
import com.BankApplication.Response.CustomerResponseMethod;
import com.BankApplication.Response.Response;
import com.BankApplication.Validations.ValidationAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ValidationAccount validationAccount;

    @Autowired
    private AccountRepository accountRepository;

    public ResponseEntity<CustomerResponseMethod> createAccount(Customer customerAccount) {
        try {
            Response response = new Response();
            if (!validationAccount.validateDetails(customerAccount)) {
                return ResponseEntity.status(HttpStatus.OK).body(new CustomerResponseMethod(404, "You can not create account", new Response()));
            } else {
                customerRepository.save(customerAccount);
                return ResponseEntity.status(HttpStatus.OK).body(new CustomerResponseMethod(200, "Account created successfully", new Response()));
            }
        }
          catch (Exception e){
            return ResponseEntity.status(HttpStatus.OK).body(new CustomerResponseMethod(404, "Not Able to fetch details", new Response()));
        }

    }

    public ResponseEntity<CustomerResponseMethod> getAllDetails() {
        try {
            Response response = new Response();
            Iterable<Customer> customersList =   customerRepository.findAll();
            response.responseList = (List<Customer>) customersList;

            if(response.responseList == null)
                throw new NullPointerException();

            boolean status = false;
            for(Customer customer : customersList){
                status = false;
                if(customer.getAccountId()!=null){
                    status = true;
                }
            }
            if(status)
                return ResponseEntity.status(HttpStatus.OK).body(new CustomerResponseMethod(200, "Verified Successfully", response));
            else
              return ResponseEntity.status(HttpStatus.OK).body(new CustomerResponseMethod(404, "Account did not verified", new Response()));
         }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.OK).body(new CustomerResponseMethod(404, "Not Able to fetch details", new Response()));
        }
    }
}

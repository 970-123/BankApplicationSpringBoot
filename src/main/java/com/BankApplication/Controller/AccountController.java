package com.BankApplication.Controller;
import com.BankApplication.Entity.Account;
import com.BankApplication.Response.CustomerResponseMethod;
import com.BankApplication.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/verify/{id}")
    public ResponseEntity<CustomerResponseMethod> verify(@PathVariable("id") Long Id){
        return accountService.verify(Id);
    }

    @GetMapping(value = "/getAllAccount")
    public ResponseEntity<CustomerResponseMethod> getAllAccount(){
        return accountService.getAllAccount();
    }

    @PostMapping(value = "/depositMoney/{accountNo}/{money}")
    public ResponseEntity<CustomerResponseMethod> depositCash(@PathVariable("accountNo") Long accountNo, @PathVariable("money") Double money){
        return accountService.depositCash(accountNo,money);
    }

    @PostMapping(value = "/withdrawMoney/{accountNo}/{amount}")
    public ResponseEntity<CustomerResponseMethod> withDrawMoney(@PathVariable("accountNo") Long accountNo, @PathVariable("amount") Double amount){
        return accountService.withdrawMoney(accountNo,amount);
    }

    @PostMapping(value = "/depositMoneyToAccount/{accountNoToTransfer}/{accountNoToDeposit}/{money}")
    public ResponseEntity<CustomerResponseMethod> depositMoneyToOtherAccount(@PathVariable("accountNoToTransfer") Long accountNoToTransfer,
           @PathVariable("accountNoToDeposit") Long accountNoToDeposit,@PathVariable("money") Double money){
        return accountService.depositCashToAccount(accountNoToTransfer,accountNoToDeposit,money);
    }

    @PostMapping(value = "/generateDebitCard/{accountNo}")
        public ResponseEntity<CustomerResponseMethod> generateDebitCard(@PathVariable("accountNo") Long accountNo){
            return accountService.generateDebitCard(accountNo);
        }

     @GetMapping(value = "/getDebitCard")
        public ResponseEntity<CustomerResponseMethod> getDebitCard(){
            return accountService.getDebitCard();
     }

     @PostMapping(value = "/generateCreditCard/{accountNo}")
        public ResponseEntity<CustomerResponseMethod> generateCreditCard(@PathVariable("accountNo") Long accountNo){
            return accountService.generateCreditCard(accountNo);
     }




}

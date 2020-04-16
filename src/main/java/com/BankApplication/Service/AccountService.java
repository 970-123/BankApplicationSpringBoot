package com.BankApplication.Service;

import com.BankApplication.Entity.Account;
import com.BankApplication.Entity.CreditCard;
import com.BankApplication.Entity.Customer;
import com.BankApplication.Entity.DebitCard;
import com.BankApplication.Repository.AccountRepository;
import com.BankApplication.Repository.CreditCardRepository;
import com.BankApplication.Repository.CustomerRepository;
import com.BankApplication.Repository.DebitCardRepository;
import com.BankApplication.Response.CustomerResponseMethod;
import com.BankApplication.Response.Response;
import com.BankApplication.Validations.GenerateAccountNo;
import com.BankApplication.Validations.ValidationAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private GenerateAccountNo generateAccountNo;

    @Autowired
    private ValidationAccount validationAccount;

    @Autowired
    private DebitCardRepository debitCardRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;


    public ResponseEntity<CustomerResponseMethod> verify(Long id) {
        Optional<Customer> idVerify = customerRepository.findById(id);
        if (idVerify.isPresent()) {
            Customer customerItems = idVerify.get();
                Account verifiedAccount = new Account();
                verifiedAccount.setId(customerItems.getId());
                verifiedAccount.setAccountType(customerItems.getAccountType());
                verifiedAccount.setAccountNo(generateAccountNo.genAccNo());
                verifiedAccount.setStatus(true);
                verifiedAccount.setBalance(1000.0);

                accountRepository.save(verifiedAccount);

                customerItems.setAccountId(verifiedAccount);
                customerRepository.save(customerItems);

            return ResponseEntity.status(HttpStatus.OK).body(new CustomerResponseMethod(200, "Verify your Account successfully", new Response()));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new CustomerResponseMethod(500, "Could not Verify your account", new Response()));
        }

    }


    public ResponseEntity<CustomerResponseMethod> depositCash(Long accountNo,Double money) {
        Optional<Account> accountList = accountRepository.findByaccountNo(accountNo);
        if(accountList.isPresent()) {
            Account account = accountList.get();
            account.setBalance(account.getBalance() + money);
            accountRepository.save(account);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomerResponseMethod(200, "Cash Deposit Successfully", new Response()));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new CustomerResponseMethod(500, "Could not deposit in account", new Response()));
        }
    }


    public ResponseEntity<CustomerResponseMethod> getAllAccount() {
        try {
            Response response = new Response();
            response.accountResponseList =    accountRepository.findAll();
//            if(response.responseList == null)
//                throw new NullPointerException();
            return  ResponseEntity.status(HttpStatus.OK).body(new CustomerResponseMethod(200, "OK", response));
        }
        catch (Exception e){
            System.out.println("exception is "+ e);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomerResponseMethod(404, "Not Able to fetch details", new Response()));
         }
      }

    public ResponseEntity<CustomerResponseMethod> depositCashToAccount(Long accountNoToTransfer, Long accountNoToDeposit, Double money) {
        Optional<Account> accountList = accountRepository.findByaccountNo(accountNoToTransfer);
        boolean status = false;
        if (accountList.isPresent()) {
            Optional<Account> accountListToDeposit = accountRepository.findByaccountNo(accountNoToDeposit);
            if (accountListToDeposit.isPresent()) {
                Account accountListing = accountList.get();
                Account account = accountListToDeposit.get();

                if (money < accountListing.getBalance()) {
                    accountListing.setBalance(accountListing.getBalance() - money);
                    account.setBalance(account.getBalance() + money);
                    accountRepository.save(account);
                    accountRepository.save(accountListing);
                    status = true;
                } else if (money > accountListing.getBalance()) {
                    status = false;
                }
            }
            else
                return ResponseEntity.status(HttpStatus.OK).body(new CustomerResponseMethod(500, "Could not transfer amount to account", new Response()));
        }
            if(status)
                return ResponseEntity.status(HttpStatus.OK).body(new CustomerResponseMethod(200, "Transfer Amount Successfully", new Response()));
         else {
            return ResponseEntity.status(HttpStatus.OK).body(new CustomerResponseMethod(500, "Could not transfer amount to account", new Response()));
        }
    }

    public ResponseEntity<CustomerResponseMethod> withdrawMoney(Long accountNo, Double amount) {
        Optional<Account> accountList = accountRepository.findByaccountNo(accountNo);
        boolean status = false;
        Double minBalance = 1000.0;
        if(accountList.isPresent()) {
            Account account = accountList.get();
            if (amount < account.getBalance() && account.getBalance() > minBalance) {
                account.setBalance(account.getBalance() - amount);
                accountRepository.save(account);
                status = true;
            } else if (amount > account.getBalance() || account.getBalance() < minBalance) {
                status = false;
            }
        }
        if(status)
            return ResponseEntity.status(HttpStatus.OK).body(new CustomerResponseMethod(200, "Amount Withdrawn Successfully", new Response()));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new CustomerResponseMethod(500, "Insufficient funds", new Response()));

    }

    public ResponseEntity<CustomerResponseMethod> generateDebitCard(Long accountNo) {
        Optional<Account> accountList = accountRepository.findByaccountNo(accountNo);
        Optional<Customer> customerOptionalList = customerRepository.findByaccountId(accountList);
        Optional<DebitCard> debitCardOptional = debitCardRepository.findByname(customerOptionalList.get().getCustomerName());
        boolean status = false;
        if(debitCardOptional.isPresent()){
            Customer customer = customerOptionalList.get();
            Account account = accountList.get();
            DebitCard debitCard = debitCardOptional.get();
            debitCard.setName(customer.getCustomerName());
            debitCard.setBalance(account.getBalance());
            debitCard.setAccountType(account.getAccountType());
            status = true;
            debitCardRepository.save(debitCard);
        }
        else if(customerOptionalList.isPresent() || accountList.isPresent()) {
            Customer customer = customerOptionalList.get();
            Account account = accountList.get();
            DebitCard debitCard = new DebitCard();
            debitCard.setCardNo(validationAccount.validateCardNo());
            debitCard.setCvv(validationAccount.validateCvv());
            debitCard.setExpiryDate(validationAccount.validateDate());
            debitCard.setName(customer.getCustomerName());
            debitCard.setBalance(account.getBalance());
            debitCard.setAccountType(account.getAccountType());
            status = true;
            debitCardRepository.save(debitCard);

        }

        if(status)
            return ResponseEntity.status(HttpStatus.OK).body(new CustomerResponseMethod(200, "Debit Card created Successfully", new Response()));
         else
            return ResponseEntity.status(HttpStatus.OK).body(new CustomerResponseMethod(500, "Could not created card", new Response()));

    }

    public ResponseEntity<CustomerResponseMethod> getDebitCard() {
        try {
            Response response = new Response();
            response.debitCardList = debitCardRepository.findAll();
            return  ResponseEntity.status(HttpStatus.OK).body(new CustomerResponseMethod(200, "Created Debit Card Successfully", response));
        }
        catch (Exception e){
            System.out.println("exception is "+ e);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomerResponseMethod(404, "Not Able to fetch details", new Response()));
        }
    }

    public ResponseEntity<CustomerResponseMethod> generateCreditCard(Long accountNo) {
        Optional<Account> accountList = accountRepository.findByaccountNo(accountNo);
        Optional<Customer> customerOptionalList = customerRepository.findByaccountId(accountList);
        System.out.println(customerOptionalList);
        Double Income = customerOptionalList.get().getAnnualIncome();

//        boolean status = false;
//        Double limit = 0.0 ;
//        if( !StringUtils.isEmpty(Income)){
//            if(Income <= 300000.0 )
//                limit = 125000.0;
//            else if(Income > 300000.0 && Income <= 500000.0)
//                limit = 200000.0;
//            else if(Income > 500000.0 && Income <= 1000000.0)
//                limit = 300000.0;
//            else
//                limit = 500000.0;
//
//        }
        if(accountList.isPresent() || customerOptionalList.isPresent()) {
            Customer customer = customerOptionalList.get();
            Account account = accountList.get();
            CreditCard creditCard = new CreditCard();
            creditCard.setCardNo(validationAccount.validateCardNo());
            creditCard.setCvv(validationAccount.validateCvv());
            creditCard.setExpiryDate(validationAccount.validateDate());
            creditCard.setAccountType(account.getAccountType());
            creditCard.setName(customer.getCustomerName());
//            creditCard.setLimit(limit);
//            status = true;
            System.out.println(creditCard.getCardNo());
            System.out.println(creditCard.getCvv());
            System.out.println(creditCard.getExpiryDate());
            System.out.println(creditCard.getAccountType());
            System.out.println(creditCard.getName());
            System.out.println(creditCard.getLimit());
//            creditCardRepository.save(creditCard);

//            Response response = new Response();
//            response.creditCardList = creditCardRepository.findAll();

            return ResponseEntity.status(HttpStatus.OK).body(new CustomerResponseMethod(200, "Credit Card created Successfully", new Response()));
        } else
            return ResponseEntity.status(HttpStatus.OK).body(new CustomerResponseMethod(500, "Could not created card", new Response()));
        }

}

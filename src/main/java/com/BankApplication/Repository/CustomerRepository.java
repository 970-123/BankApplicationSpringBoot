package com.BankApplication.Repository;
import com.BankApplication.Entity.Account;
import com.BankApplication.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
//    @Query("SELECT u FROM CUSTOMER u WHERE u.accountNo = ?1")

    Optional<Customer> findByaccountId(Optional<Account> accountList);
}

package com.BankApplication.Repository;
import com.BankApplication.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT u FROM Account u WHERE u.accountNo = ?1")
    Optional<Account> findByaccountNo(Long accountNo);

}

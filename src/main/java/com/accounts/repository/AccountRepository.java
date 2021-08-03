package com.accounts.repository;

import com.accounts.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT a FROM Account a WHERE a.customer.id = :customerId")
    List<Account> findAllByCustomerId(@Param("customerId") long customerId);
}

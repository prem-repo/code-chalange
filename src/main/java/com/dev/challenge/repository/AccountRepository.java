package com.dev.challenge.repository;

import com.dev.challenge.domain.Account;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public interface AccountRepository extends JpaRepository<Account, Long> {
     Account findByAccountNumber(String accNumber);
}
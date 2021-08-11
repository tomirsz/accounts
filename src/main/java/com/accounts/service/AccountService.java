package com.accounts.service;

import com.accounts.mapper.AccountMapper;
import com.accounts.model.dto.AccountDto;
import com.accounts.repository.AccountRepository;
import com.kodilla.commons.NewTransaction;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public List<AccountDto> fetchCustomerAccounts(long customerId) {
        return accountRepository.findAllByCustomerId(customerId).stream()
                .map(accountMapper::mapToAccountDto)
                .collect(Collectors.toList());
    }

    public Optional<BigDecimal> fetchFounds(String nrb) {
        return accountRepository.fetchFounds(nrb);
    }

    public void handleNewTransaction(NewTransaction newTransaction) {
        accountRepository.fetchAccountByAccount(newTransaction.getSenderAccount())
                .ifPresent(a -> {
                    a.setFounds(a.getFounds().subtract(newTransaction.getAmount()));
                    accountRepository.save(a);
                    log.info("Updated sender founds");
                });
        accountRepository.fetchAccountByAccount(newTransaction.getRecipientAccount())
                .ifPresent(a -> {
                    a.setFounds(a.getFounds().add(newTransaction.getAmount()));
                    accountRepository.save(a);
                    log.info("Updated recipient founds");
                });
    }
}

package com.accounts.service;

import com.accounts.mapper.AccountMapper;
import com.accounts.model.dto.AccountDto;
import com.accounts.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
}

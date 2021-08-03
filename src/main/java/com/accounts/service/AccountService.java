package com.accounts.service;

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

    public List<AccountDto> fetchCustomerAccounts(long customerId) {
        return accountRepository.findAllByCustomerId(customerId).stream()
                .map(a -> AccountDto.builder()
                        .id(a.getId())
                        .nrb(a.getNrb())
                        .currency(a.getCurrency())
                        .availableFunds(a.getFounds())
                        .build())
                .collect(Collectors.toList());
    }
}

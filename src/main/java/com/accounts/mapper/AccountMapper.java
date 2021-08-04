package com.accounts.mapper;

import com.accounts.model.dto.AccountDto;
import com.accounts.model.entity.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountMapper {

    public AccountDto mapToAccountDto(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .nrb(account.getNrb())
                .currency(account.getCurrency())
                .availableFunds(account.getFounds())
                .build();
    }
}

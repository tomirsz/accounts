package com.accounts.controller;

import com.accounts.model.response.GetAccountsResponse;
import com.accounts.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/v1")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/accounts")
    public ResponseEntity<GetAccountsResponse> getAccounts(@RequestParam(name = "customerId") long customerId) {
        return ResponseEntity.ok(GetAccountsResponse.of(accountService.fetchCustomerAccounts(customerId)));
    }
}

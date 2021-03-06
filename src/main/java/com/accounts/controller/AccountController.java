package com.accounts.controller;

import com.accounts.model.response.GetAccountsResponse;
import com.accounts.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Slf4j
@RefreshScope
@RestController("/v1")
@RequiredArgsConstructor
public class AccountController {

    @Value("${application.allow-get-accounts}")
    private boolean allowGetAccounts;

    private final AccountService accountService;

    @GetMapping("/accounts")
    public ResponseEntity<GetAccountsResponse> getAccounts(@RequestParam(name = "customerId") long customerId) {
        log.info("Get accounts for customerId: {}", customerId);
        if(!allowGetAccounts) {
            log.info("Getting accounts is disabled");
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Getting account is disabled");
        }
        return ResponseEntity.ok(GetAccountsResponse.of(accountService.fetchCustomerAccounts(customerId)));
    }

    @GetMapping("/account/fetch-founds")
    public ResponseEntity<BigDecimal> fetchFounds(@RequestParam(name="nrb") String nrb) {
        return accountService.fetchFounds(nrb)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }
}

package com.accounts.service;

import com.kodilla.commons.NewTransaction;
import com.kodilla.commons.NewTransactionMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class NewTransactionListener {

    private final AccountService accountService;

    @KafkaListener(topics = "new-transaction")
    public void consume(@Payload NewTransactionMessage newTransactionMessage) {
        log.info("Consumed newTransactionMessage: {}", newTransactionMessage);
        accountService.handleNewTransaction(newTransactionMessage.getNewTransaction());
    }
}

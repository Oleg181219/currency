package com.test.currency.controllers;

import com.test.currency.api.request.ExchangeRequest;
import com.test.currency.api.response.ErrorResponse;
import com.test.currency.api.response.Response;
import com.test.currency.model.ENUM.Currencies;
import com.test.currency.services.TransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

@RestController
@Tag(name="Transaction Controller", description="Конвертация валют")
public class TransactionController {

    private final TransactionService transactionService;


    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(value = "/exchange")
    public ResponseEntity<Response> getTransactionResult(@RequestBody ExchangeRequest exchangeRequest) throws IOException {

        if (Arrays.stream(Currencies.values())
                .noneMatch(t -> t.name()
                        .equals(exchangeRequest.getSourceName().toUpperCase())) ||
                Arrays.stream(Currencies.values())
                        .noneMatch(t -> t.name()
                                .equals(exchangeRequest.getTargetName().toUpperCase()))) {
            return ResponseEntity.badRequest().body(new ErrorResponse("wrong currencies"));
        }
        if(exchangeRequest.getUserId() == null || exchangeRequest.getSourceSum() == null){
            return ResponseEntity.badRequest().body(new ErrorResponse("empty sum or user ID"));
        }
        if(exchangeRequest.getUserId() < 0 ||
                exchangeRequest.getSourceSum().compareTo(new BigDecimal("0")) <= 0){
            return ResponseEntity.badRequest().body(new ErrorResponse("sum or user ID <= 0"));
        }


        return transactionService.getTransactionValue(exchangeRequest);
    }
}

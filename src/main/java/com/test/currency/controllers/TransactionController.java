package com.test.currency.controllers;

import com.test.currency.api.request.ExchangeRequest;
import com.test.currency.api.response.ErrorResponse;
import com.test.currency.api.response.Response;
import com.test.currency.services.TransactionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class TransactionController {

    private TransactionService transactionService;


    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @ApiOperation(value = "Проведение операции по переводу")
    @PostMapping(name = "/exchange",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getTransactionResult(@Valid @RequestBody ExchangeRequest exchangeRequest,
                                                         BindingResult error) {
        System.out.println(error);
        if (error != null) {
            return ResponseEntity.badRequest().body(new ErrorResponse("веденые данные не верны"));
        }
        return transactionService.getTransactialValue(exchangeRequest);
    }
}

package com.test.currency.controllers;

import com.test.currency.api.request.ExchangeRequest;
import com.test.currency.api.response.ErrorResponse;
import com.test.currency.api.response.Response;
import com.test.currency.services.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
@Api
public class GeneralController {

    private final TransactionService transactionService;

    public GeneralController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @GetMapping("/")
    public String index() {
        return "forward:/swagger-ui.html";
    }

    @ApiOperation(value = "Проведение операции по переводу")
    @GetMapping("/exchange")
    public ResponseEntity<Response> getTransactionResult(@Valid @RequestBody ExchangeRequest exchangeRequest,
                                                         BindingResult error){
        if(error != null){
            return ResponseEntity.badRequest().body(new ErrorResponse("веденые данные не верны"));
        }
        return transactionService.getTransactialValue(exchangeRequest);
    }
}

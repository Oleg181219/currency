package com.test.currency.services;

import com.test.currency.api.request.ExchangeRequest;
import com.test.currency.api.response.Response;
import com.test.currency.api.response.ResponseData;
import com.test.currency.model.Transaction;
import com.test.currency.repositories.TransactionRepositories;
import com.test.currency.utils.LiveResponseCurrency;
import io.swagger.v3.oas.annotations.Operation;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;


@Service
public class TransactionService {


    private final LiveResponseCurrency liveResponseCurrency;
    private final TransactionRepositories transactionRepositories;

    public TransactionService(LiveResponseCurrency liveResponseCurrency,
                              TransactionRepositories transactionRepositories) {
        this.liveResponseCurrency = liveResponseCurrency;
        this.transactionRepositories = transactionRepositories;
    }

    @Operation(
            summary = "Конвертация валют",
            description = "Конвертирует валюту, с указанием суммы конертации исходной валюты")
    public ResponseEntity<Response> getTransactionValue(ExchangeRequest request) {


        final var ACCESS_KEY = "f5b338e9b91ee6e8fd35c6c636cc9172";
        final var BASE_URL = "http://api.currencylayer.com/live?access_key=";
        final var FROM = "&currencies=";
        final var TO = ",";
        final var QUOTES = "quotes";

        var transaction = new Transaction();
        var requestToBase = BASE_URL + ACCESS_KEY + FROM + request.getSourceName().toUpperCase() + TO + request.getTargetName().toUpperCase();

        JSONObject object = liveResponseCurrency.sendLiveRequest(requestToBase);

        var source = new BigDecimal(object.get(QUOTES).toString()
                .substring(object.get(QUOTES).toString().indexOf(":") + 1,
                        object.get(QUOTES).toString().indexOf(",")));


        var target = new BigDecimal(object.get(QUOTES).toString()
                .substring(object.get(QUOTES).toString().lastIndexOf(":") + 1,
                        object.get(QUOTES).toString().lastIndexOf("}")));


        var exchCourse = target.divide(source, 6, RoundingMode.HALF_DOWN);


        var result = exchCourse
                .multiply(request.getSourceSum())
                .setScale(2, RoundingMode.HALF_DOWN);


        transaction.setSourceName(request.getSourceName());
        transaction.setTargetName(request.getTargetName());
        transaction.setUserId(request.getUserId());
        transaction.setSourceSumm(request.getSourceSum());
        transaction.setTargetSumm(result);


        var transactionId = transactionRepositories.save(transaction).getId();

        return ResponseEntity.ok(new ResponseData(transactionId, result));

    }
}

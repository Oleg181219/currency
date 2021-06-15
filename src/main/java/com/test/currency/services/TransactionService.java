package com.test.currency.services;

import com.test.currency.api.request.ExchangeRequest;
import com.test.currency.api.response.Response;
import com.test.currency.api.response.ResponseData;
import com.test.currency.model.Transaction;
import com.test.currency.repositories.CourseRepositories;
import com.test.currency.repositories.TransactionRepositories;
import com.test.currency.utils.LiveResponseCurrency;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.RoundingMode;


@Service
public class TransactionService {

    private final Logger logger = LogManager.getLogger(TransactionService.class);


    private final LiveResponseCurrency liveResponseCurrency;
    private final TransactionRepositories transactionRepositories;
    private final CourseRepositories courseRepositories;

    public TransactionService(LiveResponseCurrency liveResponseCurrency,
                              TransactionRepositories transactionRepositories,
                              CourseRepositories courseRepositories) {
        this.liveResponseCurrency = liveResponseCurrency;
        this.transactionRepositories = transactionRepositories;
        this.courseRepositories = courseRepositories;
    }

    @Operation(summary = "Конвертация валют",
            description = "Конвертирует валюту, с указанием суммы конертации исходной валюты")
    public ResponseEntity<Response> getTransactionValue(ExchangeRequest request) throws IOException {
        ResponseData responseData = new ResponseData();
        var userId = request.getUserId();
        var sourceName = "USD" + request.getSourceName().toUpperCase();
        var targetName = "USD" + request.getTargetName().toUpperCase();
        var sourceSum = request.getSourceSum();

        var dataFromBd = courseRepositories.findAll();
        var source = dataFromBd
                .stream()
                .filter(a -> a.getCurrencyName().equals(sourceName))
                .findAny();


        var target = dataFromBd
                .stream()
                .filter(a -> a.getCurrencyName().equals(targetName))
                .findAny();

        if (target.isPresent() && source.isPresent()) {

            var resultSum = target.get().getCourse()
                    .divide(source.get().getCourse(), 6, RoundingMode.HALF_EVEN);

            responseData.setResultSum(resultSum.multiply(sourceSum).setScale(2, RoundingMode.HALF_DOWN));
            Transaction transaction = new Transaction();
            transaction.setUserId(userId);
            transaction.setSourceName(sourceName.substring(3));
            transaction.setSourceSumm(sourceSum);
            transaction.setTargetName(targetName.substring(3));
            transaction.setTargetSumm(responseData.getResultSum());
            responseData.setRequestId(transactionRepositories.save(transaction).getId());
        }
        return ResponseEntity.ok(responseData);

    }
}

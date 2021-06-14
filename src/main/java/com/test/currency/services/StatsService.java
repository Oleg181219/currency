package com.test.currency.services;

import com.test.currency.api.response.Response;
import com.test.currency.api.response.ResponseData;
import com.test.currency.model.Transaction;
import com.test.currency.repositories.TransactionRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatsService {

//              Примеры запросов:
//            • Пользователи, запросившие конвертацию больше 10 000 $ за один запрос.
//            • Пользователи, суммарный запрошенный объём которых больше 100 000 $.
//            • Рейтинг направлений конвертации валют по популярности.

    private final TransactionRepositories transactionRepositories;

    public StatsService(TransactionRepositories transactionRepositories) {
        this.transactionRepositories = transactionRepositories;
    }


    public ResponseEntity<Response> getMaxRequest(){

        List<Transaction> transactions = transactionRepositories.findAll();



        return ResponseEntity.ok(new ResponseData());
    }
}

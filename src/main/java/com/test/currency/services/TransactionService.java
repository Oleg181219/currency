package com.test.currency.services;

import com.test.currency.api.request.ExchangeRequest;
import com.test.currency.api.response.Response;
import com.test.currency.api.response.ResponseData;
import com.test.currency.utils.LiveResponseCurrency;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class TransactionService {

    private final String ACCESS_KEY = "f5b338e9b91ee6e8fd35c6c636cc9172";
    private final String BASE_URL = "https://api.currencylayer.com/convert?access_key=";
    private final String FROM = "&from=";
    private final String TO = "&to=";
    private final String AMOUNT = "&amount=";


    private final LiveResponseCurrency liveResponseCurrency;

    public TransactionService(LiveResponseCurrency liveResponseCurrency) {
        this.liveResponseCurrency = liveResponseCurrency;
    }

    public ResponseEntity<Response> getTransactialValue(ExchangeRequest exchangeRequest) {

//        https://api.currencylayer.com/convert?access_key=YOUR_ACCESS_KEY&from=USD&to=GBP&amount=10


        var from = exchangeRequest.getSourceName().toUpperCase();

        var exSum = exchangeRequest.getSourceSum().toString();
        var to = exchangeRequest.getTargetName().toUpperCase();

        var userId = exchangeRequest.getUserId();

        var requestToBase = BASE_URL + ACCESS_KEY + FROM + from + TO + to + AMOUNT + exSum;

        JSONObject object = liveResponseCurrency.sendLiveRequest(requestToBase);

        System.out.println(object);


        return ResponseEntity.ok(new ResponseData(object));

    }
}

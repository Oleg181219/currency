package com.test.currency.services;

import com.test.currency.api.request.ExchangeRequest;
import com.test.currency.api.response.Response;
import com.test.currency.api.response.ResponseData;
import com.test.currency.repositories.TransactionRepositories;
import com.test.currency.utils.LiveResponseCurrency;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class TransactionService {


    private final LiveResponseCurrency liveResponseCurrency;
    private final TransactionRepositories transactionRepositories;

    public TransactionService(LiveResponseCurrency liveResponseCurrency,
                              TransactionRepositories transactionRepositories) {
        this.liveResponseCurrency = liveResponseCurrency;
        this.transactionRepositories = transactionRepositories;
    }

    @Operation(summary = "Конвертация валют",
            description = "Конвертирует валюту, с указанием суммы конертации исходной валюты")
    public ResponseEntity<Response> getTransactionValue(ExchangeRequest request) throws IOException {




//        HashMap<String, BigDecimal> base = new HashMap<>();
//        var sss = Arrays.stream(Currencies.values()).collect(Collectors.toList());
//        StringBuilder ass = new StringBuilder();
//
//
//        for (Currencies currencies : sss) {
//            ass.append(currencies.name()).append(",");
//            base.put(currencies.name(), new BigDecimal("0"));
//        }
////        System.out.println(base.size());
////        System.out.println(sss.size());
////        base.forEach((key, value) -> System.out.println(key + " " + value));
//
//
//        ass.setLength(ass.length() - 1);
////        var transaction = new Transaction();
//        var requestToBase = BASE_URL + ACCESS_KEY + FROM + ass.toString();
//
//        var object = liveResponseCurrency.sendLiveRequest(requestToBase);
//
//        Map<String, Object> map = object.toMap();
////       1
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
////       2
//
//        System.out.println(object);
//        Currencylayer currencylayer = mapper.readValue(object.toString(), Currencylayer.class);
//        System.out.println(currencylayer.getQuotes());
//        var bbb = map.get(QUOTES);
//
//        System.out.println(map.get(QUOTES));

//
//        var www = object.get(QUOTES);
//        System.out.println(object.get(QUOTES));
//        JSONObject newq = (JSONObject) object.get(QUOTES);
//
//        var source = new BigDecimal(object.get(QUOTES).toString()
//                .substring(object.get(QUOTES).toString().indexOf(":") + 1,
//                        object.get(QUOTES).toString().indexOf(",")));
//
//
//        var target = new BigDecimal(object.get(QUOTES).toString()
//                .substring(object.get(QUOTES).toString().lastIndexOf(":") + 1,
//                        object.get(QUOTES).toString().lastIndexOf("}")));
//
//
//        var exchCourse = target.divide(source, 6, RoundingMode.HALF_DOWN);
//
//
//        var result = exchCourse
//                .multiply(request.getSourceSum())
//                .setScale(2, RoundingMode.HALF_DOWN);
//
//
//        transaction.setSourceName(request.getSourceName());
//        transaction.setTargetName(request.getTargetName());
//        transaction.setUserId(request.getUserId());
//        transaction.setSourceSumm(request.getSourceSum());
//        transaction.setTargetSumm(result);
//
//
//        var transactionId = transactionRepositories.save(transaction).getId();

        return ResponseEntity.ok(new ResponseData());

    }
}

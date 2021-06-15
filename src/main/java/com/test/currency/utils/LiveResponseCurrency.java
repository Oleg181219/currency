package com.test.currency.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.currency.model.ENUM.Currencies;
import com.test.currency.model.dto.Currencylayer;
import com.test.currency.model.dto.Quotes;
import com.test.currency.repositories.CourseRepositories;
import com.test.currency.services.TransactionService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;


@Component
@EnableScheduling
public class LiveResponseCurrency {
    private final Logger logger = LogManager.getLogger(TransactionService.class);
    @Value("${currency.ACCESS_KEY}")
    private String ACCESS_KEY;
    private final String BASE_URL = "http://api.currencylayer.com/live?access_key=";
    private final String FROM = "&currencies=";
    private final String TO = ",";
    private final String QUOTES = "quotes";
    private Currencylayer currencylayer = new Currencylayer();
    private CloseableHttpClient httpClient = HttpClients.createDefault();
    private JSONObject exchangeRates;

    @Autowired
    private final CourseRepositories courseRepositories;

    public LiveResponseCurrency(CourseRepositories courseRepositories) {
        this.courseRepositories = courseRepositories;
    }


    @Scheduled(cron = "0 0 12 * * ?")
    public void sendLiveRequest() {

        Runnable task = () -> {
            try {
                logger.warn("renew data from http://www.currencylayer.com/");
                var mapper = new ObjectMapper();
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                var currenciesList = Arrays.stream(Currencies.values()).collect(Collectors.toList());
                var builder = new StringBuilder();

                for (Currencies currencies : currenciesList) {
                    builder.append(currencies.name()).append(",");
                }

                builder.setLength(builder.length() - 1);
                var requestToBase = BASE_URL + ACCESS_KEY + FROM + builder.toString();
                var get = new HttpGet(requestToBase);

                try {
                    CloseableHttpResponse response = httpClient.execute(get);
                    HttpEntity entity = response.getEntity();
                    exchangeRates = new JSONObject(EntityUtils.toString(entity));
                    currencylayer = mapper.readValue(exchangeRates.toString(), Currencylayer.class);
                    response.close();
                    httpClient.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                ArrayList<Quotes> fromDb = courseRepositories.findAll();

                for (var i = 0; i < fromDb.size(); i++) {
                    int finalI = i;
                    currencylayer.getQuotes().forEach((key, value) -> {
                        if (fromDb.get(finalI).getCurrencyName().equals(key)) {
                            fromDb.get(finalI).setCourse(value);
                        }
                    });

                }
                courseRepositories.saveAll(fromDb);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(task);
        thread.start();

    }

    @PostConstruct
    private void getStartData(){
        sendLiveRequest();
    }
}
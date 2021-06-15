package com.test.currency.services;

import com.test.currency.api.response.Response;
import com.test.currency.api.response.ResponseData;
import com.test.currency.api.response.StatsResponse;
import com.test.currency.model.dto.DirectionDTO;
import com.test.currency.repositories.TransactionRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;

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


    public ResponseEntity<Response> getMaxTransfer(BigInteger value){



        return ResponseEntity.ok(new ResponseData());
    }

    public ResponseEntity<Response> getMaxVolume(BigInteger value){
/**
 * 1- все юзеры
 * 2- перебираем и считаем сумму каждого перевода в текущий курс usd
 */


        return ResponseEntity.ok(new ResponseData());
    }

    public ResponseEntity<Response> getPopularDirections(){
        var response = transactionRepositories.findAllPopular();
        StatsResponse statsResponse = new StatsResponse();

        ArrayList<DirectionDTO> directionDTOS= new ArrayList<>();
        response.forEach((entry)->{
            var directionDTO = new DirectionDTO();
            directionDTO.setFrom(entry.getSourceName());
            directionDTO.setTo(entry.getTargetName());
            directionDTOS.add(directionDTO);

        });

        return ResponseEntity.ok(new StatsResponse(directionDTOS));
    }
}

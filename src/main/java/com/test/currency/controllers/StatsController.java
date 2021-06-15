package com.test.currency.controllers;

import com.test.currency.api.response.ErrorResponse;
import com.test.currency.api.response.Response;
import com.test.currency.services.StatsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@Tag(name = "Stats Controller", description = "Получение статистики")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

//    Примеры запросов:
//            • Пользователи, запросившие конвертацию больше 10 000 $ за один запрос.
//            • Пользователи, суммарный запрошенный объём которых больше 100 000 $.
//            • Рейтинг направлений конвертации валют по популярности.


    @PostMapping("/stats/maxtransfer")
    public ResponseEntity<Response> getMaxTransfer(@RequestParam("value") BigInteger value) {
        if (value == null) {
            return ResponseEntity.badRequest().body(new ErrorResponse("empty value"));
        }
        if (value.compareTo(new BigInteger("0")) <= 0) {
            return ResponseEntity.badRequest().body(new ErrorResponse("value can't be zero or negative"));
        }
        return statsService.getMaxTransfer(value);
    }


    @PostMapping("/stats/maxvolume")
    public ResponseEntity<Response> getMaxVolume(@RequestParam("value") BigInteger value) {
        if (value == null) {
            return ResponseEntity.badRequest().body(new ErrorResponse("empty value"));
        }
        if (value.compareTo(new BigInteger("0")) <= 0) {
            return ResponseEntity.badRequest().body(new ErrorResponse("value can't be zero or negative"));
        }
        return statsService.getMaxVolume(value);
    }

    @PostMapping("/stats/populardirection")
    public ResponseEntity<Response> getPopularDirections() {
        return statsService.getPopularDirections();
    }

}

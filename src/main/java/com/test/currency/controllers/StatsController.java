package com.test.currency.controllers;

import com.test.currency.api.response.Response;
import com.test.currency.services.StatsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name="Stats Controller", description="Получение статистики")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

//    Примеры запросов:
//            • Пользователи, запросившие конвертацию больше 10 000 $ за один запрос.
//            • Пользователи, суммарный запрошенный объём которых больше 100 000 $.
//            • Рейтинг направлений конвертации валют по популярности.



    public ResponseEntity<Response> getMaxRequest(){
        return statsService.getMaxRequest();
    }




}

package com.test.currency.controllers;

import com.test.currency.api.request.ExchangeRequest;
import com.test.currency.api.response.ErrorResponse;
import com.test.currency.api.response.Response;
import com.test.currency.services.TransactionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Controller
public class GeneralController {





    @GetMapping("/")
    public String root() {
        return "redirect:/swagger-ui.html";
    }


}

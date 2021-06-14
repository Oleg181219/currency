package com.test.currency.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;

@Data
public class Currencylayer {


    private String terms;
    private boolean success;
    private String privacy;
    private String source;
    private Timestamp timestamp;
    private HashMap<String, BigDecimal> quotes;




}

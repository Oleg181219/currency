package com.test.currency.api.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRequest {

    @NotNull
    private Long userId;
    @NotNull
    private BigDecimal sourceSum;
    @NotNull
    private String sourceName;
    @NotNull
    private String targetName;
}

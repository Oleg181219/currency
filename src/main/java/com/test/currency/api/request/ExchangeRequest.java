package com.test.currency.api.request;


import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRequest {

    @NotNull
    @Schema(description = "ID пользователя", example = "10")
    @Size(min = 0, max = 2147483646, message = "данные неверны")
    @Parameter(description = "Id пользователя", required = true)
    private Long userId;

    @NotNull
    @Schema(description = "Сумма к конвертации", example = "Должна быть больше нуля")
    @Parameter(description = "Сумма к конвертации", required = true)
    @Size(min = 0, max = 2147483646, message = "данные неверны")
    private BigDecimal sourceSum;

    @NotNull
    @Schema(description = "Исходная валюта", example = "EUR")
    @Parameter(description = "Ticker валюты которую нужно конвертировать", required = true)
    @Size(min = 3,max = 3)
    private String sourceName;

    @NotNull
    @Schema(description = "Целевая валюта", example = "USD")
    @Parameter(description = "Ticker валюты в которую нужно конвертировать", required = true)
    @Size(min = 3,max = 3)
    private String targetName;
}

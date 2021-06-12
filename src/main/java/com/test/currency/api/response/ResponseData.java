package com.test.currency.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData implements Response{

    @Schema(description = "ID запроса", example = "1")
    private Long requestId;
    @Schema(description = "Результат конвертации валют", example = "11234.78")
    private BigDecimal resultSum;


}

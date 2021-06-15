package com.test.currency.api.response;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatsResponse implements Response{

    private List response;
}

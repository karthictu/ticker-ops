package com.karthic.stocks_bot.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class PriceUpdateRequest {
    private String tickerName;
    private Double buyPrice;
    private Double sellPrice;
    private Double stopLossPrice;
}

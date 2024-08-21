package com.karthic.stocks_bot.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.karthic.stocks_bot.constants.CommonConstants;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = CommonConstants.TICKERS)
@IdClass(TickerId.class)
@JsonInclude(value = Include.NON_NULL)
public class Ticker {
    @Id
    private String userId;
    @Id
    private String tickerId;
    private String tickerName;
    private Double buyPrice;
    private Double sellPrice;
    private Double stopLossPrice;
    // private Double buyForMargin;
    // private Double sellForMargin;
    // private Double stopLossMargin;

}

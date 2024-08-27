package com.karthic.stocks_bot.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.karthic.stocks_bot.constants.CommonConstants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(value = Include.NON_NULL)
@Entity
@Table(name = CommonConstants.TICKERS)
public class Ticker {
    @Id
    @Column(insertable = true, updatable = false, nullable = false)
    private String tickerId;
    @Column(insertable = true, updatable = false, nullable = false)
    private String userId;
    @Column(insertable = true, updatable = false, nullable = false)
    private String tickerName;
    @Column(insertable = true, updatable = true, nullable = false)
    private Double buyPrice;
    @Column(insertable = true, updatable = true, nullable = false)
    private Double sellPrice;
    @Column
    private Double stopLossPrice;
    // private Double buyForMargin;
    // private Double sellForMargin;
    // private Double stopLossMargin;

}

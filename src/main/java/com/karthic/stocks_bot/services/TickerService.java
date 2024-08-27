package com.karthic.stocks_bot.services;

import java.util.List;

import com.karthic.stocks_bot.entities.Ticker;
import com.karthic.stocks_bot.models.PriceUpdateRequest;

public interface TickerService {
    Ticker save(String userId, Ticker ticker);

    List<Ticker> findAll();

    Ticker findByIdAndUserId(String tickerId, String userId);

    List<Ticker> findTickersByUserId(String userId);

    void deleteForUserId(String tickerId, String userId);

    Ticker updatePrices(String userId, String tickerId, PriceUpdateRequest priceUpdateRequest);
}

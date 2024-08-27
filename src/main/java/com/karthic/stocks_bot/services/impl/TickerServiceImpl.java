package com.karthic.stocks_bot.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.karthic.stocks_bot.entities.Ticker;
import com.karthic.stocks_bot.models.PriceUpdateRequest;
import com.karthic.stocks_bot.repositories.TickerRepository;
import com.karthic.stocks_bot.services.TickerService;

@Service
public class TickerServiceImpl implements TickerService {

    @Autowired
    private TickerRepository tickerRepository;

    @Override
    public Ticker save(String userId, Ticker ticker) {
        ticker.setUserId(userId);
        return tickerRepository.save(ticker);
    }

    @Override
    public List<Ticker> findAll() {
        List<Ticker> results = new ArrayList<Ticker>();
        tickerRepository.findAll().forEach(ticker -> results.add(ticker));
        return results;
    }

    @Override
    public Ticker findByIdAndUserId(String tickerId, String userId) throws NoSuchElementException {
        return tickerRepository.findByTickerIdAndUserId(tickerId, userId).orElseThrow();
    }

    @Override
    public List<Ticker> findTickersByUserId(String userId) {
        return tickerRepository.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public void deleteForUserId(String tickerId, String userId) {
        tickerRepository.deleteByTickerIdAndUserId(tickerId, userId);
    }

    @Override
    public Ticker updatePrices(String userId, String tickerId, PriceUpdateRequest priceUpdateRequest) {
        return save(userId, Ticker.builder().tickerId(tickerId).tickerName(priceUpdateRequest.getTickerName())
                .buyPrice(priceUpdateRequest.getBuyPrice()).sellPrice(priceUpdateRequest.getSellPrice())
                .stopLossPrice(priceUpdateRequest.getStopLossPrice()).build());
    }

}

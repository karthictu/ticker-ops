package com.karthic.stocks_bot.services.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthic.stocks_bot.entities.Ticker;
import com.karthic.stocks_bot.entities.TickerId;
import com.karthic.stocks_bot.repositories.TickerRepository;
import com.karthic.stocks_bot.services.TickerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TickerServiceImpl implements TickerService {

    @Autowired
    private TickerRepository tickerRepository;

    @Override
    public Flux<Ticker> saveAll(Flux<Ticker> tickers) {
        return tickerRepository.saveAll(tickers);
    }

    @Override
    public Mono<Ticker> save(Ticker ticker) {
        return tickerRepository.save(ticker);
    }

    @Override
    public Flux<Ticker> findAll() {
        return tickerRepository.findAll();
    }

    @Override
    public Mono<Ticker> findById(TickerId tickerId) {
        return tickerRepository.findById(tickerId);
    }

    @Override
    public Mono<Void> delete(TickerId tickerId) {
        return tickerRepository.deleteById(tickerId);
    }

}

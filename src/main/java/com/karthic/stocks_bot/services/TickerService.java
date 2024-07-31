package com.karthic.stocks_bot.services;

import java.util.Optional;
import java.util.Set;

import com.karthic.stocks_bot.entities.Ticker;
import com.karthic.stocks_bot.entities.TickerId;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TickerService {
    Flux<Ticker> saveAll(Flux<Ticker> tickers);

    Mono<Ticker> save(Ticker ticker);

    Flux<Ticker> findAll();

    Mono<Ticker> findById(TickerId tickerId);

    Mono<Void> delete(TickerId tickerId);
}

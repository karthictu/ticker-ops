package com.karthic.stocks_bot.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.karthic.stocks_bot.constants.CommonConstants;
import com.karthic.stocks_bot.entities.Ticker;
import com.karthic.stocks_bot.entities.TickerId;
import com.karthic.stocks_bot.models.PriceUpdateRequest;
import com.karthic.stocks_bot.services.TickerService;

import reactor.core.publisher.Mono;

public class TickerHandler {

    @Autowired
    private TickerService tickerService;

    public Mono<ServerResponse> retrieveAllTickers(ServerRequest request) {
        return ServerResponse.ok().bodyValue(tickerService.findAll());
    }

    public Mono<ServerResponse> retrieveTickerById(ServerRequest request) {
        return ServerResponse.ok().bodyValue(tickerService.findById(
                TickerId.builder().userId(request.pathVariable(CommonConstants.USER_ID))
                        .tickerId(request.pathVariable(CommonConstants.TICKER_ID))
                        .build()));
    }

    public Mono<ServerResponse> saveTickers(ServerRequest request) {
        return ServerResponse.ok().bodyValue(tickerService.saveAll(request.bodyToFlux(Ticker.class)));
    }

    public Mono<ServerResponse> saveTickerById(ServerRequest request) {
        return ServerResponse.ok().bodyValue(request.bodyToMono(Ticker.class)
                .doOnNext(data -> tickerService.save(data)));
    }

    public Mono<ServerResponse> deleteTickers(ServerRequest request) {
        request.bodyToMono(Ticker.class).doOnNext(data -> tickerService.delete(
                TickerId.builder().userId(request.pathVariable(CommonConstants.USER_ID)).tickerId(data.getTickerId())
                        .build()));
        return ServerResponse.ok().build();
    }

    public Mono<ServerResponse> updatePrices(ServerRequest request) {
        return ServerResponse.ok().bodyValue(request.bodyToMono(PriceUpdateRequest.class).doOnNext(data -> tickerService
                .updatePrices(request.pathVariable(CommonConstants.USER_ID),
                        request.pathVariable(CommonConstants.TICKER_ID), data)));
    }
}

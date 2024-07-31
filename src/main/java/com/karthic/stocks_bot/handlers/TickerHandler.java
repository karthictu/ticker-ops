package com.karthic.stocks_bot.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.karthic.stocks_bot.entities.Ticker;
import com.karthic.stocks_bot.entities.TickerId;
import com.karthic.stocks_bot.models.MarginUpdateRequest;
import com.karthic.stocks_bot.models.StopLossUpdateRequest;
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
                TickerId.builder().userId(request.pathVariable("userId")).tickerId(request.pathVariable("tickerId"))
                        .build()));
    }

    public Mono<ServerResponse> saveTickers(ServerRequest request) {
        return ServerResponse.ok().bodyValue(tickerService.saveAll(request.bodyToFlux(Ticker.class)));
    }

    public Mono<ServerResponse> saveTickerById(ServerRequest request) {
        return ServerResponse.ok().bodyValue(request.bodyToMono(Ticker.class)
                .doOnNext(data -> tickerService.save(Ticker.builder().userId(request.pathVariable("userId"))
                        .tickerId(request.pathVariable("tickerId")).tickerName(data.getTickerName())
                        .buyPrice(data.getBuyPrice()).sellForProfitMargin(data.getSellForProfitMargin())
                        .stopLoss(data.getStopLoss()).build())));
    }

    public Mono<ServerResponse> updateSellForProfitMargin(ServerRequest request) {
        return ServerResponse.ok().bodyValue(request.bodyToMono(MarginUpdateRequest.class)
                .doOnNext(data -> {
                    tickerService.findById(TickerId.builder().userId(request.pathVariable("userId"))
                            .tickerId(request.pathVariable("tickerId")).build()).doOnNext(ticker -> {
                                ticker.setSellForProfitMargin(data.getSellForProfitMargin());
                                tickerService.save(ticker);
                            });
                }));
    }

    public Mono<ServerResponse> updateStopLoss(ServerRequest request) {
        return ServerResponse.ok().bodyValue(request.bodyToMono(StopLossUpdateRequest.class)
                .doOnNext(data -> {
                    tickerService.findById(TickerId.builder().userId(request.pathVariable("userId"))
                            .tickerId(request.pathVariable("tickerId")).build()).doOnNext(ticker -> {
                                ticker.setStopLoss(data.getStopLoss());
                                tickerService.save(ticker);
                            });
                }));
    }

    public Mono<ServerResponse> deleteTickers(ServerRequest request) {
        request.bodyToMono(Ticker.class).doOnNext(data -> {
            tickerService.delete(
                    TickerId.builder().userId(request.pathVariable("userId")).tickerId(data.getTickerId()).build());
        });
        return ServerResponse.ok().build();
    }
}

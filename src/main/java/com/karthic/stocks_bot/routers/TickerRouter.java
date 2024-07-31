package com.karthic.stocks_bot.routers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.karthic.stocks_bot.handlers.TickerHandler;

@Configuration
public class TickerRouter {

    @Bean
    public RouterFunction<ServerResponse> tickerRoutes(TickerHandler tickerHandler) {
        return RouterFunctions.nest(RequestPredicates.path("/api/stocks-bot/{userId}/tickers"),
                RouterFunctions.route().GET(tickerHandler::retrieveAllTickers).POST(tickerHandler::saveTickers)
                        .nest(RequestPredicates.path("/{tickerId}"),
                                () -> RouterFunctions.route().GET(tickerHandler::retrieveTickerById)
                                        .POST(tickerHandler::saveTickerById)
                                        .PATCH("/sell-margin", tickerHandler::updateSellForProfitMargin)
                                        .PATCH("/stop-loss", tickerHandler::updateStopLoss)
                                        .DELETE(tickerHandler::deleteTickers)
                                        .build())
                        .build());
    }
}

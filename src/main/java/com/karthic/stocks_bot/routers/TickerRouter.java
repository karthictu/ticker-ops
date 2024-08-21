package com.karthic.stocks_bot.routers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.karthic.stocks_bot.constants.CommonConstants;
import com.karthic.stocks_bot.handlers.TickerHandler;

@Configuration
public class TickerRouter {

    @Bean
    public RouterFunction<ServerResponse> tickerRoutes(TickerHandler tickerHandler) {
        return RouterFunctions.nest(RequestPredicates.path(CommonConstants.API_BASE_URL),
                RouterFunctions.route().GET(tickerHandler::retrieveAllTickers).POST(tickerHandler::saveTickers)
                        .nest(RequestPredicates.path(CommonConstants.ENDPOINT_TICKER_ID),
                                () -> RouterFunctions.route().GET(tickerHandler::retrieveTickerById)
                                        .POST(tickerHandler::saveTickerById)
                                        .PATCH(CommonConstants.ENDPOINT_PRICES, tickerHandler::updatePrices)
                                        .DELETE(tickerHandler::deleteTickers)
                                        .build())
                        .build());
    }
}

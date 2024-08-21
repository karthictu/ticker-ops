package com.karthic.stocks_bot.constants;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonConstants {
    public static final String USER_ID = "userId";
    public static final String TICKER_ID = "tickerId";
    public static final String TICKERS = "tickers";
    public static final String PRICES = "prices";
    public static final String API = "api";
    public static final String STOCKS_BOT = "stocks-bot";
    
    public static final String SYMBOLS_FORWARD_SLASH = "/";
    public static final String SYMBOLS_OPEN_CURLY = "{";
    public static final String SYMBOLS_CLOSE_CURLY = "}";

    public static final String ENDPOINT_API = SYMBOLS_FORWARD_SLASH + API;
    public static final String ENDPOINT_PRICES = SYMBOLS_FORWARD_SLASH + PRICES;
    public static final String ENDPOINT_STOCKS_BOT = SYMBOLS_FORWARD_SLASH + STOCKS_BOT;
    public static final String ENDPOINT_TICKERS = SYMBOLS_FORWARD_SLASH + TICKERS;
    public static final String ENDPOINT_USER_ID = SYMBOLS_FORWARD_SLASH + SYMBOLS_OPEN_CURLY + USER_ID + SYMBOLS_CLOSE_CURLY;
    public static final String ENDPOINT_TICKER_ID = SYMBOLS_FORWARD_SLASH + SYMBOLS_OPEN_CURLY + TICKER_ID + SYMBOLS_CLOSE_CURLY;
    public static final String API_BASE_URL = ENDPOINT_API + ENDPOINT_STOCKS_BOT + ENDPOINT_USER_ID + ENDPOINT_TICKERS;
}

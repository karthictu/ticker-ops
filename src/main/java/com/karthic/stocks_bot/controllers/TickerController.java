package com.karthic.stocks_bot.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.karthic.stocks_bot.constants.CommonConstants;
import com.karthic.stocks_bot.entities.Ticker;
import com.karthic.stocks_bot.models.PriceUpdateRequest;
import com.karthic.stocks_bot.services.TickerService;

@RestController
@RequestMapping(CommonConstants.API_BASE_URL)
public class TickerController {

    @Autowired
    private TickerService tickerService;

    @GetMapping
    public ResponseEntity<List<Ticker>> getAllTickersForUserId(@PathVariable String userId) {
        List<Ticker> results = tickerService.findTickersByUserId(userId);
        if (results.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok().body(results);
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Ticker saveTickerById(@PathVariable(name = CommonConstants.USER_ID) String userId,
            @RequestBody Ticker ticker) {
        return tickerService.save(userId, ticker);
    }

    @GetMapping(path = CommonConstants.ENDPOINT_TICKER_ID)
    public ResponseEntity<Ticker> retrieveTickersById(@PathVariable(name = CommonConstants.TICKER_ID) String tickerId,
            @PathVariable(name = CommonConstants.USER_ID) String userId) {
        Ticker result = null;
        try {
            result = tickerService.findByIdAndUserId(tickerId, userId);
        } catch (NoSuchElementException nse) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping(path = CommonConstants.ENDPOINT_TICKER_ID)
    @ResponseStatus(HttpStatus.OK)
    public void deleteTickerById(@PathVariable(name = CommonConstants.TICKER_ID) String tickerId,
            @PathVariable(name = CommonConstants.USER_ID) String userId) {
        tickerService.deleteForUserId(tickerId, userId);
    }

    @PatchMapping(path = CommonConstants.ENDPOINT_UPDATE_PRICES)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Ticker updatePrices(@PathVariable(name = CommonConstants.TICKER_ID) String tickerId,
            @PathVariable(name = CommonConstants.USER_ID) String userId,
            @RequestBody PriceUpdateRequest priceUpdateRequest) {
        return tickerService.updatePrices(userId, tickerId, priceUpdateRequest);
    }
}

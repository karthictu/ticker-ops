package com.karthic.stocks_bot.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.karthic.stocks_bot.entities.Ticker;
import com.karthic.stocks_bot.entities.TickerId;

@Repository
public interface TickerRepository extends ReactiveCrudRepository<Ticker, TickerId> {

}

package com.karthic.stocks_bot.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.karthic.stocks_bot.entities.Ticker;

@Repository
public interface TickerRepository extends CrudRepository<Ticker, String> {

    public List<Ticker> findAllByUserId(String userId);
    public Optional<Ticker> findByTickerIdAndUserId(String tickerId, String userId);
    public void deleteByTickerIdAndUserId(String tickerId, String userId);

}

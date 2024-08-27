create table tickers (
    ticker_id varchar(255) not null,
    buy_price float(53),
    sell_price float(53),
    stop_loss_price float(53),
    ticker_name varchar(255),
    user_id varchar(255),
    primary key (ticker_id)
)
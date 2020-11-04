package GradedLab2;

import java.util.HashMap;
import java.util.Map;

public class BrokerageFirm {
    static Map<String,Trader> traders;
    static  Trader getTrader(String name){
        return traders.get(name);
    }

    static Map<String,Scrip> scrips;
    static  Scrip getScrip(String name){
        return scrips.get(name);
    }

    public  void addScrip(String ticker, String sector,  double O, double H, double L, double C ){
        StockExchange exchange= new StockExchange("NSE");
        Scrip scrip= new Scrip(ticker,sector,0,0,0,L,H,O,C, exchange);
        exchange.add(scrip);
        scrips.put(ticker,scrip);
    }

    public void placeOrder(String name, String type, String ticker, int quantity, double rate){
        Order order= new Order(name,type,scrips.get(ticker),quantity,rate,0,0);
        Orderbook.addOrder(order);
    }

    public void addUser(String name, int funds, Map<String,Integer>holdings){
        Trader trader=  new Trader(name,funds, holdings);
        traders.put(name, trader);
    }



    public static void main(String[] args) {
        StockExchange NYSE= new  StockExchange("NSE");
        StockExchange BSE= new  StockExchange("BSE");




    }
}

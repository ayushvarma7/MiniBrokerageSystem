package GradedLab2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Scrip { //Company is abbreviated as Scrip
    String name; //name of the company
    String ticker; // ticker or abbreviation
    String sector; // category of stock
    int totalShares; // total no. of shares of the company
    double upperCircuitRate; //maximum allowed price of the share
    double lowerCircuitRate; //minimum allowed price of the share
    double LTP=0; // means the latest trading price
    double lowPrice=0; // lowest price per share of the transaction in the whole day
    double highPrice=0; // highest price per share of the transaction in the whole day
    double openPrice=0; // price of the first transaction of the day
    double closePrice=0; // price of the last transaction of the day
    StockExchange stockExchange; // listed in which stock exchange

    Map<Trader, Integer> shares= new HashMap<>(); // Map of {Trader and Integer} pair consisting of
//    all the shares owned/acquired by different traders

//Constructors


    public Scrip( String ticker, String sector, double upperCircuitRate, double lowerCircuitRate, double LTP, double lowPrice, double highPrice, double openPrice, double closePrice, StockExchange stockExchange) {
        this.ticker = ticker;
        this.sector = sector;
        this.upperCircuitRate = highPrice+(0.1*highPrice);
        this.lowerCircuitRate = lowPrice-(0.1*lowPrice);
        this.lowPrice = lowPrice;
        this.highPrice = highPrice;
        this.openPrice = openPrice;
        this.closePrice = closePrice;
        this.stockExchange = stockExchange;
    }

    public Scrip(){//if a Scrip is just initialised then we set its attributes as the following
        this.Scrip("unknown","UNK","unknown", 0,0,0,0);
    }



    public Scrip(StockExchange exchange, String ticker, String sector) {
        //initialising with the ticker, sector and the stock exchange it is present in
        this( ticker, sector,0,0,0,0,0,0,0, exchange);
    }

//Methods
    public String getName() {
        return name;
    }

    public String getTicker() {
        return ticker;
    }

    public String getSector() {
        return sector;
    }

    public int getTotalShares() {
        return totalShares;
    }

    public double getUpperCircuitRate() {
        return upperCircuitRate;
    }

    public double getLowerCircuitRate() {
        return lowerCircuitRate;
    }


    public StockExchange getStockExchange() {
        return stockExchange;
    }

    public double getLTP() {
        return LTP;
    }

    public void setLTP(int LTP) {
        this.LTP = LTP;
    }

    public double getLowPrice() {
        return lowPrice;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public void Scrip(String name ,String ticker, String sector, double O, double H, double L, double C){
        this.name=name;
        this.ticker=ticker;
        this.sector=sector;
        this.openPrice=O;
        this.highPrice=H;
        this.lowPrice=L;
        this.closePrice=C;
        this.upperCircuitRate=highPrice+(0.1*highPrice);
        this.lowerCircuitRate=lowPrice-(0.1*lowPrice);
    }


    public String printScrip() {
        return "scrip:" +ticker +
                ", sector:" + sector + ", lowPrice:" + lowPrice +
                ", highPrice:" + highPrice +
                ", openPrice:" + openPrice +
                ", closePrice:" + closePrice ;
    }

    @Override
    public String toString() {
        return ticker+ ", OHLC = <"+ openPrice+", "+ highPrice+ ", "+ lowPrice+ ", "+ closePrice+ ">";
    }
}

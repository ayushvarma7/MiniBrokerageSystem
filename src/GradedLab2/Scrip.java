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
    double LTP; // means the latest trading price
    double lowPrice=0; // lowest price per share of the transaction in the whole day
    double highPrice=0; // highest price per share of the transaction in the whole day
    double openPrice=0; // price of the first transaction of the day
    double closePrice=0; // price of the last transaction of the day
    StockExchange stockExchange; // listed in which stock exchange

    Map<Trader, Integer> shares= new HashMap<>();

//Constructors


    public Scrip( String ticker, String sector, double upperCircuitRate, double lowerCircuitRate, double LTP, double lowPrice, double highPrice, double openPrice, double closePrice, StockExchange stockExchange) {
        this.ticker = ticker;
        this.sector = sector;
        this.upperCircuitRate = upperCircuitRate;
        this.lowerCircuitRate = lowerCircuitRate;
        this.lowPrice = lowPrice;
        this.highPrice = highPrice;
        this.openPrice = openPrice;
        this.closePrice = closePrice;
        this.stockExchange = stockExchange;
    }

    public Scrip(){
        this.Scrip("unknown","UNK","unknown", 0,0,0,0);
    }



    public Scrip(StockExchange exchange, String ticker, String sector) {
        this( ticker, sector,0,0,0,0,0,0,0, exchange);
    }


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



    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", ticker='" + ticker + '\'' +
                ", sector='" + sector + '\'' +
                ", totalShares=" + totalShares +
                ", upperCircuitRate=" + upperCircuitRate +
                ", lowerCircuitRate=" + lowerCircuitRate +
                ", LTP=" + LTP +
                ", lowPrice=" + lowPrice +
                ", highPrice=" + highPrice +
                ", openPrice=" + openPrice +
                ", closePrice=" + closePrice +
                ", stockExchange=" + stockExchange +
                '}';
    }
}

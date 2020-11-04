package GradedLab2;

import java.util.ArrayList;
import java.util.UUID;

public class Company {
    String name; //name of the company
    String ticker; // ticker or abbreviation
    String sector; // category of stock
    int totalShares; // total no. of shares of the company
    int upperCircuitRate; //maximum allowed price of the share
    int lowerCircuitRate; //minimum allowed price of the share
    int LTP; // means the latest trading price
    int lowPrice; // lowest price per share of the transaction in the whole day
    int highPrice; // highest price per share of the transaction in the whole day
    int openPrice; // price of the first transaction of the day
    int closePrice; // price of the last transaction of the day
    StockExchange stockExchange; // listed in which stock exchange

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

    public int getUpperCircuitRate() {
        return upperCircuitRate;
    }

    public int getLowerCircuitRate() {
        return lowerCircuitRate;
    }


    public StockExchange getStockExchange() {
        return stockExchange;
    }

    public int getLTP() {
        return LTP;
    }

    public void setLTP(int LTP) {
        this.LTP = LTP;
    }

    public int getLowPrice() {
        return lowPrice;
    }

    public int getHighPrice() {
        return highPrice;
    }

    public int getOpenPrice() {
        return openPrice;
    }

    public int getClosePrice() {
        return closePrice;
    }

    public void addScrip(String name , String sector, int O, int H, int L, int C){
        this.name=name;
        this.sector=sector;
        this.openPrice=O;
        this.highPrice=H;
        this.lowPrice=L;
        this.closePrice=C;
    }
}

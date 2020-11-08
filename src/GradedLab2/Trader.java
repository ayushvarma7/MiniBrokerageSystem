package GradedLab2;

import java.util.Map;
import java.util.UUID;

public class Trader {
    String name; //name of the trader
    UUID traderId; // unique trader id
    double funds=0; // funds in the account
    Map<String, Integer> sharesOwned;  // Map of all the name of shares and amount owned by the trader
    Orderbook personalOrderBook;  // contains all the orders done by the trader

//    Constructors
    public Trader(String name, int funds, Map<String, Integer> sharesOwned) {
        this.name = name;
        this.funds = funds;
        this.sharesOwned = sharesOwned;
    }

//    Methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getTraderId() {
        return traderId;
    }

    public double getFunds() {
        return funds;
    }

    public void setFunds(int funds) {
        this.funds = funds;
    }

    public Map<String, Integer> getSharesOwned() {//returns the map of shares owned by the trader
        return sharesOwned;
    }

    public void setSharesOwned(Map<String, Integer> sharesOwned) {
        this.sharesOwned = sharesOwned;
    }

    public Orderbook getPersonalOrderBook() {//returns the orderbook for the trader
        return personalOrderBook;
    }

    public void setPersonalOrderBook(Orderbook personalOrderBook) {
        this.personalOrderBook = personalOrderBook;
    }

    @Override
    public String toString() {
        return "user:" +
                name + ", funds:" + funds + ", holding:" + sharesOwned;
    }
}

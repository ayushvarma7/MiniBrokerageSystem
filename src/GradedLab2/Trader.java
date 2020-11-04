package GradedLab2;

import java.util.HashMap;
import java.util.UUID;

public class Trader {
    String name; //name of the trader
    UUID traderId; // unique trader id
    int funds; // funds in the account
    HashMap<Company, Double> sharesOwned;  // hashmap of all the shares owned by the trader
    Orderbook personalOrderBook;  // contains all the orders done by the trader

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getTraderId() {
        return traderId;
    }

    public int getFunds() {
        return funds;
    }

    public void setFunds(int funds) {
        this.funds = funds;
    }

    public HashMap<Company, Double> getSharesOwned() {
        return sharesOwned;
    }

    public void setSharesOwned(HashMap<Company, Double> sharesOwned) {
        this.sharesOwned = sharesOwned;
    }

    public Orderbook getPersonalOrderBook() {
        return personalOrderBook;
    }

    public void setPersonalOrderBook(Orderbook personalOrderBook) {
        this.personalOrderBook = personalOrderBook;
    }
}

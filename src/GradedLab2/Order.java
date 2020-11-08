package GradedLab2;

public class Order {
    String trader; // trader name who initialised the order
    String orderType; // orderType as in buy-order or sell-order
    Scrip scrip; // company name
    int quantity; // quantity of shares in the order
    double rate; // rate at which the transaction is taking place
    int askPrice=0;
    int bidPrice=0;

    public Order(String trader, String orderType, Scrip scrip, int quantity, double rate, int askPrice, int bidPrice) {
        this.trader = trader;
        this.orderType = orderType;
        this.scrip = scrip;
        this.quantity = quantity;
        this.rate = rate;
        this.askPrice = askPrice;
        this.bidPrice = bidPrice;

    }

    public String getTrader() {
        return trader;
    }

    public String getOrderType() {
        return orderType;
    }

    public Scrip getScrip() {
        return scrip;
    }



    public int getQuantity(){
        return  quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getRate() {
        return rate;
    }

    public String printOrder() {
        return  trader +
                ", type:" + orderType +
                ", scrip:" + scrip.getTicker() +
                ", qty:" + quantity +
                ", rate: " + rate ;
    }

    @Override
    public String toString() {
        return orderType+ " order "+ scrip.getTicker()+ ":"+ quantity + " at " + rate;
    }

}


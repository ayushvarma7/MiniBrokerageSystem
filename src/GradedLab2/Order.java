package GradedLab2;

public class Order {
    Trader User; // trader name who initialised the order
    String orderType; // orderType as in buy-order or sell-order
    Company scrip; // company name
    int quantity; // quantity of shares in the order
    int rate; // rate at which the transaction is taking place
    int askPrice=0;
    int bidPrice=0;



    public Trader getUser() {
        return User;
    }

    public String getOrderType() {
        return orderType;
    }

    public Company getScrip() {
        return scrip;
    }

    public int getQuantity(){
        return  quantity;
    }

    public int getRate() {
        return rate;
    }
}


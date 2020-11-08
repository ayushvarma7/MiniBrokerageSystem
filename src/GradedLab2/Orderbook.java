package GradedLab2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Orderbook {
    static Map<Trader, List<Order>> orders= new HashMap<>();// Map of traders and the list of orders for this trader
   static List<Order> closedOrders=new ArrayList<>();//this will contain the completed orders

    public Map<Trader, List<Order>> getOrders() {
        return orders;
    }

    public void setOrders(Map<Trader, List<Order>> orders) {
        this.orders = orders;
    }

    public static void addOrder(Order order){//adding an order in the Orderbook
        Trader trader=BrokerageFirm.getTrader(order.getTrader());//first getting the name of the trader
//        System.out.println(trader.getFunds());
        if(orders.containsKey(trader)){//now checking if this trader is already present in the Orderbook
            orders.get(trader).add(order);//if yes, then adding the new order into the list of orders for this trader
        }else{//if the trader is new
            List<Order> list= new ArrayList<>();//then making a new list for the trader
            list.add(order);//now adding this order into the newly generated list
            orders.put(trader,list);//inserting this list into the map of 'orders'
        }
        System.out.println("Order placed for user:"+order.printOrder());
    }

    public static void showOrderBook(){
        System.out.println("\nOrderbook:");
        orders.values().forEach(x-> System.out.println(x+"\n"));

    }
   static public void showOrdersForTrader(Trader trader){ //assuming we have a unique person for every name
        if(orders.containsKey(trader)){//if the trader is present in the map of orders
            orders.get(trader).forEach(x-> System.out.println(x)); //then, print all the orders of the trader
        }else{ //if there is no such trader
            System.out.println("No orders Found");
        }
    }

    static public void ordersCompletion() {
        List <Order> buyOrders=new ArrayList<>();
        List <Order> sellOrders=new ArrayList<>();
        orders.values().forEach(listOfOrders-> listOfOrders.forEach( order-> {
            if(order.getOrderType().contains("buy")) buyOrders.add(order);
            else sellOrders.add(order);
        }));
        buyOrders.forEach(x-> {
            sellOrders.forEach(y -> {
//                System.out.println(x.printOrder()+ "  "+ y.printOrder());
                if (x.getQuantity()>0 && y.getQuantity()>0 && x.getScrip().getTicker() == y.getScrip().getTicker()) {
                    //the price of negotiation is equal or complete
                    if(y.getRate()<=x.getRate()) {
                        //the remaining quantity left in buy order
                        if (x.getQuantity()-y.getQuantity()>0){
                            x.setQuantity(x.getQuantity()-y.getQuantity());
//                            getting the list of trader's orders and then removing this specific order
                            orders.get(BrokerageFirm.traders.get(y.getTrader())).remove(y);
                            closedOrders.add(y); //adding this order in the list of closed orderbook
                            System.out.println(y.getQuantity()+ " qty of scrip:" + x.getScrip().getTicker()+ " sold for INR "+ x.getRate()+"; Buyer:"+ x.getTrader()+ ", Seller:"+ y.getTrader());
                            y.setQuantity(0);//removing this order from the main orderbook
                        }else {
                            y.setQuantity(y.getQuantity()-x.getQuantity());
                            orders.get(BrokerageFirm.traders.get(x.getTrader())).remove(x);
                            closedOrders.add(x);
                            System.out.println(x.getQuantity()+ " qty of scrip:" + x.getScrip().getTicker()+ " sold for INR "+ x.getRate()+"; Buyer:"+ x.getTrader()+ ", Seller:"+ y.getTrader());
                            x.setQuantity(0);
//                            buyOrders.remove(x);
                        }
                    }
                }
            });
        });
    }
}

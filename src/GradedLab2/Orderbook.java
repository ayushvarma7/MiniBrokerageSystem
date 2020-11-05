package GradedLab2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Orderbook {
    static Map<Trader, List<Order>> orders= new HashMap<>();// Map of traders and the list of orders for this trader

    public Map<Trader, List<Order>> getOrders() {
        return orders;
    }

    public void setOrders(Map<Trader, List<Order>> orders) {
        this.orders = orders;
    }

    public static void addOrder(Order order){//adding an order in the Orderbook
        Trader trader=BrokerageFirm.getTrader(order.getTrader());//first getting the name of the trader a
        if(orders.containsKey(trader)){//now checking if this trader is already present in the Orderbook
            orders.get(trader).add(order);//if yes, then adding the new order into the list of orders for this trader
        }else{//if the trader is new
            List<Order> list= new ArrayList<>();//then making a new list for the trader
            list.add(order);//now adding this order into the newly generated list
            orders.put(trader,list);//inserting this list into the map of 'orders'
        }
    }

    public static void showOrderBook(){
        orders.values().forEach(x-> System.out.println(x));
    }
   static public void showOrdersForTrader(Trader trader){ //assuming we have a unique person for every name
        if(orders.containsKey(trader)){//if the trader is present in the map of orders
            orders.get(trader).forEach(x-> System.out.println(x)); //then, print all the orders of the trader
        }else{ //if there is no such trader
            System.out.println("No orders Found");
        }
    }
}

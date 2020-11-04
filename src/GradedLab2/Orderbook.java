package GradedLab2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Orderbook {
    static Map<Trader, List<Order>> orders;

    public Map<Trader, List<Order>> getOrders() {
        return orders;
    }

    public void setOrders(Map<Trader, List<Order>> orders) {
        this.orders = orders;
    }

    public static void addOrder(Order order){
        Trader trader=BrokerageFirm.getTrader(order.getTrader());
        if(orders.containsKey(trader)){
            orders.get(trader).add(order);
        }else{
            List<Order> list= new ArrayList<>();
            list.add(order);
            orders.put(trader,list);
        }
    }

   static public void showOrdersForTrader(Trader trader){
        if(orders.containsKey(trader)){
            orders.get(trader).forEach(x-> System.out.println(x));
        }else{
            System.out.println("No orders Found");
        }
    }
}

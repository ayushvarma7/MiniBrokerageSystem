package GradedLab2;

import java.util.ArrayList;

public class StockExchange extends Company{
    String Name; //name of the stock exchange
    ArrayList<Company> Companies= new ArrayList<Company>(); //contains all the companies present in the stock exchange
    Orderbook orderbook; // all the orders completed in this stock exchange

   public String getName() {
      return Name;
   }

   public ArrayList<Company> getCompanies() {
      return Companies;
   }

   public void setCompanies(ArrayList<Company> companies) {
      Companies = companies;
   }

   public Orderbook getOrderbook() {
      return orderbook;
   }

   public StockExchange(String name) {
      Name = name;
   }

   public void addScrip(int L){
      
   }


}

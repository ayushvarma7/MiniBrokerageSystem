package GradedLab2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockExchange{
    String Name; //name of the stock exchange
//    ArrayList<Scrip> Companies= new ArrayList<Scrip>(); //contains all the companies present in the stock exchange
    Map<String, List<Scrip>> Companies= new HashMap<>();
   Orderbook orderbook; // all the orders completed in this stock exchange



//   Constructors


   public StockExchange(String name) {
      Name = name;
   }

   public void add(Scrip company){
      String sector= company.getSector();
      if(Companies.containsKey(sector)) {
         Companies.get(sector).add(company);
      }
      else {
         List<Scrip> list= new ArrayList<>();
         list.add(company);
         Companies.put(sector,list);
      }
   }

   public Map<String, List<Scrip>> getCompanies() {
      return Companies;
   }

   public void setCompanies(Map<String, List<Scrip>> companies) {
      Companies = companies;
   }

   public String getName() {
      return Name;
   }

   public Orderbook getOrderbook() {
      return orderbook;
   }

//   listing out the companies on the basis of the sector query
   public void getCompanySectorClassification(String sector){
      if(Companies.containsKey(sector)) {
         Companies.get(sector).forEach(x-> System.out.println(x));
      }else {
         System.out.println("No stocks found!");
      }
   }//end of function


}

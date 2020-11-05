package GradedLab2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockExchange{
    String Name; //name of the stock exchange
//    ArrayList<Scrip> Companies= new ArrayList<Scrip>(); //contains all the companies present in the stock exchange
    Map<String, List<Scrip>> Companies= new HashMap<>();//Map of 'Sector' String and list of Companies in it
   Orderbook orderbook; // all the orders completed in this stock exchange



//   Constructors


   public StockExchange(String name) {
      Name = name;
   }

   public void add(Scrip company){
      String sector= company.getSector(); //first extracting the sector of the company
      if(Companies.containsKey(sector)) {//if there is already a sector present in the map
         Companies.get(sector).add(company);//add this company into that list
      }
      else {//iff not present originally
         List<Scrip> list= new ArrayList<>();//create a new list of this sector
         list.add(company);//adding the company into the list
         Companies.put(sector,list);//adding the new list into the map
      }
   }

   public Map<String, List<Scrip>> getCompanies() {
      return Companies; //returns a map of companies
   }

   public void setCompanies(Map<String, List<Scrip>> companies) {
      Companies = companies; //sets the companies
   }

   public String getName() {
      return Name; //returns the name of the stock exchange
   }

   public Orderbook getOrderbook() {
      return orderbook;//returns the orderbook
   }

//   listing out the companies on the basis of the sector query
   public void getScripsForSector(String sector){
      System.out.println("Scrips listed in sector:"+ sector);
      if(Companies.containsKey(sector)) { //if the specified sector is present
         Companies.get(sector).forEach(x-> System.out.println(x));//print out all the companies from the list of specified sector
      }else {//if the specified sector isn't present then print out NOT FOUND
         System.out.println("No stocks found!");
      }
   }//end of function


}

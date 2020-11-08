package GradedLab2;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//Made by Ayush Varma CSE'23
// github link:https://github.com/ayushvarma7/MiniBrokerageSystem.git

public class BrokerageFirm {
    static Map<String,Trader> traders= new HashMap<>(); //Map of Name of the trader and the details of the trader

    static Map<String,Scrip> scrips= new HashMap<>(); //Map of {String of 'ticker' and the scrip itself}

    static  Trader getTrader(String name){//returns the name of the trader
        return traders.get(name);
    }


    static  Scrip getScrip(String name) { //returns the Scrip details using the name of the scrip
        return scrips.get(name);
    }

    public void addScrip(String ticker, String sector,  double O, double H, double L, double C , StockExchange exchange){
//        adding a new scrip into the stock exchange
//        int rand= (int)(Math.random()*2);
        Scrip scrip = new Scrip(ticker, sector, 0, 0, 0, L, H, O, C, exchange);
        exchange.add(scrip);
        scrips.put(ticker, scrip);

    }

    public void deleteScrip(String name){
        if(scrips.containsKey(name)){
            System.out.println("Deleted scrip:"+ name);
            scrips.remove(name);
        }else System.out.println("No Scrip found!");
    }

    public void showScrips(){
        System.out.println("\nScrips:");
        scrips.values().forEach(x-> System.out.println(x.printScrip()));
    }

    public void placeOrder(String name, String type, String ticker, int quantity, double rate){
        Order order= new Order(name,type,scrips.get(ticker),quantity,rate,0,0);
        if(rate>= scrips.get(ticker).getLowerCircuitRate() && rate<= scrips.get(ticker).getUpperCircuitRate()){
           if(order.getOrderType().contains("buy") && traders.containsKey(name.trim()) && traders.get(name.trim()).getFunds()<quantity*rate)
               System.out.println("Order rejected for user:" + order.printOrder());
           else Orderbook.addOrder(order);
        }
        else System.out.println("Order rejected for user:"+ order.printOrder() );

    }

    public void addUser(String name, int funds, Map<String,Integer>holdings){
        Trader trader=  new Trader(name,funds, holdings);
        traders.put(name, trader);
        System.out.println("Added user: "+ name+ " with a new instantiation of Trader" );
    }

    public void deleteUser(String name){
        if(traders.containsKey(name)){
            traders.remove(name);
            System.out.println("Deleted User:"+name);
        }else System.out.println("No user found!");
    }

    public void showUsers(){
        System.out.println("\nUsers");
        traders.values().forEach(x-> System.out.println(x));
    }


    public static void main(String[] args) throws Exception{
        BrokerageFirm brokerageFirm = new BrokerageFirm();
        StockExchange NSE= new StockExchange("NSE");
        StockExchange BSE= new StockExchange("BSE");
        StockExchange exchange=NSE;
        File file, file1;
        Scanner fileIn;
        int response;
        JFileChooser chooser= new JFileChooser("./src/GradedLab2/");

        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        response=chooser.showOpenDialog(null);

        if (response==JFileChooser.APPROVE_OPTION){
            file= chooser.getSelectedFile();

//            file=new File("./src/GradedLab2/sample_input2.txt");//chooser.getSelectedFile();
            boolean flag=true;
            try{
                fileIn= new Scanner(file);
                if(file.isFile()){
                    while(fileIn.hasNextLine()) {
                        String line = fileIn.nextLine();
//                        System.out.println(line);
                        if (line.startsWith("Add user")) {
                            String name= line.substring(10, line.indexOf(","));
//                            System.out.println(name);
                            int funds= Integer.parseInt(line.substring(line.indexOf("s:")+2,line.indexOf("holding")-1));
//                            System.out.println(funds);
                            String h= (line.contains("None"))?null :line.substring(line.indexOf("{")+1, line.indexOf("}"));
//                            System.out.println(h);
                            Map<String, Integer> holdings= new HashMap<>();
                            if(h!=null){
                                String [] holdingWords= h.split(",");
                                for (int j=0; j<holdingWords.length; j++){
                                    String[] values =holdingWords[j].split(":");
                                    holdings.put(values[0], Integer.parseInt(values[1]));
                                }
                            }
                                brokerageFirm.addUser(name,funds, holdings);
                        }else if (line.startsWith("Show Orderbook")){
                            Orderbook.showOrderBook();
                        }else if (line.startsWith("Show sector")){
                            String sector= line.substring(line.indexOf(":")+1);//takes the value of the sector
                            exchange.getScripsForSector(sector); //passes the sector into the function to print all the values
                        }else if(line.startsWith("Delete scrip: ")){
                            String scrip= line.substring(line.indexOf(":")+1);//takes the value of the scrip
                            brokerageFirm.deleteScrip(scrip);
                        }else if(line.startsWith("Delete User:")){
                            String user= line.substring(line.indexOf(":")+2);//takes the value of the scrip
                            brokerageFirm.deleteUser(user);
                        }else if(line.startsWith("Show Scrips")){
                            brokerageFirm.showScrips();
                        }else if(line.startsWith("Show Users")){
                            brokerageFirm.showUsers();
                        }else if(line.startsWith("Exit")){
                            System.out.println("Market closed!");
                        }else if(line.startsWith("Execute")){
                            System.out.println("Executed orders");
                            Orderbook.ordersCompletion();
                        }

                        else {
                            String[] words = line.split(",");
                            switch (words[0].split(":")[0]) {
                                case "Add scrip": {
                                    int num=(int) (Math.random()*2);
                                    exchange= ((int) (Math.random()*2)==0)? NSE : BSE;
                                    brokerageFirm.addScrip(brokerageFirm.splitItem(words[0]), brokerageFirm.splitItem(words[1]), Double.parseDouble(brokerageFirm.splitItem(words[2])), Double.parseDouble(brokerageFirm.splitItem(words[3])), Double.parseDouble(brokerageFirm.splitItem(words[4])), Double.parseDouble(brokerageFirm.splitItem(words[5])),exchange);
                                    break;
                                }

                                case "Place order": {
                                   if(flag) {
                                       System.out.println("Market Opens!");
                                       flag=false;
                                   }
                                    brokerageFirm.placeOrder(brokerageFirm.splitItem(words[1]), brokerageFirm.splitItem(words[2]), brokerageFirm.splitItem(words[3]), Integer.parseInt(brokerageFirm.splitItem(words[4])), Double.parseDouble(brokerageFirm.splitItem(words[5])));
                                    break;
                                }
                            }

                        }
                    }
                }else{
                    System.out.println("That isn't a file!");
                }
                fileIn.close(); //closing the file
            }catch (FileNotFoundException e){
                //Incase the file is not present/error
                e.printStackTrace();
            }
        }

        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        response=chooser.showOpenDialog(null);

        if (response==JFileChooser.APPROVE_OPTION){
            file1= chooser.getSelectedFile();
//    file1= new File("./src/GradedLab2/15daysdata.csv");
        try{
            fileIn= new Scanner(file1);
            double avg=0, highest=0, lowest=Integer.MAX_VALUE, maxDrawDown=0, sum=0, profit = 0, openingDayPrice=Integer.MAX_VALUE;
            int i=-1;
            if(file1.isFile()){
                while(fileIn.hasNextLine()) {
                    i++;
                    String line = fileIn.nextLine();
//                    System.out.println("line " +line);
                    String []words= line.split(",");
                    if(i!=0){
                        if(i==1) openingDayPrice=Double.parseDouble(words[3]);
                            sum+=Double.parseDouble(words[7]);
                        highest=Math.max(highest, Double.parseDouble(words[4]));
                        lowest=Math.min(lowest, Double.parseDouble(words[5]));
                        profit+=Math.abs(Double.parseDouble(words[7])-Double.parseDouble(words[3]));
//                        assuming we are having profit for every day during these 15days giving us the max returns

                    }
                }
                System.out.println("Avg value is " + (sum / i)+ "\nlowest price: "+ lowest+ "\nhighest price: "+ highest+ "\nMax Drawdown: "+ (highest-lowest));
                System.out.println("Max return is: "+ profit+ " and the max return potential is: "+ (profit/openingDayPrice)*100+ "%");
            }
            fileIn.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
}

    }

    public  String splitItem(String s) {
        return s.split(":")[1];
    }
}

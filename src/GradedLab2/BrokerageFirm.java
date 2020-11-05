package GradedLab2;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BrokerageFirm {
    static Map<String,Trader> traders= new HashMap<>(); //Map of Name of the trader and the details of the trader

    static  Trader getTrader(String name){//returns the name of the trader
        return traders.get(name);
    }

    static Map<String,Scrip> scrips= new HashMap<>(); //Map of {String of 'sector' and the scrip itself}

    static  Scrip getScrip(String name) { //returns the Scrip details using the name of the scrip
        return scrips.get(name);
    }

    public void addScrip(String ticker, String sector,  double O, double H, double L, double C , StockExchange exchange){
//        adding a new scrip into the stock exchange
        int rand= (int)(Math.random()*2);
//        StockExchange exchange=(rand==0)? NSE : BSE;
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
        scrips.values().forEach(x-> System.out.println(x.printScrip()));
    }

    public void placeOrder(String name, String type, String ticker, int quantity, double rate){
        Order order= new Order(name,type,scrips.get(ticker),quantity,rate,0,0);
        Orderbook.addOrder(order);
    }

    public void addUser(String name, int funds, Map<String,Integer>holdings){
        Trader trader=  new Trader(name,funds, holdings);
        traders.put(name, trader);
    }

    public void deleteUser(String name){
        if(traders.containsKey(name)){
            traders.remove(name);
            System.out.println("Deleted User:"+name);
        }
    }

    public void showUsers(){
        traders.values().forEach(x-> System.out.println(x));
    }


    public static void main(String[] args) throws Exception{
        BrokerageFirm brokerageFirm = new BrokerageFirm();
        StockExchange NSE= new StockExchange("NSE");
        StockExchange BSE= new StockExchange("BSE");
        StockExchange exchange=NSE;
        File file;
        Scanner fileIn;
        int response;
        JFileChooser chooser= new JFileChooser("./src/GradedLab2/");

        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//        response=chooser.showOpenDialog(null);

//        if (response==JFileChooser.APPROVE_OPTION){
        if(true) {
            file=new File("./src/GradedLab2/sample_input2.txt");//chooser.getSelectedFile();
            try{
                fileIn= new Scanner(file);
                if(file.isFile()){
                    while(fileIn.hasNextLine()) {
                        String line = fileIn.nextLine();
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
                        }

                        else {
                            String[] words = line.split(",");
                            switch (words[0].split(":")[0]) {
                                case "Add scrip": {
//                                System.out.println("Ticker: "+Double.parseDouble(brokerageFirm.splitItem(words[2])));
                                    brokerageFirm.addScrip(brokerageFirm.splitItem(words[0]), brokerageFirm.splitItem(words[1]), Double.parseDouble(brokerageFirm.splitItem(words[2])), Double.parseDouble(brokerageFirm.splitItem(words[3])), Double.parseDouble(brokerageFirm.splitItem(words[4])), Double.parseDouble(brokerageFirm.splitItem(words[5])),exchange);
                                    break;
                                }

                                case "Place order": {
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

        Orderbook.showOrderBook();
        exchange.getScripsForSector(" Pharma");


    }

    public  String splitItem(String s) {
        return s.split(":")[1];
    }
}

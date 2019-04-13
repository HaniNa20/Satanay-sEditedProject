package parking_lot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import excelReadWrite.DataBase;

public class Tenant extends Customer {

    int ID;
    int rent;
    int Park;
    
    DataBase db;
    
    Scanner n = new Scanner(System.in);
    
    FileInputStream Tenant;
    Scanner t;
    
    public Tenant(DataBase db) {
        this.db = db;
    }
    
    @Override
    void showMenu() {
        System.out.println("\n");
        System.out.println("Welcome Tenant");
        System.out.println("\n");
        System.out.println("------------------------------\n");
        
        //connectToFile("tenant.txt");
        
        int choice = 0;
        
        while(choice != 3) {
        
        System.out.println("\n1 : Show all data\n");
        System.out.println("2 : Search by ID\n");
        System.out.println("3 : Go back\n");
        
        choice = n.nextInt();
        
        switch(choice) {
            case 1:
                //connectToFile("tenant.txt");
                
                /*while(t.hasNextLine()) {
                    String str = t.nextLine();
                    //System.out.println("--------------------------------------------");
                    System.out.println(str);
                    System.out.println("--------------------------------------------");
                }*/
                        
                //t.close();
                
                db.printAllData();
                break;
                
            case 2:
                //connectToFile("tenant.txt");
                System.out.println("Enter your ID : ");
                this.ID = n.nextInt();
                //n.next();
                /*while (t.hasNextLine()) {
                String str = t.nextLine();
                if (Character.getNumericValue(str.charAt(0)) == ID) {
                    //System.out.println("--------------------------------------------");
                    System.out.println(str);
                    System.out.println("--------------------------------------------");
                    System.out.println("Thank You For Your Trust\n");
                    break;
                }
                } 
                t.close();*/
                if(ID <= db.getNumbOfRows() && ID > 0) {
                db.printRow(ID-1);
                }
                else {
                    System.out.println("ID not available");
                }
                break;
                
            case 3:
                break;
        }
        
        }
        
    }
    
    /*void connectToFile(String fileName){
        try {
            Tenant = new FileInputStream(fileName);
            t = new Scanner(Tenant);
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND.");
        }
    }*/
    
}

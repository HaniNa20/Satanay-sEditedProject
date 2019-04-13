package parking_lot;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import excelReadWrite.DataBase;

public class Parking_Lot {

    DataBase TenantDataBase = new DataBase("Tenant.xlsx", "Sheet1");
    DataBase VisitorsDataBase = new DataBase("Visitors.xlsx", "Sheet1");
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        while(true) {
        System.out.println("WELCOME");
        System.out.println();
        System.out.println("*********************************************************************** ");
        System.out.println("A I AM A TENANT");
        System.out.println("B I AM A VISITOR");
        System.out.println();
        System.out.println("*********************************************************************** ");
        System.out.println("Enter an option :");
        System.out.println("*********************************************************************** ");

        Scanner op = new Scanner(System.in);
        char option = '\0';
        option = op.next().charAt(0);
        switch (option) {
            case 'a':
            case 'A':
                Tenant tt = new Tenant(new Parking_Lot().TenantDataBase);
                tt.showMenu();
                break;
                
            case 'b':
            case 'B':

                FileWriter fv = null;
                try {
                    fv = new FileWriter("visitor.txt", true);
                    Visitor v = new Visitor(new Parking_Lot().VisitorsDataBase);
                    v.showMenu();
                } catch (Exception e) {
                    System.out.println("file not found");
                } finally {
                    fv.close();
                }
                break;
                
            case 'c':
            case 'C':
                System.exit(0);
                
            default:
                System.out.println("Invaild option!!.please enter again");
                break;

        }
        System.out.println("\n");
        }
    }
}

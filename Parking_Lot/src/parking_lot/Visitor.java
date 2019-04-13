package parking_lot;

import java.util.Scanner;
import excelReadWrite.DataBase;

public class Visitor extends Customer {

    int entering_hour;
    int leaving_hour;
    int total_cost;

    DataBase db;
    
    Scanner s = new Scanner(System.in);
    
    private boolean quit = false;
    
    public Visitor(DataBase db) {
        this.db = db;
    }

    protected void payment() {
        this.total_cost = (leaving_hour - entering_hour) * 500;
    }

    @Override
    void showMenu() {
        char option = '\0';
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Welcome Visitor");
        System.out.println();
        System.out.println("------------------------------");
        System.out.println("Please Enter Your Name:");
        super.name = s.next();
        System.out.println("\n");
        System.out.println("------------------------------");
        System.out.println("Please Enter Your Car Number:");
        super.number = s.next();
        System.out.println("\n");
        System.out.println("*********************************************************************** ");

        do {
            System.out.println("A. Entering Hour");
            System.out.println("B. Leaving Hour");
            System.out.println("C. Total cost");
            System.out.println("D. Exit");
            System.out.println("*********************************************************************** ");
            System.out.println("Enter an option");
            System.out.println("*********************************************************************** ");
            option = s.next().charAt(0);
            System.out.println();

            switch (option) {
                case 'a':
                case 'A':
                    System.out.println("------------------------------");
                    System.out.println("Enter Your Visit Beginning Hour");
                    System.out.println("------------------------------");
                    this.entering_hour = s.nextInt();
                    System.out.println();
                    break;
                
                case 'b':
                case 'B':
                    System.out.println("------------------------------");
                    System.out.println("Enter Your Visit Ending Hour");
                    System.out.println("------------------------------");
                    this.leaving_hour = s.nextInt();
                    System.out.println();
                    break;

                case 'c':
                case 'C':
                    System.out.println("------------------------------");
                    System.out.println("The Total Cost For Your Visit:");
                    System.out.println("------------------------------");
                    payment();
                    System.out.println(total_cost);
                    break;
                
                case 'd':
                case 'D':
                    payment();
                    System.out.println("***********************************************************");
                    quit = true;
                    break;

                default:
                    System.out.println("Invaild option!!.please enter again");
                    break;
            }

        } while (!quit);
        save();
        System.out.println("Thank you for your visit");
    }

    public void save() {
        Object temp[] = {name, number, entering_hour, leaving_hour, total_cost};
        db.writeInNewRow(temp);
    }
}

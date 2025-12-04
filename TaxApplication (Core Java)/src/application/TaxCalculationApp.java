package application;

import java.util.Scanner;

public class TaxCalculationApp {

    static Scanner sc = new Scanner(System.in);
    
    static VehicleMenu vm=new VehicleMenu();
    static PropertyMenu pm=new PropertyMenu();
    

    public static void main(String[] args) {
        
        // Login Page
        System.out.println("+---------------------------------+");
        System.out.println("|  WELCOME TO TAX CALCULATION APP |");
        System.out.println("+---------------------------------+");
        System.out.println("PLEASE LOGIN TO CONTINUE");
        System.out.print("USERNAME - ");
        String user = sc.nextLine();
        System.out.print("PASSWORD - ");
        String pass = sc.nextLine();
        if (user.equals("admin") && pass.equals("admin123")) {
            mainMenu();
        } else {
            System.out.println("Invalid Credentials");
        }
    }

    public static void mainMenu() {

        while (true) {
            System.out.println();
            System.out.println("1. PROPERTY TAX");
            System.out.println("2. VEHICLE TAX");
            System.out.println("3. TOTAL");
            System.out.println("4. EXIT");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    PropertyMenu.propertyMenu();
                    break;
                case "2":
                    VehicleMenu.vehicleMenu();
                    break;
                case "3":
                    displayTotal();
                    break;
                case "4":
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    break;
            }
        }
    }

    private static void displayTotal() {
        Total.displayTotal();

    }

    

    
}

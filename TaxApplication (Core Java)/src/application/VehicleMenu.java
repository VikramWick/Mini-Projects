package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import model.Vehicle;

public class VehicleMenu {
    static Scanner sc=new Scanner(System.in);
    static List<Vehicle> vehicles = new ArrayList<>();

    // ----------------------------------------------------
    // VEHICLE MODULE
    // ----------------------------------------------------
    public static void vehicleMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("1. ADD VEHICLE DETAILS");
            System.out.println("2. CALCULATE VEHICLE TAX");
            System.out.println("3. DISPLAY ALL VEHICLES");
            System.out.println("4. BACK TO MAIN MENU");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    addVehicle();
                    break;
                case "2":
                    calculateVehicleTax();
                    break;
                case "3":
                    displayVehicles();
                    break;
                case "4":
                    back = true;
                    break;
            }
        }
    }

    public static void addVehicle() {
        // Validation: 4 digit unique, non-zero. 0000 invalid.
        String regNo = "";
        while (true) {
            System.out.print("ENTER THE VEHICLE REGISTRATION NUMBER (4 digit number)-  ");
            regNo = sc.nextLine();
            if (regNo.matches("\\d{4}") && !regNo.equals("0000")) {
                // Check uniqueness
                boolean exists = false;
                for (Vehicle v : vehicles) {
                    if (v.getRegNo().equals(regNo))
                        exists = true;
                }
                if (!exists)
                    break;
            }
            else{
                System.out.println("ENTER IN CORRECT FORMAT");
            }
        }

        System.out.print("ENTER BRAND OF THE VEHICLE - ");
        String brand = sc.nextLine();

        // Validation: 120 to 300
        int velocity = 0;
        while (true) {
            try {
                System.out.print("ENTER THE MAXIMUM VELOCITY OF THE VEHICLE(120KMPH - 300KMPH) - ");
                velocity = Integer.parseInt(sc.nextLine());
                if (velocity >= 120 && velocity <= 300)
                    break;
            } catch (Exception e) {
            }
        }

        // Validation: 2 to 50
        int capacity = 0;
        while (true) {
            try {
                System.out.print("ENTER CAPACITY(NUMBER OF SEATS) OF THE VEHICLE - ");
                capacity = Integer.parseInt(sc.nextLine());
                if (capacity >= 2 && capacity <= 50)
                    break;
            } catch (Exception e) {
            }
        }

        int type = 0;
        while (true) {
            System.out.println("CHOOSE THE TYPE OF THE VEHICLE -");
            System.out.println("1. PETROL DRIVEN");
            System.out.println("2. DIESEL DRIVEN");
            System.out.println("3. CNG/LPG DRIVEN");
            try {
                String t = sc.nextLine();
                type = Integer.parseInt(t);
                if (type >= 1 && type <= 3)
                    break;
            } catch (Exception e) {
            }
        }

        // Validation: 50000 to 1000000
        double cost = 0;
        while (true) {
            try {
                System.out.print("ENTER THE PURCHASE COST OF THE VEHICLE - (50000 - 1000000) ");
                cost = Double.parseDouble(sc.nextLine());
                if (cost >= 50000 && cost <= 1000000)
                    break;
            } catch (Exception e) {
            }
        }

        vehicles.add(new Vehicle(regNo, brand, velocity, capacity, type, cost));
    }

    public static void calculateVehicleTax() {
        if (vehicles.isEmpty()) {
            System.out.println("------------------------------------");
            System.out.println(" No vehicles found to calculate Tax ");
            System.out.println("------------------------------------");
            return;
        }
        displayVehiclesHeader();
        for(Vehicle v:vehicles){
            System.out.println(v);
        }
        displayVehiclesFooter();
        System.out.println();
        System.out.print("ENTER THE REGISTRATION NO OF VEHICLE TO CALCULATE THE TAX - ");
        String regInput = sc.nextLine();

        boolean found = false;
        for (Vehicle v : vehicles) {
            if (v.getRegNo().equals(regInput)) {
                v.calculateTax();
                System.out.println("VEHICLE TAX FOR REGISTRATION NO - " + regInput + " IS " + v.getVehicleTax());
                System.out.println();
                found = true;
                break;
            }
        }
        if (!found)
            System.out.println("Registration Number Not Found.");
            System.out.println();
    }

    public static void displayVehiclesHeader(){
        String headerFormat = """
                %s
                %10s %15s %15s %15s %17s %17s %16s
                %s
                """;

        System.out.println(headerFormat.formatted(
                "=".repeat(112),
                "REG NO.",
                "BRAND",
                "MAX. VELOCITY",
                "NO. OF SEATS",
                "VEHICLE TYPE",
                "PURCHASE COST",
                "VEHICLE TAX",
                "=".repeat(112)));

    }

    public static void displayVehiclesFooter(){
        String footerFormat = """
                %s
                """;
        System.out.println(footerFormat.formatted("=".repeat(112)));
    }

    public static void displayVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("No Data Present at This Moment");
            return;
        }

        displayVehiclesHeader();
        // String headerFormat = """
        //         %s
        //         %10s %15s %15s %15s %17s %17s %16s
        //         %s
        //         """;

        // System.out.println(headerFormat.formatted(
        //         "=".repeat(112),
        //         "REG NO.",
        //         "BRAND",
        //         "MAX. VELOCITY",
        //         "NO. OF SEATS",
        //         "VEHICLE TYPE",
        //         "PURCHASE COST",
        //         "VEHICLE TAX",
        //         "=".repeat(112)));

        Collections.sort(vehicles); // Sort by RegNo
        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
        displayVehiclesFooter();
    }
    
}

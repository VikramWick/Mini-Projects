package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Property;

public class PropertyMenu {

    static Scanner sc = new Scanner(System.in);
    static int propertyIdCounter = 1;
    static List<Property> properties = new ArrayList<>();

    static void propertyMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("1. ADD PROPERTY DETAILS");
            System.out.println("2. CALCULATE PROPERTY TAX");
            System.out.println("3. DISPLAY ALL PROPERTIES");
            System.out.println("4. BACK TO MAIN MENU");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    addProperty();
                    break;
                case "2":
                    calculatePropertyTax();
                    break;
                case "3":
                    displayProperties();
                    break;
                case "4":
                    back = true;
                    break;
                default:
                    break;
            }
        }
    }

    private static void displayProperties() {
        displayPropertiesHeader();
        for (Property p : properties) {
            System.out.println(p);
        }
        displayPropertiesFooter();
    }

    private static void addProperty() {
        System.out.println("ENTER THE PROPERTY DETAILS -");

        // Validation: Non-zero positive number
        double basePrice = 0;
        while (true) {
            try {
                System.out.print("ENTER THE BASE VALUE OF LAND - ");
                basePrice = Double.parseDouble(sc.nextLine());
                if (basePrice > 0)
                    break;
            } catch (Exception e) {
            }
        }

        int area = 0;
        while (true) {
            try {
                System.out.print("ENTER THE BUILT-UP AREA OF LAND - ");
                area = Integer.parseInt(sc.nextLine());
                if (area > 0)
                    break;
            } catch (Exception e) {
            }
        }

        // Validation: Age strictly non-zero positive
        int age = 0;
        while (true) {
            try {
                System.out.print("ENTER THE AGE OF LAND IN YEARS - ");
                age = Integer.parseInt(sc.nextLine());
                if (age > 0)
                    break;
            } catch (Exception e) {
            }
        }

        // Validation: Y or N (Case insensitive)
        char city = 'N';
        while (true) {
            System.out.print("IS THE LAND LOCATED IN CITY?(Y: YES, N: NO) - ");
            String input = sc.nextLine().trim().toUpperCase();
            if (input.equals("Y") || input.equals("N")) {
                city = input.charAt(0);
                break;
            }
        }
        properties.add(new Property(propertyIdCounter++, area, basePrice, age, city));
    }

    public static int getPropCount() {
        return propertyIdCounter;
    }

    public static void displayPropertiesFooter() {
        // Text box
        String c1 = """
                %s
                """;
        System.out.println(c1.formatted("=".repeat(110)));

    }

    public static void calculatePropertyTax() {

        if (propertyIdCounter == 1) {
            System.out.println("--------------------------------------");
            System.out.println(" No Properties found to calculate Tax ");
            System.out.println("--------------------------------------");
            return;

        }

        displayPropertiesHeader();
        for (Property p : properties) {
            System.out.println(p);
        }
        displayPropertiesFooter();

        System.out.print("ENTER THE PROPERTY ID TO CALCULATE THE TAX - ");
        try {
            int id = Integer.parseInt(sc.nextLine());
            boolean found = false;
            for (Property p : properties) {
                if (p.getId() == id) {
                    p.calculateTax();
                    System.out.println("PROPERTY TAX FOR PROPERTY ID - " + id + " IS " + p.getPropertyTax());
                    found = true;
                    break;
                }
            }
            if (!found)
                System.out.println("ID Not Found.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
        }
    }

    private static void displayPropertiesHeader() {

        String header = """
                %s
                %7s %18s %18s %16s %21s %22s
                %s
                """;

        System.out.println(
                header.formatted(
                        "=".repeat(110),
                        "ID",
                        "BUILT-UP AREA",
                        "BASE PRICE",
                        "AGE(YEARS)",
                        "IN CITY",
                        "PROPERTY TAX",
                        "=".repeat(110)));
    }

}

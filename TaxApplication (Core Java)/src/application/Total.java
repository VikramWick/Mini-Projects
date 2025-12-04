package application;

import model.Property;
import model.Vehicle;

public class Total {
    public static void displayTotal() {
        // 1. Calculate Property Totals
        double totalPropTax = 0.0;
        for (Property p : PropertyMenu.properties) {
            totalPropTax += p.getPropertyTax();
        }
        int propCount = PropertyMenu.properties.size();

        String horizontalLine = "-----------------------------------------------------------------------";

        // 2. Calculate Vehicle Totals
        double totalVehicleTax = 0.0;
        for (Vehicle v : VehicleMenu.vehicles) {
            totalVehicleTax += v.getVehicleTax();
        }
        int vehicleCount = VehicleMenu.vehicles.size();

        // 3. Calculate Grand Totals
        double grandTotalTax = totalPropTax + totalVehicleTax;
        int totalCount = propCount + vehicleCount;

        System.out.println("+" + horizontalLine + "+");
        String header = String.format("| %-10s  %-20s  %-20s  %-14s|",
                                     "SR. NO.", "PARTICULAR", "QUANTITY", "TAX");
        System.out.println(header);
        System.out.println("+" + horizontalLine + "+");

        
        String propRow = String.format("| %-10d  %-20s  %-20d  %-14.2f|",
                1, "PROPERTIES", propCount, totalPropTax);
        System.out.println(propRow);
 
        String vehicleRow = String.format("| %-10d  %-20s  %-20d  %-14.2f|",
                   2, "VEHICLES", vehicleCount, totalVehicleTax);
        System.out.println(vehicleRow);
        
        System.out.println("+" + horizontalLine + "+");
        
        String totalRow = String.format("| %-10s  %-20s  %-20d  %-14.2f|",
                "TOTAL", "----------", totalCount, grandTotalTax);
        System.out.println(totalRow);
        
        System.out.println("+" + horizontalLine + "+");
    }
}

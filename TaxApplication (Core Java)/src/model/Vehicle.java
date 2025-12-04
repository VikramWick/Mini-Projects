package model;

public class Vehicle implements Comparable<Vehicle> {
    private String regNo;
    private String brand;
    private int maxVelocity;
    private int capacity;
    private int typeChoice; // 1=Petrol, 2=Diesel, 3=CNG
    private double purchaseCost;
    private double vehicleTax;

    public Vehicle(String regNo, String brand, int maxVelocity, int capacity, int typeChoice, double purchaseCost) {
        this.regNo = regNo;
        this.brand = brand;
        this.maxVelocity = maxVelocity;
        this.capacity = capacity;
        this.typeChoice = typeChoice;
        this.purchaseCost = purchaseCost;
        this.vehicleTax = 0.0;
    }

    public void calculateTax() {
        // Formula from text:
        // Petrol: Velocity + Capacity + 10% of Cost
        // Diesel: Velocity + Capacity + 11% of Cost
        // CNG: Velocity + Capacity + 12% of Cost

        double percentage = 0.0;
        switch (typeChoice) {
            case 1:
                percentage = 0.10;
                break;
            case 2:
                percentage = 0.11;
                break;
            case 3:
                percentage = 0.12;
                break;
        }

        this.vehicleTax = this.maxVelocity + this.capacity + (this.purchaseCost * percentage);
    }

    public String getTypeString() {
        if (typeChoice == 1)
            return "PETROL";
        if (typeChoice == 2)
            return "DIESEL";
        return "CNG/LPG";
    }

    public String getRegNo() {
        return regNo;
    }

    public double getVehicleTax() {
        return vehicleTax;
    }

    @Override
    public int compareTo(Vehicle o) {
        return this.regNo.compareTo(o.regNo);
    }

    @Override
    public String toString() {
        return String.format("%10s %16s %15d %15d %17s %17.2f %16.2f",
                regNo, brand, maxVelocity, capacity, getTypeString(), purchaseCost, vehicleTax);
    }
}
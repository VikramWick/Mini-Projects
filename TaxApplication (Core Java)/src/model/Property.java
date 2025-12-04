package model;

public class Property implements Comparable<Property> {
    private int id;
    private int builtUpArea;
    private double basePrice;
    private int age;
    private char inCity;
    private double propertyTax;

    public double getPropertyTax() {
        return propertyTax;
    }

    public int getId() {
        return id;
    }

    public Property(int id, int builtUpArea, double basePrice, int age, char inCity) {
        this.id = id;
        this.builtUpArea = builtUpArea;
        this.basePrice = basePrice;
        this.age = age;
        this.inCity = inCity;
        this.propertyTax = 0.0;
    }

    public void calculateTax() {
        double coreTax = this.builtUpArea * this.age * this.basePrice;

        if (Character.toUpperCase(this.inCity) == 'Y') {
            this.propertyTax = coreTax + (0.5 * this.builtUpArea);
        } else {
            this.propertyTax = coreTax;
        }
    }

    @Override
    public int compareTo(Property o) {
        return Integer.compare(this.id, o.id);
    }

    @Override
    public String toString() {
        return String.format("%7d %18d %18.2f %16d %21s %18.2f",
                id, builtUpArea, basePrice, age, Character.toUpperCase(inCity), propertyTax);
    }
}

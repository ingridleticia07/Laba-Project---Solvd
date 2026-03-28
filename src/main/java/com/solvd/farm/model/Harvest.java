package main.java.com.solvd.farm.model;

import main.java.com.solvd.farm.interfaces.Sellable;

public class Harvest extends FarmProduct implements Sellable {
    private int totalYield;
    private double pricePerKg;

    public Harvest(String name, int totalYield, double pricePerKg) {
        super(name);
        this.totalYield = totalYield;
        this.pricePerKg = pricePerKg;
    }

    @Override
    public double calculateRevenue() {
        return totalYield * pricePerKg;
    }

    public int getTotalYield() {
        return totalYield;
    }

    public void setTotalYield(int totalYield) {
        this.totalYield = totalYield;
    }
}


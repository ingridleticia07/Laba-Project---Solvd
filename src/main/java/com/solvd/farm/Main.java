package main.java.com.solvd.farm;

import main.java.com.solvd.farm.exception.InsufficientFundsException;
import main.java.com.solvd.farm.exception.InsufficientResourcesException;
import main.java.com.solvd.farm.exception.NoProfitException;
import main.java.com.solvd.farm.interfaces.Costable;
import main.java.com.solvd.farm.interfaces.Feedable;
import main.java.com.solvd.farm.interfaces.Maintainable;
import main.java.com.solvd.farm.interfaces.Sellable;
import main.java.com.solvd.farm.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        Farmer farmer = new Farmer("John", 100000);

        Farm farm = new Farm("Orange County", 100.5, "Canadá", farmer);

        Crop corn = new Crop("Corn", 30, 200);
        Crop wheat = new Crop("Wheat", 40, 300);

        Animal chicken = new Animal("Chicken", 40, 2);
        Animal pig = new Animal("Pork", 12, 2);
        Animal cow = new Animal("Cow", 30, 4);

        Tool shovel = new Tool("Shovel", 15, 5);
        Tool tractor = new Tool("Tractor", 1000, 1);

        FoodSupply animalFeed = new FoodSupply("Animal Feed", 30000, 0.8);
        FoodSupply grainStorage = new FoodSupply("Grain", 900, 1.5);

        Product eggs = new Product("Eggs", 10, 500);
        Product milk = new Product("Milk", 3, 250);
        Product pork = new Product("Pork", 5, 100);

        Season autumn = new Season("Autumn", 120);

        Expense fertilizer = new Expense("Fertilizer", 400);
        Expense equipmentRepair = new Expense("Equipment Repair", 250);

        Harvest cornHarvest = new Harvest("Corn", 1200, 2);
        Harvest wheatHarvest = new Harvest("Wheat", 900, 3);

        Costable[] crops = {corn, wheat};
        Costable[] foodSupplies = {animalFeed, grainStorage};
        Feedable[] animals = {cow, chicken, pig};
        Maintainable[] maintainables = {shovel, tractor, fertilizer, equipmentRepair};
        Sellable[] products = {eggs, milk, pork};
        Sellable[] harvests = {cornHarvest, wheatHarvest};

        logger.info(farm.generateReport());

        try {
            farm.processSeason(
                    autumn,
                    crops,
                    foodSupplies,
                    animals,
                    animalFeed,
                    maintainables,
                    products,
                    harvests
            );

            logger.info("Season processed successfully!");
            logger.info("Remaining money: {}", farmer.getMoney());

        } catch (InsufficientResourcesException e) {
            logger.error("Resource error: {}", e.getMessage());

        } catch (InsufficientFundsException e) {
            logger.error("Financial error: {}", e.getMessage());

        } catch (NoProfitException e) {
            logger.warn("Warning: {}", e.getMessage());

        } catch (Exception e) {
            logger.error("Unexpected error: {}", e.getMessage());
        }
    }

}

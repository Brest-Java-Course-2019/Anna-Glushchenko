package com.epam.brest.cources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CalculatorImpl implements Calculator {

    @Override
    public void CalculateCost(double weight, double distance) {


        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream("src/main/resources/prices.properties");
            property.load(fis);

            double min = Double.parseDouble(property.getProperty("min.weight"));
            double medium = Double.parseDouble(property.getProperty("medium.weight"));
            double max = Double.parseDouble(property.getProperty("max.weight"));

            if (weight < 5) {
                double cost = weight * distance * min;
                System.out.printf("Delivery cost: %.2f", cost);
            }
            if (weight >= 5 && weight < 10) {
                double cost = weight * distance * medium;
                System.out.printf("Delivery cost: %.2f", cost);
            }
            if (weight >= 10) {
                double cost = weight * distance * max;
                System.out.printf("Delivery cost: %.2f", cost);
            }

        } catch (IOException e) {
            System.err.println("File not found!");
        }

    }
}

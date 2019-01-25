package com.epam.brest.cources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CalculatorImpl implements Calculator {

    @Override
    public double CalculateCost(double weight, double distance) {

        FileInputStream fis;
        Properties property = new Properties();

        double price = 0;
        try {
            fis = new FileInputStream("src/main/resources/prices.properties");
            property.load(fis);


            double min = Double.parseDouble(property.getProperty("min.weight"));
            double medium = Double.parseDouble(property.getProperty("medium.weight"));
            double max = Double.parseDouble(property.getProperty("max.weight"));

            if (weight < 5) {
                price = min;
            }
            if (weight >= 5 && weight < 10) {
                price = medium;
            }
            if (weight >= 10) {
                price = max;
            }

        } catch (IOException e) {
            System.err.println("File not found!");
        }

        return weight * distance * price;
    }
}

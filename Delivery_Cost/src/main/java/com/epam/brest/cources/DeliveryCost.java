package com.epam.brest.cources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class DeliveryCost {

    public static void main(String[] args) {

        calculate();
    }

    private static void calculate() {
        Scanner in = new Scanner(System.in);

        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream("/home/anna/Documents/Projects/Anna-Glushchenko/Delivery_Cost/src/main/resources/prices.properties");
            property.load(fis);

            double min = Double.parseDouble(property.getProperty("min.weight"));
            double medium = Double.parseDouble(property.getProperty("medium.weight"));
            double max = Double.parseDouble(property.getProperty("max.weight"));

            System.out.println("Weight: ");
            double weight = in.nextFloat();
            if (weight <= 0) {
                System.out.print("This data must be greater than 0");
                return;
            }
            System.out.println("Distance: ");
            double distance = in.nextFloat();
            if (distance <= 0) {
                System.out.print("this data must be greater than 0");
                return;
            }

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

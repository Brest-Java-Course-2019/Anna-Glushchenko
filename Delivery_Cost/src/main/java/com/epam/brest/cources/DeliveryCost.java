package com.epam.brest.cources;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Scanner;

public class DeliveryCost {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in, "UTF_8");

        Delivery delivery = new Delivery();

        System.out.println("Weight: ");
        double weight = in.nextDouble();
        if (weight <= 0) {
            System.out.print("This data must be greater than 0");
            return;
        }
        System.out.println("Distance: ");
        double distance = in.nextDouble();
        if (distance <= 0) {
            System.out.print("this data must be greater than 0");
            return;
        }

        delivery.setWeight(weight);
        delivery.setDistance(distance);

        CalculatorImpl calculator = new CalculatorImpl();
        double price = Price.getPrice(delivery.getWeight());
        double cost = calculator.calculateCost(delivery.getWeight(), delivery.getDistance(), price);

       // System.out.println(delivery);
        LOGGER.info("Delivery: {}", delivery);
        //System.out.printf("Delivery cost: %.2f", cost);
        LOGGER.info("Cost: {}", cost);
    }
}

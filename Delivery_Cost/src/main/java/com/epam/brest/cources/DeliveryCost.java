package com.epam.brest.cources;

import java.util.Scanner;

public class DeliveryCost {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

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
        calculator.CalculateCost(delivery.getWeight(), delivery.getDistance());

    }
}

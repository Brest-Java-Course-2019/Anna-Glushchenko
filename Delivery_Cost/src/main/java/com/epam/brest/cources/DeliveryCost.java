package com.epam.brest.cources;

import java.util.Scanner;

public class DeliveryCost {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Weight: ");
        double weight = in.nextFloat();
        System.out.println("Distance: ");
        double distance = in.nextFloat();
        double price = 1.23;
        double cost = weight*distance*price;
        System.out.printf("Delivery cost :  %.2f", cost);

    }

}

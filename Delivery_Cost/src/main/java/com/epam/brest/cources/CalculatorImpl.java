package com.epam.brest.cources;

public class CalculatorImpl implements Calculator {

    @Override
    public double calculateCost(double weight, double distance, double price) {

        return weight * distance * price;
    }
}

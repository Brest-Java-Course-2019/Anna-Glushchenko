package com.epam.brest.cources;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.io.IOException;

class CalculatorImplTest {

    private Delivery delivery = new Delivery();

    @BeforeEach
    void init() {

        System.out.println("@BeforeEach");
        delivery.setDistance(5.3);
        delivery.setWeight(1.2);
    }

    @Test
    void testCalculation() throws IOException {

        CalculatorImpl calculator = new CalculatorImpl();
        double cost = calculator.calculateCost(delivery.getWeight(), delivery.getDistance(), Price.getPrice(1.2));

        Assertions.assertEquals(5.4696, cost);
    }

    @Test
    void testCalculationException() throws IOException {

        CalculatorImpl calculator = new CalculatorImpl();
        double cost = calculator.calculateCost(delivery.getWeight(), delivery.getDistance(), Price.getPrice(1.2));

        Assertions.assertThrows(AssertionFailedError.class, () -> {
            Assertions.assertEquals(7.589, cost);
        });
    }

    @Test
    void testGetTrueMinPrice() throws IOException {

        double price = Price.getPrice(delivery.getWeight());

        Assertions.assertEquals(0.86, price);
    }

    @Test
    void testGetTrueMediumPrice() throws IOException {

        delivery.setWeight(5.2);
        double price = Price.getPrice(delivery.getWeight());

        Assertions.assertEquals(1.27, price);
    }

    @Test
    void testGetTrueMaxPrice() throws IOException {

        delivery.setWeight(15.8);
        double price = Price.getPrice(delivery.getWeight());

        Assertions.assertEquals(1.62, price);
    }

    @Test
    void testGetFalsePrice() throws IOException {

        double price = Price.getPrice(delivery.getWeight());

        Assertions.assertThrows(AssertionFailedError.class, () -> {
            Assertions.assertEquals(1.2, price);
        });
    }

    @Test
    void testTrueInputValue() {

        Assertions.assertTrue( delivery.getWeight() > 0 && delivery.getDistance() > 0);
    }


}

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
}

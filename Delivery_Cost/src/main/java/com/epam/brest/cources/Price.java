package com.epam.brest.cources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

class Price {
    private static final double MIN_WEIGHT = 5;
    private static final double MEDIUM_WEIGHT = 10;
    private static final double MAX_WEIGHT = 15;

    static double getPrice(double weight) throws IOException {

        Properties property = new Properties();
        FileInputStream fis = null;
        double price = 0;
        try {
            fis = new FileInputStream("src/main/resources/prices.properties");
            property.load(fis);

            double min = Double.parseDouble(property.getProperty("min.weight"));
            double medium = Double.parseDouble(property.getProperty("medium.weight"));
            double max = Double.parseDouble(property.getProperty("max.weight"));

            if (weight < MIN_WEIGHT) {
                price = min;
            }
            if (weight >= MIN_WEIGHT && weight < MEDIUM_WEIGHT) {
                price = medium;
            }
            if (weight >= MAX_WEIGHT) {
                price = max;
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return price;
    }
}

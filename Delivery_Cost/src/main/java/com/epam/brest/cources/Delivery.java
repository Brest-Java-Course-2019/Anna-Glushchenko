package com.epam.brest.cources;

class Delivery {

    private double weight;
    private double distance;

    Delivery() {
    }

    double getWeight() {
        return weight;
    }

    void setWeight(double weight) {
        this.weight = weight;
    }

    double getDistance() {
        return distance;
    }

    void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "weight=" + weight +
                ", distance=" + distance +
                '}';
    }
}

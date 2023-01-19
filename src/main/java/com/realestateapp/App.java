package com.realestateapp;

import java.math.BigDecimal;

public class App {

    public static void main(String[] args) {
        System.out.println(new RandomApartmentGenerator().generate());
        System.out.println(new RandomApartmentGenerator(10.00,new BigDecimal(20.00)).generate());
    }
}

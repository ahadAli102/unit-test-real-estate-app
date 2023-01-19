package com.realestateapp;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertAll;

class RandomApartmentGeneratorTest {
    protected static Random random = new Random();
    /*
    RandomApartmentGenerator, which generates an apartment with a random price and area.
    If you invoke the constructor with no parameters, the default values are used: minimum area of 30.0 square meters
    and minimum price per square meter of 3000.0. You can also specify your own minimum area and price.
    In either case, the maximum values are: mimum values * 4.0
     */

    /*
    a. should_GenerateCorrectApartment_When_DefaultMinAreaMinPrice
    b. should_GenerateCorrectApartment_When_CustomMinAreaMinPrice
   */

    @RepeatedTest(10)
    @DisplayName("When creating random apartment with no argument")
    void testRandomApartmentGeneratorWithNoArgument(){
        Apartment generatedApartment = new RandomApartmentGenerator().generate();
        assertAll(
                () -> Assertions.assertTrue(generatedApartment.getArea() >= 30.0),
                () -> Assertions.assertTrue(generatedApartment.getPrice().doubleValue() >= 3000.0)
        );
    }

    @RepeatedTest(100)
    @DisplayName("When creating random apartment with valid argument")
    void testRandomApartmentGeneratorWithValidArgument(){
        double maxForArea = 30.0*4;
        double maxForPrice = 3000.0*4;

        double randomAreaValue = randomInRange(-100.00,1000);
        double randomPriceValue = randomInRange(-100.00,100000);
        Apartment generatedApartment = new RandomApartmentGenerator(randomAreaValue,new BigDecimal(randomPriceValue)).generate();
        assertAll(
                () -> Assertions.assertTrue(generatedApartment.getArea() <= maxForArea),
                () -> Assertions.assertTrue(generatedApartment.getPrice().doubleValue() <= maxForPrice)
        );
    }


    public static double randomInRange(double min, double max) {
        double range = max - min;
        double scaled = random.nextDouble() * range;
        double shifted = scaled + min;
        return shifted; // == (rand.nextDouble() * (max-min)) + min;
    }
}

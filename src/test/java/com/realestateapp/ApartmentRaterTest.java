package com.realestateapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ApartmentRaterTest {

    @Nested
    @DisplayName("When rating to apartment")
    class RateApartmentTest {
        @Test
        @DisplayName("with 0 square feet area")
        void testRateApartment(){
            Apartment apartment = new Apartment(0,new BigDecimal(10.00));
            int expected = -1;
            int actual = ApartmentRater.rateApartment(apartment);
            Assertions.assertEquals(expected,actual);
        }

        @Test
        @DisplayName("with negative pricing")
        void testRateApartment1(){
            Apartment apartment = new Apartment(100.00,new BigDecimal(-10.00));
            int min = -1;
            int max = 2;
            int actual = ApartmentRater.rateApartment(apartment);
            Assertions.assertTrue(((actual<min) || (actual>max)));
        }

        @Test
        @DisplayName("with negative area")
        void testRateApartment2(){
            Apartment apartment = new Apartment(-1,new BigDecimal(10.00));
            int min = -1;
            int max = 2;
            int actual = ApartmentRater.rateApartment(apartment);
            Assertions.assertTrue(((actual<min) || (actual>max)));
        }

        @Test
        @DisplayName("with positive values")
        void testRateApartment3(){
            Apartment apartment = new Apartment(100,new BigDecimal(10.00));
            int min = -1;
            int max = 2;
            int actual = ApartmentRater.rateApartment(apartment);
            Assertions.assertTrue(((actual>=min) && (actual<=max)));
        }
        @Test
        @DisplayName("with 0 price")
        void testRateApartment4(){
            Apartment apartment = new Apartment(100,new BigDecimal(0.00));
            int min = -1;
            int max = 2;
            int actual = ApartmentRater.rateApartment(apartment);
            Assertions.assertTrue(((actual<min) || (actual>max)));
        }
    }

    @Nested
    @DisplayName("When calculating average rating of apartment")
    class AverageRatingTest{

        @Test
        @DisplayName("with wrong apartments list")
        void testCalculateAverageRating1(){
            List<Apartment> apartments = new ArrayList<>();
            apartments.add(new Apartment(-1.0,new BigDecimal(1.00)));
            apartments.add(new Apartment(1.0,new BigDecimal(-1.00)));
            apartments.add(new Apartment(1.0,new BigDecimal(1.00)));
            assertThrows(RuntimeException.class,()-> ApartmentRater.calculateAverageRating(apartments));
        }

        @Test
        @DisplayName("with empty apartments list")
        void testCalculateAverageRating2(){
            List<Apartment> apartments = new ArrayList<>();
            assertThrows(RuntimeException.class,()-> ApartmentRater.calculateAverageRating(apartments));
        }

        @Test
        @DisplayName("with null apartments list")
        void testCalculateAverageRating3(){
            List<Apartment> apartments = null;
            Assertions.assertDoesNotThrow(RuntimeException.class,()-> ApartmentRater.calculateAverageRating(apartments));
        }

        @Test
        @DisplayName("with correct apartments list")
        void testCalculateAverageRating4(){
            List<Apartment> apartments = new ArrayList<>();
            apartments.add(new Apartment(100.0,new BigDecimal(1.00)));

            System.out.println(ApartmentRater.calculateAverageRating(apartments));
            Assertions.assertTrue((ApartmentRater.calculateAverageRating(apartments))>0);
        }
    }

}

package model.entity;

import java.time.LocalDate;

public class Car extends Vehicle {
    private static final int DAILY_RENTAL_COST_LESS_THAN_WEEK = 20;
    private static final int DAILY_RENTAL_COST_MORE_THAN_WEEK = 15;
    private static final double DAILY_INSURANCE_COST_PERCENTAGE = 0.0001;
    private static final double DISCOUNT_FOR_HIGH_SAFETY_RATING = 0.90; // percentage
    private int safetyRating;

    public Car(String brand, String model, double value, int reservedRentalDays, int actualRentalDays, Client client,
               int safetyRating, LocalDate startDate, LocalDate endDate, LocalDate actualReturnDate) {
        super(brand, model, value, reservedRentalDays, actualRentalDays, client, startDate, endDate, actualReturnDate);
        this.setSafetyRating(safetyRating);
    }


    public int getSafetyRating() {
        return safetyRating;
    }

    public void setSafetyRating(int safetyRating) {
        if (safetyRating < 1 || safetyRating > 5) {
            throw new IllegalArgumentException("Safety rating must be between 1 and 5");
        }
        this.safetyRating = safetyRating;
    }


    @Override
    public double getDailyRentalCost(int rentalDays) {
        return (rentalDays < 7) ? DAILY_RENTAL_COST_LESS_THAN_WEEK : DAILY_RENTAL_COST_MORE_THAN_WEEK;
    }

    @Override
    public double getDailyInsuranceCost() {
        double baseInsuranceCost = getValue() * DAILY_INSURANCE_COST_PERCENTAGE;

        if (this.meetsInsuranceCriteria()) {
            return baseInsuranceCost * DISCOUNT_FOR_HIGH_SAFETY_RATING;
        } else {
            return baseInsuranceCost;
        }
    }

    @Override
    public boolean meetsInsuranceCriteria() {
        return getSafetyRating() >= 4;
    }

    @Override
    public double getDailyInitInsurance() {
        return this.getValue() * DAILY_INSURANCE_COST_PERCENTAGE;
    }
}

package model.entity;

import java.time.LocalDate;

public class CargoVan extends Vehicle {
    private static final int DAILY_RENTAL_COST_LESS_THAN_WEEK = 50;
    private static final int DAILY_RENTAL_COST_MORE_THAN_WEEK = 40;
    private static final double DAILY_INSURANCE_COST_PERCENTAGE = 0.0003;
    private static final double DISCOUNT_FOR_EXPERIENCED_DRIVERS = 0.85;
    private int yearsExperience;

    public CargoVan(String brand, String model, double value, int reservedRentalDays, int actualRentalDays,
                    Client client,int yearsExperience, LocalDate startDate, LocalDate endDate, LocalDate actualReturnDate) {
        super(brand, model, value, reservedRentalDays, actualRentalDays, client, startDate, endDate, actualReturnDate);
        this.setYearsExperience(yearsExperience);
    }


    public int getYearsExperience() {
        return yearsExperience;
    }

    public void setYearsExperience(int yearsExperience) {
        this.yearsExperience = yearsExperience;
    }

    @Override
    public double getDailyRentalCost(int rentalDays) {
        return (rentalDays < 7) ? DAILY_RENTAL_COST_LESS_THAN_WEEK : DAILY_RENTAL_COST_MORE_THAN_WEEK;
    }

    @Override
    public double getDailyInsuranceCost() {
        double baseInsuranceCost = getValue() * DAILY_INSURANCE_COST_PERCENTAGE;

        if (this.meetsInsuranceCriteria()) {
            return baseInsuranceCost * DISCOUNT_FOR_EXPERIENCED_DRIVERS;
        } else {
            return baseInsuranceCost;
        }
    }

    @Override
    public boolean meetsInsuranceCriteria() {
        return getYearsExperience() > 5;
    }

    @Override
    public double getDailyInitInsurance() {
        return this.getValue() * DAILY_INSURANCE_COST_PERCENTAGE; // no need to check if it meets the criteria
    }
}

package model.entity;


import java.time.LocalDate;

public class Motorcycle extends Vehicle {
    private static final int DAILY_RENTAL_COST_LESS_THAN_WEEK = 15;
    private static final int DAILY_RENTAL_COST_MORE_THAN_WEEK = 10;
    private static final double DAILY_INSURANCE_COST_PERCENTAGE = 0.0002;
    private static final double MARKUP_FOR_YOUNG_RIDERS = 1.20;
    private int age;

    public Motorcycle(String brand, String model, double value, int reservedRentalDays, int actualRentalDays,
                      Client client, int age, LocalDate startDate, LocalDate endDate, LocalDate actualReturnDate) {
        super(brand, model, value, reservedRentalDays, actualRentalDays, client, startDate, endDate, actualReturnDate);
        this.setAge(age);
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public double getDailyRentalCost(int rentalDays) {
        return (rentalDays < 7) ? DAILY_RENTAL_COST_LESS_THAN_WEEK : DAILY_RENTAL_COST_MORE_THAN_WEEK;
    }

    @Override
    public double getDailyInsuranceCost() {
        double baseInsuranceCost = getValue() * DAILY_INSURANCE_COST_PERCENTAGE;

        if (this.meetsInsuranceCriteria()) {
            return baseInsuranceCost * MARKUP_FOR_YOUNG_RIDERS;
        } else {
            return baseInsuranceCost;
        }
    }

    @Override
    public boolean meetsInsuranceCriteria() {
        return getAge() < 25;
    }

    @Override
    public double getDailyInitInsurance() {
        return this.getValue() * DAILY_INSURANCE_COST_PERCENTAGE;
    }


}

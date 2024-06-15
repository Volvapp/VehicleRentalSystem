package model.interfaces;

public interface Rentable {
    double getDailyRentalCost(int rentalDays);

    double calculateRentalCost(int reservedRentalDays, int actualRentalDays);
}
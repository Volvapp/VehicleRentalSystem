package model.interfaces;

public interface Insurable {
    double getDailyInsuranceCost();

    boolean meetsInsuranceCriteria();

    double calculateInsuranceCost(int reservedRentalDays, int actualRentalDays);

    double getDailyInitInsurance();
}
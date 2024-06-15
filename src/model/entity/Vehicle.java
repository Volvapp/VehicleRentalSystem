package model.entity;

import model.interfaces.Insurable;
import model.interfaces.Rentable;

import java.time.LocalDate;

public abstract class Vehicle implements Rentable, Insurable {
    private String brand;
    private String model;
    private double value;
    private Client client;
    private int reservedRentalDays;
    private int actualRentalDays;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate actualReturnDate;

    public Vehicle(String brand, String model, double value, int reservedRentalDays, int actualRentalDays, Client client, LocalDate startDate, LocalDate endDate, LocalDate actualReturnDate) {
        this.setBrand(brand);
        this.setModel(model);
        this.setValue(value);
        this.setReservedRentalDays(reservedRentalDays);
        this.setActualRentalDays(actualRentalDays);
        this.setClient(client);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setActualReturnDate(actualReturnDate);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getReservedRentalDays() {
        return reservedRentalDays;
    }

    public void setReservedRentalDays(int reservedRentalDays) {
        this.reservedRentalDays = reservedRentalDays;
    }

    public int getActualRentalDays() {
        return actualRentalDays;
    }

    public void setActualRentalDays(int actualRentalDays) {
        this.actualRentalDays = actualRentalDays;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getActualReturnDate() {
        return actualReturnDate;
    }

    public void setActualReturnDate(LocalDate actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    public abstract double getDailyRentalCost(int rentalDays);

    public abstract double getDailyInsuranceCost();

    public abstract boolean meetsInsuranceCriteria();

    public abstract double getDailyInitInsurance();

    public double calculateRentalCost(int reservedRentalDays, int actualRentalDays) {
        double totalCost = 0.0;
        double dailyRentalCost = getDailyRentalCost(reservedRentalDays);

        if (areActualDaysGreaterThan(actualRentalDays, reservedRentalDays)) {
            totalCost += dailyRentalCost * actualRentalDays + (dailyRentalCost / 2.0) * (reservedRentalDays - actualRentalDays);
        } else {
            totalCost += dailyRentalCost * actualRentalDays;
        }

        return totalCost;
    }

    public double calculateInsuranceCost(int reservedRentalDays, int actualRentalDays) {
        double totalCost = 0.0;
        double dailyInsuranceCost = getDailyInsuranceCost();

        if (areActualDaysGreaterThan(actualRentalDays, reservedRentalDays)) {
            totalCost += dailyInsuranceCost * actualRentalDays;
        } else {
            totalCost += dailyInsuranceCost * reservedRentalDays;
        }

        return totalCost;
    }

    private static boolean areActualDaysGreaterThan(int reservedRentalDays, int actualRentalDays) {
        return actualRentalDays > reservedRentalDays;
    }

    public double calculateTotalSum() {
        return calculateRentalCost(reservedRentalDays, actualRentalDays) + calculateInsuranceCost(reservedRentalDays, actualRentalDays);
    }

    public String generateInvoice() {
        StringBuilder sb = new StringBuilder();
        sb.append("XXXXXXXXXX\n")
                .append(String.format("Date: %s\n", LocalDate.now().plusDays(actualRentalDays)))
                .append(String.format("Customer name: %s %s\n", client.getFirstName(), client.getLastName()))
                .append(String.format("Rented vehicle: %s %s\n\n", this.getBrand(), this.getModel()))
                .append(String.format("Reservation start date: %s\n", this.getStartDate()))
                .append(String.format("Reservation end date: %s\n", this.getEndDate()))
                .append(String.format("Reserved rental days: %d days\n\n", reservedRentalDays))
                .append(String.format("Actual return date: %s\n", this.getActualReturnDate()))
                .append(String.format("Actual rental days: %d days\n\n", actualRentalDays))
                .append(String.format("Rental cost per day: $%.2f\n", this.getDailyRentalCost(actualRentalDays))); // makes much more sense to be actual rental days

        if (meetsInsuranceCriteria()) {
            sb.append(String.format("Initial insurance per day: $%.2f\n", this.getDailyInitInsurance()));
            if (this.getDailyInsuranceCost() - this.getDailyInitInsurance() > 0) {
                sb.append(String.format("Insurance addition per day: $%.2f\n", this.getDailyInsuranceCost() - this.getDailyInitInsurance()));
            } else {
                sb.append(String.format("Insurance discount per day: $%.2f\n", this.getDailyInitInsurance() - this.getDailyInsuranceCost()));
            }
        }
        sb.append(String.format("Insurance per day: $%.2f\n\n", this.getDailyInsuranceCost()));

        if (reservedRentalDays != actualRentalDays) {
            sb.append(String.format("Early return discount for rent: $%.2f\n", calculateRentalDiscount(reservedRentalDays, actualRentalDays)))
                    .append(String.format("Early return discount for insurance: $%.2f\n", calculateInsuranceDiscount(reservedRentalDays, actualRentalDays)));
        }
        sb.append(String.format("Total rent: $%.2f\n", calculateRentalCost(reservedRentalDays, actualRentalDays)))
                .append(String.format("Total insurance: $%.2f\n", calculateInsuranceCost(reservedRentalDays, actualRentalDays)))
                .append(String.format("Total: %.2f\n", calculateTotalSum()))
                .append("XXXXXXXXXX\n\n");


        return sb.toString();
    }

    private double calculateInsuranceDiscount(int reservedRentalDays, int actualRentalDays) {
        return this.getDailyInsuranceCost() * reservedRentalDays - this.calculateInsuranceCost(reservedRentalDays, actualRentalDays);
    }

    private double calculateRentalDiscount(int reservedRentalDays, int actualRentalDays) {
        return this.getDailyRentalCost(reservedRentalDays) * reservedRentalDays - this.calculateRentalCost(reservedRentalDays, actualRentalDays);
    }
}

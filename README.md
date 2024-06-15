# Vehicle Rental System

This Java project simulates a vehicle rental system where clients can rent different types of vehicles (Cars, Motorcycles, Cargo Vans). It calculates rental costs and insurance based on specific criteria for each vehicle type.

## Usage

1. **Main Class**: `Main.java`
   - This class demonstrates the usage of the Vehicle Rental System.
   - It creates instances of different vehicles and prints their rental invoices.

2. **Entities**:
   - **Vehicle.java**: Abstract class representing a generic vehicle with rental and insurance functionalities.
   - **Car.java**: Extends Vehicle, implements specific rental and insurance costs for cars.
   - **Motorcycle.java**: Extends Vehicle, implements specific rental and insurance costs for motorcycles.
   - **CargoVan.java**: Extends Vehicle, implements specific rental and insurance costs for cargo vans.
   - **Client.java**: Represents a client renting a vehicle.

3. **Interfaces**:
   - **Rentable.java**: Interface defining methods for rental costs.
   - **Insurable.java**: Interface defining methods for insurance costs and criteria.

## Features

- **Dynamic Rental Calculation**: Calculates rental costs based on reserved and actual rental days.
- **Insurance Calculation**: Calculates insurance costs with discounts based on specific criteria for each vehicle type.
- **Invoice Generation**: Generates an invoice detailing rental and insurance costs for each vehicle rented.

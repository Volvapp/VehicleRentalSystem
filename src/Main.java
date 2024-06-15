import model.entity.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Client c1 = new Client("John", "Doe");
        Client c2 = new Client("Jane", "Doe");
        Client c3 = new Client("Jack", "Doe");
        Vehicle car = new Car("Mitsubishi", "Mirage", 15000.00, 10,
                10, c1, 3,
                LocalDate.of(2024, 6, 3),
                LocalDate.of(2024, 6, 13),
                LocalDate.of(2024, 6, 13));

        Vehicle motorcycle = new Motorcycle("Triumph", "Tiger Sport 660", 10000, 10,
                10, c2, 20,
                LocalDate.of(2024, 6, 3),
                LocalDate.of(2024, 6, 13),
                LocalDate.of(2024, 6, 13));

        Vehicle cargoVan = new CargoVan("Citroen", "Jumper", 20000, 15,
                10, c3, 8,
                LocalDate.of(2024, 6, 3),
                LocalDate.of(2024,6,18),
                LocalDate.of(2024,6,13));

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(car);
        vehicles.add(motorcycle);
        vehicles.add(cargoVan);

        for (Vehicle vehicle : vehicles) {
            System.out.print(vehicle.generateInvoice());
        }


        System.out.println();

    }
}

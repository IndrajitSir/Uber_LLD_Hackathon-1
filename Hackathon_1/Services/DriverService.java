package Hackathon_1.Services;

import Hackathon_1.Factory.VehicleFactory;
import Hackathon_1.Manager.DriverManager;
import Hackathon_1.NotificationSystem.*;
import Hackathon_1.Strategy.IDriverService;
import Hackathon_1.models.*;

interface ILocatable {
    String getCurrentLocation(Driver driver);
}
public class DriverService implements ILocatable, IDriverService {
    private final DriverManager driverManager = DriverManager.getInstance();
    private final VehicleFactory vehicleFactory = new VehicleFactory();
    // Create NotificationService.
    private final NotificationSystem notificationSystem = NotificationSystem.getInstance();

    @Override
    public String getCurrentLocation(Driver driver) {
        return driver.getLocation();
    }

    @Override
    public boolean acceptRide(int driverID, Rider rider) {
        Driver driver = (Driver) driverManager.driverMap.get(driverID);
        // Create a notification with decorators.
        if (driver.isAvailable()) {
            driver.setAvailable(false);
            notificationSystem.notifyRiderViaEmail(rider.getEmail(), "Driver having id: " + driverID + " accepted ride request!");
        }
        return true;
    }
    @Override
    public boolean acceptRide(int driverID, Rider rider, String scheduleTime) {
        Driver driver = (Driver) driverManager.driverMap.get(driverID);
        // Create a notification with decorators.
        driver.setAvailable(false);
        notificationSystem.notifyRiderViaEmail(rider.getEmail(), "Ride accepted & scheduled on " + scheduleTime);
        notificationSystem.notifyDriverViaEmail(driver.getEmail(), "Ride accepted & scheduled on " + scheduleTime);
        return true;
    }

    public void addVehicleToDriver(int driverID, String type, double speed, int capacity, String model,
            String vehicleNumber, String licenseNumber) {
        User user = driverManager.driverMap.get(driverID);
        if (user == null) {
            throw new IllegalArgumentException("Driver not found");
        }
        Vehicle vehicle = vehicleFactory.createVehicle(type, speed, capacity, model, vehicleNumber);
        if (user instanceof Driver driver) {
            driver.setVehicle(vehicle);
            driver.setLicenseNumber(licenseNumber);
        }
    }
    public User addDriver(String name, String phoneNumber, String email, String currentLocation){
        User newDriver = driverManager.addDriver(name, phoneNumber, email);
        if(newDriver instanceof Driver driver){
            driver.setLocation(currentLocation);
            return driver;
        }
        return newDriver;
    }
    @Override
    public void addToRideHistory(Driver rider, RideBooking booking){
        rider.addToRideHistory(booking);
        System.out.println("Ride added to driver history!");
    }
}

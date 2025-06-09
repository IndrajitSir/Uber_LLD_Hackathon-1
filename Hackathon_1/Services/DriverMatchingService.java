package Hackathon_1.Services;

import Hackathon_1.Manager.DriverManager;
import Hackathon_1.NotificationSystem.*;
import Hackathon_1.Strategy.IDriverMatchingService;
import Hackathon_1.Strategy.IDriverService;
import Hackathon_1.Strategy.IMatchingStrategy;
import Hackathon_1.models.Driver;
import Hackathon_1.models.Rider;
import Hackathon_1.utilities.RideType;
import Hackathon_1.utilities.VehicleType;

import java.util.List;
public class DriverMatchingService implements IDriverMatchingService {
    private final DriverManager driverManager = DriverManager.getInstance();
    private final IMatchingStrategy matchingStrategy;
    private final IDriverService driverService;
    // Notification System - Plug & Play model
    private final NotificationSystem notificationSystem = NotificationSystem.getInstance();


    public DriverMatchingService(IMatchingStrategy matchingStrategy, IDriverService driverService) {
        this.matchingStrategy = matchingStrategy;
        this.driverService = driverService;
    }
    @Override
    public Driver findDriver(String pickUpLocation, VehicleType preferredVehicleType, RideType rideType, Rider rider, List<Driver> declinedDrivers){
        List<Driver> availableDrivers = driverManager.getAvailableDrivers();
        Driver selectedDriver =  matchingStrategy.matchDriver(availableDrivers, declinedDrivers, pickUpLocation, preferredVehicleType);
        if(selectedDriver==null){
            notificationSystem.notifyRiderViaEmail(rider.getEmail(), "No driver found for the given criteria.");
            return null;
        }
        notificationSystem.notifyDriverViaEmail(selectedDriver.getEmail(), "New Ride request has come! Please accept if possible.");
        if(!driverService.acceptRide(selectedDriver.getId(), rider)){
            declinedDrivers.add(selectedDriver);
            findDriver(pickUpLocation, preferredVehicleType, rideType, rider, declinedDrivers);
        }
        return selectedDriver;
    }
    @Override
    public Driver findDriver(String pickUpLocation, VehicleType preferredVehicleType, RideType rideType, String scheduleTime, Rider rider, List<Driver> declinedDrivers){
        List<Driver> availableDrivers = driverManager.getAvailableDrivers();
        Driver selectedDriver =  matchingStrategy.matchDriver(availableDrivers, declinedDrivers, pickUpLocation, preferredVehicleType);
        if(selectedDriver==null){
            notificationSystem.notifyRiderViaEmail(rider.getEmail(), "No driver found for the given criteria.");
            return null;
        }
        notificationSystem.notifyDriverViaEmail(selectedDriver.getEmail(), "Ride request! Scheduled time: "+ scheduleTime + " ! Please accept if possible.");
        if(!driverService.acceptRide(selectedDriver.getId(), rider)){
            declinedDrivers.add(selectedDriver);
            findDriver(pickUpLocation, preferredVehicleType, rideType, scheduleTime, rider, declinedDrivers);
        }
        return selectedDriver;
    }
}

package Hackathon_1;

import Hackathon_1.Factory.RideFactoryProvider.ScheduleRide;
import Hackathon_1.Manager.CityManager;
import Hackathon_1.NotificationSystem.*;
import Hackathon_1.Services.*;
import Hackathon_1.Services.Payment.CreditCard;
import Hackathon_1.Services.Pricing.FareService;
import Hackathon_1.Strategy.IDriverMatchingService;
import Hackathon_1.Strategy.IMatchingStrategy;
import Hackathon_1.algorithms.NearestDriver;
import Hackathon_1.models.RideBooking;
import Hackathon_1.models.User;
import Hackathon_1.utilities.RideType;

public class RideIt {
    public static void main(String[] args){
        // Notification System - PLug & Play model
        NotificationSystem notificationSystem = NotificationSystem.getInstance();

        CityManager cityManager = CityManager.getInstance();
        cityManager.addCity("Delhi");
        cityManager.addCity("Mumbai");
        cityManager.addDistanceBetweenCity("Delhi", "Mumbai", 1400.0);

        DriverService driverService = new DriverService();
        User newDriver = driverService.addDriver("Raju", "9876589438", "raju1@gmail.com", "Delhi");
        driverService.addVehicleToDriver(newDriver.getId(), "bike", 40.0, 3, "z12hdis", "Wb2354er", "3465767839");

        RideService rideService = new RideService(new FareService(), new CreditCard(), new RiderService(), new DriverService());
        IMatchingStrategy matchingStrategy = new NearestDriver(); // Driver Matching Algorithm
        IDriverMatchingService driverMatchingService = new DriverMatchingService(matchingStrategy, driverService);

        RiderService riderService = new RiderService(rideService, driverMatchingService);
        User user = riderService.addRider("Indrajit Mandal", "8391015655", "indrajitmandal779@gmail.com");

        RideBooking bookedRide = riderService.requestRide(user.getId(), "Delhi", "Mumbai", "bike", RideType.SCHEDULED, "12:09");
        if(bookedRide==null){
            notificationSystem.notifyRiderViaEmail(user.getEmail(), "Service not available on those cities.");
            return;
        }

        if(bookedRide.getRideType() instanceof ScheduleRide ride){
            notificationSystem.notifyRiderViaEmail(user.getEmail(), "Ride booked! Scheduled time: "+ ride.getScheduleTime());
        }else{
            notificationSystem.notifyRiderViaEmail(user.getEmail(), "The ride is booked!");
        }

        rideService.changeStatus(bookedRide.getId(), "Driver on the way.");
        rideService.changeStatus(bookedRide.getId(), "Passenger onboard.");
        rideService.changeStatus(bookedRide.getId(), "completed");
    }
}

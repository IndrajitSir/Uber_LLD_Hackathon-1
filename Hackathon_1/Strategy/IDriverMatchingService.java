package Hackathon_1.Strategy;

import Hackathon_1.Factory.RideFactoryProvider.IRideFactory;
import Hackathon_1.models.Driver;
import Hackathon_1.models.Rider;
import Hackathon_1.utilities.RideType;
import Hackathon_1.utilities.VehicleType;

import java.util.List;

public interface IDriverMatchingService {
    Driver findDriver(String pickUpLocation, VehicleType preferredVehicleType, RideType rideType, Rider rider, List<Driver> declinedDrivers);
    Driver findDriver(String pickUpLocation, VehicleType preferredVehicleType, RideType rideType, String scheduleTime, Rider rider, List<Driver> declinedDrivers);
}

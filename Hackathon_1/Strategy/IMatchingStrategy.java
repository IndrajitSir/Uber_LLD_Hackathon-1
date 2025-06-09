package Hackathon_1.Strategy;

import Hackathon_1.models.Driver;
import Hackathon_1.utilities.VehicleType;

import java.util.List;

public interface IMatchingStrategy {
    Driver matchDriver(List<Driver> driver, List<Driver> declinedDrivers, String pickUpLocation, VehicleType vehicleType);
}

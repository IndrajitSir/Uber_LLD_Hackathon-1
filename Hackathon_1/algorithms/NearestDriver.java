package Hackathon_1.algorithms;

import Hackathon_1.Strategy.IMatchingStrategy;
import Hackathon_1.models.Driver;
import Hackathon_1.utilities.VehicleType;

import java.util.List;
import java.util.Objects;

public class NearestDriver implements IMatchingStrategy {
    @Override
    public Driver matchDriver(List<Driver> driver, List<Driver> declinedDrivers, String pickUpLocation, VehicleType vehicleType) {
        for(Driver d: driver){ // correct business logic is not written yet.
            System.out.println("Available driver name: "+ d.getName());
            if(Objects.equals(d.getLocation(), pickUpLocation) && Objects.equals(d.getVehicle().getType(), vehicleType)){
                return d;
            }
        }
        return null;
    }
}

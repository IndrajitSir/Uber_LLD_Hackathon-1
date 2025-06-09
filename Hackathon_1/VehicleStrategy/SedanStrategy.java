package Hackathon_1.VehicleStrategy;

import Hackathon_1.models.Vehicle;
import Hackathon_1.utilities.VehicleType;

public class SedanStrategy extends Vehicle {
    public SedanStrategy(VehicleType type, double speed, int capacity, String model, String vehicleNumber) {
        super(type, speed, capacity, model, vehicleNumber);
    }
}

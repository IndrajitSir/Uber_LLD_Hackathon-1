package Hackathon_1.Factory;

import Hackathon_1.VehicleStrategy.AutoRickshawStrategy;
import Hackathon_1.VehicleStrategy.BikeStrategy;
import Hackathon_1.VehicleStrategy.SUVStrategy;
import Hackathon_1.VehicleStrategy.SedanStrategy;
import Hackathon_1.models.Vehicle;
import Hackathon_1.utilities.VehicleType;

public class VehicleFactory {
    public Vehicle createVehicle(String vehicleType, double speed, int capacity, String model, String vehicleNumber){
        if(vehicleType.equalsIgnoreCase("bike")){
            return new BikeStrategy(VehicleType.valueOf(vehicleType.toUpperCase()), speed, capacity, model, vehicleNumber);
        } else if (vehicleType.equalsIgnoreCase("SUV")) {
            return new SUVStrategy(VehicleType.valueOf(vehicleType.toUpperCase()), speed, capacity, model, vehicleNumber);
        }else if (vehicleType.equalsIgnoreCase("sedan")) {
            return new SedanStrategy(VehicleType.valueOf(vehicleType.toUpperCase()), speed, capacity, model, vehicleNumber);
        }else if (vehicleType.equalsIgnoreCase("auto_ricksaw")) {
            return new AutoRickshawStrategy(VehicleType.valueOf(vehicleType.toUpperCase()), speed, capacity, model, vehicleNumber);
        } else{
            throw new IllegalArgumentException("Invalid vehicle type: " + vehicleType);
    }
    }
}

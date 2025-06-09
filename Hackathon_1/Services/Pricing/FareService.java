package Hackathon_1.Services.Pricing;

import Hackathon_1.utilities.VehicleType;

import java.util.HashMap;
import java.util.Map;

public class FareService implements IPricing{
    public final Map<VehicleType, Double> fareRates;

    public FareService(){
        fareRates = new HashMap<>();
        fareRates.put(VehicleType.SUV, 15.0);
        fareRates.put(VehicleType.SEDAN, 10.0);
        fareRates.put(VehicleType.BIKE, 5.0);
        fareRates.put(VehicleType.AUTO_RICKSHAW, 6.0);
    }

    @Override
    public double calculateFare(VehicleType type, double distanceInKm){
        return fareRates.get(type) * distanceInKm;
    }
}

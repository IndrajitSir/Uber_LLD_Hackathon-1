package Hackathon_1.Services.Pricing;

import Hackathon_1.utilities.VehicleType;

import java.util.Map;

public interface IPricing {
    double calculateFare(VehicleType type, double distanceInKm);
}

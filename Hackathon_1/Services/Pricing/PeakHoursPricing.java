package Hackathon_1.Services.Pricing;

import Hackathon_1.utilities.VehicleType;

public class PeakHoursPricing extends PricingDecorator{
    public PeakHoursPricing(IPricing pricing) {
        super(pricing);
    }
    @Override
    public double calculateFare(VehicleType type, double distanceInKm) {
        return pricing.calculateFare(type, distanceInKm)*10;
    }
}

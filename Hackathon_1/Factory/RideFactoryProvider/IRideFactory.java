package Hackathon_1.Factory.RideFactoryProvider;

import Hackathon_1.models.Driver;
import Hackathon_1.models.RideBooking;
import Hackathon_1.models.Rider;

public interface IRideFactory {
    RideBooking bookRide(Rider rider, Driver driver, String from, String to, double distance, IRideFactory rideType);
}


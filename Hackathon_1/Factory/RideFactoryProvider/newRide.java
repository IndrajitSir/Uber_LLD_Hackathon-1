package Hackathon_1.Factory.RideFactoryProvider;

import Hackathon_1.Factory.RideFactoryProvider.IRideFactory;
import Hackathon_1.models.Driver;
import Hackathon_1.models.RideBooking;
import Hackathon_1.models.Rider;

public class newRide implements IRideFactory {
    @Override
    public RideBooking bookRide(Rider rider, Driver driver, String from, String to, double distance, IRideFactory rideType){
        return new RideBooking(rider, driver, "matched", from, to, distance, rideType);
    }
}

package Hackathon_1.Strategy;

import Hackathon_1.models.Driver;
import Hackathon_1.models.RideBooking;
import Hackathon_1.models.Rider;

import java.util.List;

public interface IDriverService {
    boolean acceptRide(int driverID, Rider rider);
    boolean acceptRide(int driverID, Rider rider, String scheduleTime);
    void addToRideHistory(Driver rider, RideBooking booking);
}

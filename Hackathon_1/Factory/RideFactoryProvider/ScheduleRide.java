package Hackathon_1.Factory.RideFactoryProvider;

import Hackathon_1.Factory.RideFactoryProvider.IRideFactory;
import Hackathon_1.models.Driver;
import Hackathon_1.models.RideBooking;
import Hackathon_1.models.Rider;

public class ScheduleRide implements IRideFactory {
    String scheduleTime;

    public ScheduleRide(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    @Override
    public RideBooking bookRide(Rider rider, Driver driver, String from, String to, double distance, IRideFactory rideType) {
        return new RideBooking(rider, driver, "matched", from, to, distance, rideType);
    }
}

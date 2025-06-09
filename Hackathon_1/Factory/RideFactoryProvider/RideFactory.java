package Hackathon_1.Factory.RideFactoryProvider;

import Hackathon_1.Factory.RideFactoryProvider.IRideFactory;
import Hackathon_1.utilities.RideType;

public class RideFactory {
    public IRideFactory createRide(RideType type){
        return switch (type){
            case NEW -> new newRide();
            case CARPOOL -> new CarpoolingRides();
            case SCHEDULED -> throw new IllegalArgumentException("Schedule Time is not given!");
        };

//        if(type==RideType.NEW){
//            return new newRide();
//        } else if (type==RideType.CARPOOL) {
//            return new CarpoolingRides();
//        } else if (type==RideType.SCHEDULED) {
//            throw new IllegalArgumentException("Schedule Time is not given!");
//        } else{
//            throw new IllegalArgumentException("Not supported type!");
//        }
    }
    public IRideFactory createRide(RideType type, String scheduleTime){
        return new ScheduleRide(scheduleTime);
    }
}

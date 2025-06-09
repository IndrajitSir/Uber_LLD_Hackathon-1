package Hackathon_1.Manager;

import Hackathon_1.models.RideBooking;

import java.util.HashMap;
import java.util.Map;

public class RideManager {
    private static RideManager instance = null;
    public final Map<Integer, RideBooking> bookings = new HashMap<>();
    private RideManager(){}

    public static RideManager getInstance(){
        if(instance==null){
            instance = new RideManager();
        }
        return instance;
    }

    public Map<Integer, RideBooking> getRideHistory(){
        return bookings;
    }
}

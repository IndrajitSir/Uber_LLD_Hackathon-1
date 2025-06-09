package Hackathon_1.Services;
import Hackathon_1.Manager.CityManager;
import Hackathon_1.Manager.RiderManager;
import Hackathon_1.Strategy.IDriverMatchingService;
import Hackathon_1.models.*;
import Hackathon_1.utilities.RideType;
import Hackathon_1.utilities.VehicleType;

import java.util.LinkedList;

interface IBooker{
    RideBooking requestRide(int riderID,String from, String to, String vehicleType, RideType rideType);
    RideBooking requestRide(int riderID,String from, String to, String vehicleType, RideType rideType, String scheduleTime);
}
interface IRiderService{
    void addToRideHistory(Rider rider, RideBooking booking);
}
public class RiderService implements IBooker, IRiderService {
    private final CityManager cityManager = CityManager.getInstance();
    private final RiderManager riderManager = RiderManager.getInstance();
    private IRideService rideService;
    private IDriverMatchingService driverMatchingService;
    public RiderService(){}
    public RiderService(RideService rideService, IDriverMatchingService driverMatchingService) {
        this.rideService = rideService;
        this.driverMatchingService = driverMatchingService;
    }

    @Override
    public RideBooking requestRide(int riderID, String from, String to, String vehicleType, RideType rideType) {
        if(cityManager.isCityConnected(from, to)){
            User user = riderManager.getRiderById(riderID);
            if(user==null){
                throw new IllegalArgumentException("Invalid rider id!");
            }
            try{
                if(user instanceof Rider rider){
                    Driver driver = driverMatchingService.findDriver(from, VehicleType.valueOf(vehicleType), rideType, rider, new LinkedList<>());
                    RideBooking bookedRide = rideService.addRide(rider, driver, from, to, cityManager.distanceOf(from, to), rideType);
                    if(bookedRide==null){
                        throw new InstantiationException("Error while adding new Ride!");
                    }
                    System.out.println("Ride booked for " + from + " to " + to + ", ride id: "+ bookedRide.getId());
                    return bookedRide;
                }
            }catch (InstantiationException e){
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
    @Override
    public RideBooking requestRide(int riderID, String from, String to, String vehicleType, RideType rideType, String scheduleTime) {
        if(cityManager.isCityConnected(from, to)){
            User user = riderManager.getRiderById(riderID);
            if(user==null){
                throw new IllegalArgumentException("Invalid rider id!");
            }
            try{
                if(user instanceof Rider rider){
                    Driver driver = driverMatchingService.findDriver(from, VehicleType.valueOf(vehicleType.toUpperCase()), rideType, scheduleTime, rider, new LinkedList<>());
                    if(driver==null){
                        return null;
                    }
                    RideBooking bookedRide = rideService.addRide(rider, driver, from, to, cityManager.distanceOf(from, to), rideType, scheduleTime);
                    if(bookedRide==null){
                        throw new InstantiationException("Error while adding new Ride!");
                    }
                    System.out.println("Ride booked for " + from + " to " + to + ", ride id: "+ bookedRide.getId());
                    return bookedRide;
                }
            }catch (InstantiationException e){
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
    @Override
    public void addToRideHistory(Rider rider, RideBooking booking){
        rider.addToRideHistory(booking);
        System.out.println("Ride added to rider history!");
    }
    public User addRider(String name, String phoneNumber, String email){
        return riderManager.addRider(name, phoneNumber, email);
    }
}

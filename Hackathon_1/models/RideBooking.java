package Hackathon_1.models;

import Hackathon_1.Factory.RideFactoryProvider.IRideFactory;

public class RideBooking {
    static int idCount = 0;
    private final int id;
    private Rider rider;
    private Driver driver;
    private String status;
    private String pickUpLocation;
    private String dropOffLocation;
    private double distance;
    private IRideFactory rideType;

    public IRideFactory getRideType() {
        return rideType;
    }

    public void setRideType(IRideFactory rideType) {
        this.rideType = rideType;
    }

    public RideBooking(Rider rider, Driver driver, String status, String pickUpLocation, String dropOffLocation, double distance, IRideFactory rideType) {
        this.id = ++idCount;
        this.rider = rider;
        this.driver = driver;
        this.status = status;
        this.pickUpLocation = pickUpLocation;
        this.dropOffLocation = dropOffLocation;
        this.distance = distance;
        this.rideType = rideType;
    }

    public int getId() {
        return id;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getStatus() {
        return status;
    }

    public RideBooking setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(String pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public String getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(String dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}

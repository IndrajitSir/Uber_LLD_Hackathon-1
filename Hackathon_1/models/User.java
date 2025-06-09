package Hackathon_1.models;

import java.util.LinkedList;
import java.util.List;

abstract public class User {
    protected int id;
    protected String name;
    protected String phoneNumber;
    protected String email;
    protected List<RideBooking> rideHistory;
    User(){}

    public User(int id, String name, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.rideHistory = new LinkedList<>();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RideBooking> getRideHistory() {
        return rideHistory;
    }

    public void setRideHistory(List<RideBooking> rideHistory) {
        this.rideHistory = rideHistory;
    }
    public void addToRideHistory(RideBooking rideBooking){ this.rideHistory.add(rideBooking);}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


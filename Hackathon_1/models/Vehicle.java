package Hackathon_1.models;

import Hackathon_1.utilities.VehicleType;

abstract public class Vehicle {
    static int idCount = 0;
    private final int vehicleId;
    private VehicleType type;
    private double speed;
    private int capacity;
    private String model;
    private String vehicleNumber;
    public Vehicle(VehicleType type, double speed, int capacity, String model, String vehicleNumber) {
        this.vehicleId = ++idCount;
        this.type = type;
        this.speed = speed;
        this.capacity = capacity;
        this.model = model;
        this.vehicleNumber = vehicleNumber;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}

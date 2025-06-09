package Hackathon_1.models;
public class Driver extends User  {
    private static int idCount = 0;
    private Vehicle vehicle;
    private Rating rating;
    private boolean isAvailable;
    private String licenseNumber;
    private String location;

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public Driver() {
    }

    public Driver(String name, String phoneNumber, String email) {
        super(++idCount, name, phoneNumber, email);
        this.isAvailable = true;
        this.licenseNumber = null;
        this.vehicle = null;
    }
}

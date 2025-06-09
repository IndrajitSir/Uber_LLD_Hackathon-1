package Hackathon_1.Manager;

import Hackathon_1.Factory.UserFactory;
import Hackathon_1.Factory.VehicleFactory;
import Hackathon_1.models.User;
import Hackathon_1.models.Vehicle;
import Hackathon_1.models.Driver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DriverManager {
    private static DriverManager instance = null;
    public final Map<Integer, User> driverMap = new HashMap<>();
    private final UserFactory userFactory = new UserFactory();
    private DriverManager(){}

    public static DriverManager getInstance(){
        if(instance==null){
            instance = new DriverManager();
        }
        return instance;
    }
    public User addDriver(String name, String phoneNumber, String email){
        User driver = userFactory.createUser("driver", name, phoneNumber, email);
        driverMap.put(driver.getId(), driver);
        System.out.println(name + " has been added as driver.");
        return driver;
    }

    public User getDriverById(int id){
        return driverMap.get(id);
    }

    public User removeDriver(int id){
        return driverMap.remove(id);
    }

    public List<Driver> getAvailableDrivers(){
        return driverMap.values().stream().filter(user -> user instanceof Driver).map(user -> (Driver) user).filter(Driver::isAvailable).collect(Collectors.toList());
    }
    public List<Driver> getAvailableDriversByCity(String cityName){
        return driverMap.values().stream().filter(user -> user instanceof Driver).map(user -> (Driver) user).filter(Driver::isAvailable).filter(driver -> cityName.equalsIgnoreCase(driver.getLocation())).collect(Collectors.toList());
    }
}

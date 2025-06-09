package Hackathon_1.Factory;

import Hackathon_1.models.Driver;
import Hackathon_1.models.Rider;
import Hackathon_1.models.User;

public class UserFactory {
    public User createUser(String userType, String name, String phoneNumber, String email){
        if(userType.equalsIgnoreCase("rider")){
            return new Rider(name, phoneNumber, email);
        }
        else if(userType.equalsIgnoreCase("driver")){
            return new Driver(name, phoneNumber, email);
        }else{
            System.out.println("Invalid user type!");
            throw new IllegalArgumentException("Invalid user type: " + userType);
        }
    }
}

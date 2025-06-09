package Hackathon_1.Manager;

import Hackathon_1.Factory.UserFactory;
import Hackathon_1.models.Rider;
import Hackathon_1.models.User;

import java.util.HashMap;
import java.util.Map;

public class RiderManager {
    private static RiderManager instance = null;
    private final Map<Integer, User> riderMap = new HashMap<>();
    private final UserFactory userFactory = new UserFactory();
    private RiderManager(){}

    public static RiderManager getInstance(){
        if(instance==null){
            instance = new RiderManager();
        }
        return instance;
    }

    public User addRider(String name, String phoneNumber, String email){
        User rider = userFactory.createUser("rider", name, phoneNumber, email);
        riderMap.put(rider.getId(), rider);
        System.out.println(name + " successfully registered!");
        return rider;
    }

    public User getRiderById(int id){
        return riderMap.get(id);
    }

    public User removeRider(int id){
        return riderMap.remove(id);
    }
}

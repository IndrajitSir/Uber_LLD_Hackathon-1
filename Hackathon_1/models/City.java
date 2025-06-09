package Hackathon_1.models;

import java.util.List;

public class City {
    private int id;
    private String cityName;
    public int getId() {
        return id;
    }

    public City(int id, String cityName) {
        this.id = id;
        this.cityName = cityName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return cityName;
    }

    public void setName(String cityName) {
        this.cityName = cityName;
    }
}

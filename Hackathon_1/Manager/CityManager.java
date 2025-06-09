package Hackathon_1.Manager;

import Hackathon_1.models.City;

import java.util.HashMap;
import java.util.Map;

public class CityManager {
    static int idCount = 0;
    private static CityManager instance = null;
    private final Map<City, Map<City, Double>> cityGraph = new HashMap<>();
    private final Map<String, City> cityRegistry = new HashMap<>();
    private CityManager(){}
    public static CityManager getInstance(){
        if(instance==null){
            instance = new CityManager();
        }
        return instance;
    }
    public Map<City, Map<City, Double>> getCityGraph() {
        return cityGraph;
    }

    public void addCity(String cityName){
        cityRegistry.put(cityName, new City(++idCount, cityName));
//        cityGraph.put(new City(++idCount, cityName), new HashMap<>());
        System.out.println("New City ( cityName: " + cityName +" ) has been added!");
    }
    public void addDistanceBetweenCity(String firstCity, String secondCity, Double distance){
        if(cityRegistry.containsKey(firstCity) && cityRegistry.containsKey(secondCity)){
            City city1 = cityRegistry.get(firstCity);
            City city2 = cityRegistry.get(secondCity);
            cityGraph.putIfAbsent(city1, new HashMap<>());
            cityGraph.putIfAbsent(city2, new HashMap<>());
            cityGraph.get(city1).put(city2, distance);
            cityGraph.get(city2).put(city1,distance);
            System.out.println(distance + "km distance has been added for "+firstCity +" between "+secondCity+" .");
            display();
        }
    }

    public boolean isCityConnected(String firstCity, String secondCity){
        if(cityRegistry.containsKey(firstCity) && cityRegistry.containsKey(secondCity)){
            City city1 = cityRegistry.get(firstCity);
            City city2 = cityRegistry.get(secondCity);
            if(city1==null||city2==null){
                throw new IllegalArgumentException("One of the city doesn't exists!");
            }
            Map<City, Double> connectedCities = cityGraph.get(city1);
            if(connectedCities==null){
                System.out.println("Problem with cityGraph!");
                return false;
            }
            return connectedCities.get(city2) > 0;
        }
        return false;
    }

    public double distanceOf(String firstCity, String secondCity){
        if(isCityConnected(firstCity, secondCity)){
            City city1 = cityRegistry.get(firstCity);
            City city2 = cityRegistry.get(secondCity);

            Map<City, Double> connectedCities = cityGraph.get(city1);
            return connectedCities.get(city2);
        }
        throw new IllegalArgumentException("Cities are not connected!");
    }

    private void display(){
        System.out.println("\t\tCity graph: ");
        for(Map.Entry<City, Map<City, Double>> graph: cityGraph.entrySet()){
            System.out.print(graph.getKey().getName() + " connected to: ");
            for(Map.Entry<City, Double> city: graph.getValue().entrySet()){
                System.out.print("\t" +city.getKey().getName());
            }
            System.out.println();
        }
    }
}

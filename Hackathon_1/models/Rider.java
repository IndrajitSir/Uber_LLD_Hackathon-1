package Hackathon_1.models;

public class Rider extends User {
    static int idCount = 0;

    public Rider(String name, String phoneNumber, String email) {
        super(++idCount, name, phoneNumber, email);
    }

    public Rider() {
    }
}
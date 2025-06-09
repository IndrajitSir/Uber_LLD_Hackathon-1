package Hackathon_1.NotificationSystem;

public class SimpleNotification implements INotification{
    private String text;

    public SimpleNotification(String msg) {
        this.text = msg;
    }

    public String getContent() {
        return text;
    }
}

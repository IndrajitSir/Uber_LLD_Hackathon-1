package Hackathon_1.NotificationSystem;

public class TimestampDecorator extends NotificationDecorator{
    public TimestampDecorator(INotification n) {
        super(n);
    }

    public String getContent() {
        return "[2025-04-13 14:22:00] " + notification.getContent();
    }
}

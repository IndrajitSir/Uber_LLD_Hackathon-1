package Hackathon_1.NotificationSystem;

abstract public class NotificationDecorator implements INotification{
    protected INotification notification;

    public NotificationDecorator(INotification n) {
        this.notification = n;
    }
}

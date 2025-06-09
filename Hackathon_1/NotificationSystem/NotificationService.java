package Hackathon_1.NotificationSystem;

import java.util.ArrayList;
import java.util.List;

public class NotificationService {
    private final NotificationObservable observable;
    private static NotificationService instance;
    private final List<INotification> notifications = new ArrayList<>();

    private NotificationService() {
        observable = new NotificationObservable();
    }

    public static NotificationService getInstance() {
        if (instance == null) {
            instance = new NotificationService();
        }
        return instance;
    }

    public NotificationObservable getObservable() {
        return observable;
    }

    public void sendNotification(INotification notification) {
        notifications.add(notification);
        observable.setNotification(notification);
        System.out.println("Notification sended: "+notification.getContent());
    }
}

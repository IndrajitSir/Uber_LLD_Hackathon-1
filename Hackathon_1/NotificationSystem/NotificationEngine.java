package Hackathon_1.NotificationSystem;

import java.util.ArrayList;
import java.util.List;

public class NotificationEngine implements IObserver{
    private final NotificationObservable notificationObservable;
    private final List<INotificationStrategy> notificationStrategies = new ArrayList<>();

    public NotificationEngine(NotificationObservable observable) {
        this.notificationObservable = observable;
    }

    public void addNotificationStrategy(INotificationStrategy ns) {
        this.notificationStrategies.add(ns);
    }

    public void update() {
        String notificationContent = notificationObservable.getNotificationContent();
        for (INotificationStrategy strategy : notificationStrategies) {
            strategy.sendNotification(notificationContent);
        }
        notificationStrategies.clear();// Extra added only for this particular project - RideIt
    }
}

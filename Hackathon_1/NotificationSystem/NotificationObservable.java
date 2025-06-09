package Hackathon_1.NotificationSystem;

import java.util.ArrayList;
import java.util.List;

public class NotificationObservable implements IObservable{
    private List<IObserver> observers = new ArrayList<>();
    private INotification currentNotification;

    public void addObserver(IObserver obs) {
        observers.add(obs);
    }

    public void removeObserver(IObserver obs) {
        observers.remove(obs);
    }
    public void clearObservers(){observers.clear();}

    public void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update();
        }
        observers.clear();
    }

    public void setNotification(INotification notification) {
        this.currentNotification = notification;
        notifyObservers();
    }

    public INotification getNotification() {
        return currentNotification;
    }

    public String getNotificationContent() {
        return currentNotification.getContent();
    }
}

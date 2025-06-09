package Hackathon_1.NotificationSystem;

public class PopUpStrategy implements INotificationStrategy{
    public void sendNotification(String content) {
        System.out.println("Sending Popup Notification: \n" + content);
    }
}

package Hackathon_1.NotificationSystem;

public class NotificationSystem {
    private static NotificationSystem instance = null;
    private NotificationSystem(){}
    public static NotificationSystem getInstance(){
        if(instance==null){
            instance = new NotificationSystem();
        }
        return instance;
    }
    NotificationService notificationService = NotificationService.getInstance();

    // Get Observable
    NotificationObservable notificationObservable = notificationService.getObservable();

    // Create Logger Observer
    Logger logger = new Logger(notificationObservable);

    // Create NotificationEngine observers.
    NotificationEngine notificationEngine = new NotificationEngine(notificationObservable);
    public void notifyRiderViaEmail(String riderEmail, String message){
        notificationEngine.addNotificationStrategy(new EmailStrategy(riderEmail));

        // Attach these observers.
        notificationObservable.addObserver(logger);
        notificationObservable.addObserver(notificationEngine);

        // Create a notification with decorators.
        INotification notification = new SimpleNotification(message);
        notification = new TimestampDecorator(notification);
        notification = new SignatureDecorator(notification, "Customer Care");
        notificationService.sendNotification(notification);
    }
    public void notifyRiderViaSMS(String riderPhoneNumber, String message){
        notificationEngine.addNotificationStrategy(new SMSStrategy(riderPhoneNumber));

        // Attach these observers.
        notificationObservable.addObserver(logger);
        notificationObservable.addObserver(notificationEngine);

        // Create a notification with decorators.
        INotification notification = new SimpleNotification(message);
        notification = new TimestampDecorator(notification);
        notification = new SignatureDecorator(notification, "Customer Care");
        notificationService.sendNotification(notification);
    }

    public void notifyDriverViaEmail(String driverEmail, String message){
        notificationEngine.addNotificationStrategy(new EmailStrategy(driverEmail));

        // Attach these observers.
        notificationObservable.addObserver(logger);
        notificationObservable.addObserver(notificationEngine);

        // Create a notification with decorators.
        INotification notification = new SimpleNotification(message);
        notification = new TimestampDecorator(notification);
        notification = new SignatureDecorator(notification, "Customer Care");
        notificationService.sendNotification(notification);
    }
    public void notifyDriverViaSMS(String driverPhoneNumber, String message){
        notificationEngine.addNotificationStrategy(new SMSStrategy(driverPhoneNumber));

        // Attach these observers.
        notificationObservable.addObserver(logger);
        notificationObservable.addObserver(notificationEngine);

        // Create a notification with decorators.
        INotification notification = new SimpleNotification(message);
        notification = new TimestampDecorator(notification);
        notification = new SignatureDecorator(notification, "Customer Care");
        notificationService.sendNotification(notification);
    }
}

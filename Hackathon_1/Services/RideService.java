package Hackathon_1.Services;

import Hackathon_1.Factory.RideFactoryProvider.IRideFactory;
import Hackathon_1.Factory.RideFactoryProvider.RideFactory;
import Hackathon_1.Manager.RideManager;
import Hackathon_1.NotificationSystem.*;
import Hackathon_1.Services.Payment.IPaymentStrategy;
import Hackathon_1.Services.Pricing.IPricing;
import Hackathon_1.Strategy.IDriverService;
import Hackathon_1.models.Driver;
import Hackathon_1.models.RideBooking;
import Hackathon_1.models.Rider;
import Hackathon_1.utilities.RideType;
interface IRideService{
    RideBooking addRide(Rider rider, Driver driver, String pickUpLocation, String dropOffLocation, double distance, RideType rideType);
    RideBooking addRide(Rider rider, Driver driver, String pickUpLocation, String dropOffLocation, double distance, RideType rideType, String scheduleTime);
}
public class RideService implements IRideService{
    private final RideManager rideManager = RideManager.getInstance();
    private final RideFactory rideFactory = new RideFactory();
    private IRiderService riderService;
    private IDriverService driverService;
    private IPricing pricing;
    private IPaymentStrategy paymentStrategy;
    private final NotificationSystem notificationSystem = NotificationSystem.getInstance();
    public RideService(){}
    public RideService(IPricing pricing, IPaymentStrategy paymentStrategy, IRiderService riderService, IDriverService driverService) {
        this.pricing = pricing;
        this.paymentStrategy = paymentStrategy;
        this.riderService = riderService;
        this.driverService = driverService;
    }
    @Override
    public RideBooking addRide(Rider rider, Driver driver, String pickUpLocation, String dropOffLocation,
            double distance, RideType rideType) {
        try {
            IRideFactory r = rideFactory.createRide(rideType);
            RideBooking ride = r.bookRide(rider, driver, pickUpLocation, dropOffLocation, distance, r);
            if (ride == null) {
                throw new InstantiationException("Error while creating new ride!");
            }
            rideManager.bookings.put(ride.getId(), ride);
            System.out.println("Ride data stored successfully!");
            return ride;
        } catch (InstantiationException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    @Override
    public RideBooking addRide(Rider rider, Driver driver, String pickUpLocation, String dropOffLocation,
            double distance, RideType rideType, String scheduleTime) {
        try {
            IRideFactory r = rideFactory.createRide(rideType, scheduleTime);
            RideBooking ride = r.bookRide(rider, driver, pickUpLocation, dropOffLocation, distance, r);
            if (ride == null) {
                throw new InstantiationException("Error while creating new ride!");
            }
            rideManager.bookings.put(ride.getId(), ride);
            System.out.println("Ride data stored successfully!");
            return ride;
        } catch (InstantiationException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void changeStatus(int rideId, String newStatus) {
        RideBooking updatedRideStatus = rideManager.bookings.computeIfPresent(rideId,
                (Integer, RideBooking) -> RideBooking.setStatus(newStatus));
        assert updatedRideStatus != null;
        if (updatedRideStatus.getStatus().equalsIgnoreCase(newStatus)) {
            notificationSystem.notifyRiderViaEmail(updatedRideStatus.getRider().getEmail(), "Ride status update: " + newStatus);
            if (newStatus.equalsIgnoreCase("completed")) {
                double totalPrice = pricing.calculateFare(updatedRideStatus.getDriver().getVehicle().getType(),
                        updatedRideStatus.getDistance());
                notificationSystem.notifyRiderViaEmail(updatedRideStatus.getRider().getEmail(), "Congratulations🎊 on your successful ride! Kindly pay: " + totalPrice);
                riderService.addToRideHistory(updatedRideStatus.getRider(), updatedRideStatus);
                driverService.addToRideHistory(updatedRideStatus.getDriver(), updatedRideStatus);
                updatedRideStatus.getDriver().setAvailable(true);
                if (paymentStrategy.pay()) {
                    notificationSystem.notifyRiderViaEmail(updatedRideStatus.getRider().getEmail(), "Payment is successful! see you next time😘");
                }
            }
        }
    }
}

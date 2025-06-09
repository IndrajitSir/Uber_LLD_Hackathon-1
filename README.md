🧠 Approach to Low-Level Design (LLD) of Ride Sharing Booking System

✅ Overview of 'RideIt' (I named this system)

The Ride Sharing Booking System (inspired by platforms like Uber) was designed to be modular, extensible, testable, and clean, following object-oriented principles, SOLID design principles, and leveraging appropriate design patterns. This approach ensures the system is scalable and can be extended with features like different ride types, real-time updates, and multiple users interacting simultaneously.


---

## SOLID Principles Used
1. SRP (Single Responsibility Principle)
Classes like (RiderManager, DriverManager, RideManager and CityManager): 4 Managers and 3 services (DriverService, RideService, and RiderService) were designed to handle only one responsibility: managing Riders, Drivers, Rides, and Cities respectively.

2. OCP (Open/Closed principle)
Used Strategy and Factory patterns to allow extension of ride Types and Vehicle types without modifying existing code.

3. LSP (Liskov Substition Principle)
Subclasses of IMatchingStrategy and IPaymentStrategy can be used InterChangeably, without breaking the system.

4. ISP (Interface Segregation Principle)
Created small focused interfaces to avoid forcing classes to implement unnecessary methods.

5. DIP(Dependency Inversion Principle)
High level modules like RiderService depend on abstractions/interfaces rather thsn concrete classes, allowing flexibilty. All services communicate to each other via depending on abstraction/interfaces.
---

### Design Patterns Used

1. Factory Pattern

Used in VehicleFactory and UserFactory.

Helps in instantiating different types of vehicles (Bike, SUV, Hatchback) or users (Driver, Rider) without exposing the instantiation logic.
    VehicleFactory: for vehicle creation
    RideFactory: for ride type (i.e. NewRide, ScheduleRide, CarpoolingRide) creation
    UserFactory: for user (Rider or Driver) creation 

2. Strategy Pattern

Applied in the driver matching logic.

Different strategies like NearestDriverMatching, CheapestFareMatching can be used interchangeably via a common interface.

Allows the algorithm for driver selection to be injected and changed at runtime.


3. Decorator Pattern

Used in FareService (Pricing calculation), it helps to decorate the price according to rideType.
also used in Notification, to decorate the notification accordingly.


4. Observer Pattern

Used to notify drivers in real-time when a rider requests a ride.

Driver implements the Observer interface and RiderService or a NotificationCenter acts as the subject.


5. Singleton Pattern

Applied in Managers and in Notification System to make it as 'Plug & Play model', to ensure only one instance is used globally.


ex: CityManager cityManager = CityManager.getInstance();


---

## 🧩 System Components and Responsibilities

### 1. User Management
- *User (Abstract Class)*: Common parent for all user types (Driver, Rider).
- *Driver*: Inherits from User, includes driver-specific attributes like license number, vehicle, availability status, and location updates.
- *Rider*: Inherits from User, can request rides, rate drivers, and receive notifications.

### 2. Ride Management
- *RideBooking*: Core entity representing a single ride. Contains information such as origin, destination, fare, driver, rider, and ride status.
- *RideManager*: Singleton responsible for managing all active and completed rides. Coordinates ride lifecycle events (request, accept, complete).
- *RideFactory*: Factory class responsible for creating different types of rides based on requested ride type (e.g., scheduled, carpooling, standard/newRide).

### 3. Driver Matching
- *IDriverMatchingStrategy*: Interface for implementing different driver-matching algorithms.
- *NearestDriverMatching*: Strategy that selects the driver closest to the rider.
- *MostIdleDriverMatching*: Alternative strategy that assigns drivers who’ve been idle the longest.
- *DriverService*: Handles driver registration, availability, matching, and status updates.

### 4. Pricing and Fare Calculation
- *IPricingStrategy*: Interface for fare calculation logic.
- *FareService*: Default implementation based on distance and time.
- *PeakHoursPricing*: Adjusts base price using PricingDecorator logic during high demand.
- *PricingDecorator*: Concrete decorator that applies surge pricing on top of base pricing.

### 5. Vehicle Management
- *Vehicle (Abstract Class)*: Represents vehicle-related data and behavior.
- *Car, Bike, Auto*: Inherit from Vehicle class and specify type-specific details.
- *VehicleFactory*: Factory class to instantiate vehicle objects based on input type.
- *VehicleType*: Enum to identify different vehicle categories.

### 6. Location and City Services
- *CityManager*: Singleton managing all cities, active drivers in cities, and ride availability by location.

### 7. Notification System
- *NotificationService*: Singleton service using the Observer Pattern to broadcast ride requests to subscribed drivers.
- *NotificationType*: Enum defining types of system notifications.
- *INotifiable / Observer*: Interface implemented by Driver to receive notifications.
- *Subject*: NotificationService acts as the Subject that Drivers subscribe to.

### 8. Utilities and Supporting Components
- *Enums*: Various enums like RideType, VehicleType, etc., enhance type safety and readability.
- *Exceptions*: Custom exceptions can be added for handling invalid requests, unavailable rides, etc.

---

🔔 Real-Time Features (Observer Pattern)

When a rider requests a ride, from available drivers in that city only one driver is notified at a time, if the driver accepts the request, then the rider/user is notified.
if the driver rejects, then the system/DriverMatchingAlgorithm will look for another driver and send him the notification to accept and so on.

The driver can accept or reject the ride.

---

🛠 Extensibility

The design is extensible for adding:

New ride types (e.g., Pool, Scheduled)

New notification mechanisms (SMS, Push, Email)

Dynamic pricing models



---

📌 Final Thoughts

This system was built keeping code maintainability, testability, and scalability in mind. The use of clean architecture, separation of concerns, and design patterns makes it flexible enough for future real-world enhancements, such as third-party payment integration or map APIs.

package Hackathon_1.Services.Payment;

public class UPI implements IPaymentStrategy{
    @Override
    public boolean pay() {
        System.out.println("Payment Processing via UPI.");
        return true;
    }
}

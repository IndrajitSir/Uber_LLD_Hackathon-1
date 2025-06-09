package Hackathon_1.Services.Payment;

public class CreditCard implements IPaymentStrategy{
    @Override
    public boolean pay() {
        System.out.println("Payment Processing via credit card.");
        return true;
    }
}

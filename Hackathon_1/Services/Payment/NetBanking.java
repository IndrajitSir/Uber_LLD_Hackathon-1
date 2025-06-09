package Hackathon_1.Services.Payment;

public class NetBanking implements IPaymentStrategy{
    @Override
    public boolean pay() {
        System.out.println("Payment Processing via Net Banking.");
        return true;
    }
}

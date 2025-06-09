package Hackathon_1.Services.Pricing;

abstract public class PricingDecorator implements IPricing{
    protected IPricing pricing;
    PricingDecorator(IPricing p){ this.pricing = p; }
}

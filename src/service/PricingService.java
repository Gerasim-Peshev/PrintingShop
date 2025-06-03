package service;

import model.*;

import java.math.BigDecimal;

public class PricingService {
    private int discountThreshold;
    private double discountPercentage;

    public PricingService(int discountThreshold, double discountPercentage) {
        this.discountThreshold = discountThreshold;
        this.discountPercentage = discountPercentage;
    }

    public double calculatePaperCost(Publication pub) {
        return BigDecimal.valueOf(pub.getPaper().getPrice(pub.getSize())).multiply(BigDecimal.valueOf(pub.getCopies())).doubleValue();
    }

    public double calculatePrintCost(Publication pub, double basePricePerCopy) {
        double cost = basePricePerCopy * pub.getCopies();
        if (pub.getCopies() > discountThreshold) {
            cost *= (1 - discountPercentage / 100);
        }
        return cost;
    }
}
package model;

import java.math.BigDecimal;

public class Manager extends Employee {
    private double bonusPercentage;

    public Manager(String name, double baseSalary, double bonusPercentage) {
        super(name, BigDecimal.valueOf(baseSalary));
        this.bonusPercentage = bonusPercentage;
    }

    public double getSalaryWithBonus(double revenue, double threshold) {
        if (revenue > threshold)
            return baseSalary.multiply(BigDecimal.valueOf(1 + bonusPercentage / 100)).doubleValue();

        return baseSalary.doubleValue();
    }
}
package model;

import java.io.Serializable;
import java.math.BigDecimal;

public abstract class Employee implements Serializable {
    protected String name;
    protected BigDecimal baseSalary;

    public Employee(String name, BigDecimal baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    public String getName() {
        return name;
    }
}
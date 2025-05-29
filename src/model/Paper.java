package model;

import enums.PaperSize;
import enums.PaperType;

import java.math.BigDecimal;

public class Paper {
    private PaperType type;
    private BigDecimal basePriceA5;

    public Paper(PaperType type, double basePriceA5) {
        this.type = type;
        this.basePriceA5 = BigDecimal.valueOf(basePriceA5);
    }

    public double getPrice(PaperSize size) {
        return basePriceA5.multiply(BigDecimal.valueOf(size.getMultiplier())).doubleValue();
    }

    public PaperType getType() {
        return type;
    }
}
package enums;

public enum PaperSize {
    A5(1.0), A4(1.2), A3(1.5), A2(1.8), A1(2.0);

    private final double multiplier;

    PaperSize(double multiplier) {
        this.multiplier = multiplier;
    }

    public double getMultiplier() { return multiplier; }
}

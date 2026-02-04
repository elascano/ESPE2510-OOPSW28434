package ec.edu.espe.inventory.model;

public final class StockAlert {
    private final int threshold;

    public StockAlert(int threshold) {
        if (threshold < 0) throw new IllegalArgumentException("Threshold no puede ser negativo");
        this.threshold = threshold;
    }

    public int getThreshold() { return threshold; }

    public boolean isLow(int stock) {
        return stock < threshold;
    }
}


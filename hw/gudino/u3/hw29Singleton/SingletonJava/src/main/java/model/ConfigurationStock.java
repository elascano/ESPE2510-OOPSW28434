package model;

public class ConfigurationStock {
    private static ConfigurationStock instance;
    private int minimumStock;

    private ConfigurationStock() {
        minimumStock = 10; // 
    }

    public static ConfigurationStock getInstance() {
        if (instance == null) {
            instance = new ConfigurationStock();
        }
        return instance;
    }

    public int getMinimumStock() {
        return minimumStock;
    }

    public void setMinimumStock(int minimumStock) {
        this.minimumStock = minimumStock;
    }
}

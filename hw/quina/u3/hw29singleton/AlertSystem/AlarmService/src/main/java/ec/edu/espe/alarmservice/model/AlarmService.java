package ec.edu.espe.alarmservice.model;

/**
 *
 * @author Maryuri Quiña, @ESPE
 */
public class AlarmService {

   private static AlarmService instance;
    private int minStock = 10;

    private AlarmService() {
    }

    public static AlarmService getInstance() {
        if (instance == null) {
            instance = new AlarmService();
        }
        return instance;
    }

    public boolean isLowStock(Product product) {
        return product != null && product.getStock() < minStock;
    }

    public int getMinStock() {
        return minStock;
    }

    public void setMinStock(int minStock) {
        this.minStock = minStock;
    }
}

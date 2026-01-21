package ec.espe.edu.ws32Singleton.model;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, @ESPE
 */
public class AlarmService {

    private static AlarmService instance;
    private int MIN_STOCK = 10;

    private AlarmService() {
    }

    public static AlarmService getInstance() {
        if (instance == null) {
            instance = new AlarmService();
        }
        return instance;
    }

    public boolean isLowStock(Product product) {
        return product != null && product.getStock() < MIN_STOCK;
    }

    public int getMinStock() {
        return MIN_STOCK;
    }
}

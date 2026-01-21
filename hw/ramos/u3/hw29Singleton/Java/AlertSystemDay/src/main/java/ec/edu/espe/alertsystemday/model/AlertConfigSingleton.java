package ec.edu.espe.alertsystemday.model;

/**
 *
 * @author Paulo Ramos
 */
import java.io.*;

public class AlertConfigSingleton {

    private static AlertConfigSingleton instance;
    private int alertDays;
    private static final String FILE_PATH = "alert_config.json";

    private AlertConfigSingleton() {
        load();
    }

    public static AlertConfigSingleton getInstance() {
        if (instance == null) {
            instance = new AlertConfigSingleton();
        }
        return instance;
    }

    public int getAlertDays() {
        return alertDays;
    }

    public void setAlertDays(int alertDays) {
        this.alertDays = alertDays;
        save();
    }

    private void load() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            alertDays = 3;
            save();
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            alertDays = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            alertDays = 3;
        }
    }

    private void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(String.valueOf(alertDays));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package ec.edu.espe.alertsystemperday.model;

/**
 *
 * @author Paulo Ramos
 */

public class AlertConfig {

    private int alertDays;

    public AlertConfig(int alertDays) {
        this.alertDays = alertDays;
    }

    public int getAlertDays() {
        return alertDays;
    }

    public void setAlertDays(int alertDays) {
        this.alertDays = alertDays;
    }
}

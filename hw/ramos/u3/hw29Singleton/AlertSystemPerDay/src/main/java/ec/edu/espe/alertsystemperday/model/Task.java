package ec.edu.espe.alertsystemperday.model;

/**
 *
 * @author Paulo Ramos
 */
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Task {

    private String name;
    private LocalDate dueDate;

    public Task(String name, LocalDate dueDate) {
        this.name = name;
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public long getDaysRemaining() {
        return ChronoUnit.DAYS.between(LocalDate.now(), dueDate);
    }
}

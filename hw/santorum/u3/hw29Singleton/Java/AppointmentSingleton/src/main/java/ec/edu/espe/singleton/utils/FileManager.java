 
package ec.edu.espe.singleton.utils;

import ec.edu.espe.singleton.model.Appointment;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author Thais Santorum
 */



public class FileManager {

    private static final String FILE_NAME = "appointments.txt";

    public static void saveAppointment(Appointment appointment) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(appointment.toFileString() + "\n");
        } catch (IOException e) {
            System.out.println("Error saving appointment");
        }
    }
}

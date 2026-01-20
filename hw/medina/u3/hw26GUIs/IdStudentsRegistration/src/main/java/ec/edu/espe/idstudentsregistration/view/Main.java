package ec.edu.espe.idstudentsregistration.view;

import ec.edu.espe.idstudentsregistration.model.MongoConnection;

/**
 * Main: arranca la view y al final cierra conexión.
 */
public class Main {
    public static void main(String[] args) {
        try {
            new ConsoleView().start();
        } finally {
            MongoConnection.close(); // buena práctica
        }
    }
}

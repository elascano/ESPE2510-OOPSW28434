
package ec.edu.espe.singleton.utils;

/**
 *
 * @author Yets
 */

public class EmailService {

    public static void sendEmail(String to, String message) {
        String from = EmailConfig.getInstance().getSenderEmail();

        System.out.println("----- EMAIL SENT -----");
        System.out.println("From: " + from);
        System.out.println("To: " + to);
        System.out.println("Message: " + message);
        System.out.println("----------------------");
    }
}

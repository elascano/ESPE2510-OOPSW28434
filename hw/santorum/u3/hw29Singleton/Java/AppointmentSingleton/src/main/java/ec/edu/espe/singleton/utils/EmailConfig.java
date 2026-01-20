
package ec.edu.espe.singleton.utils;


import java.io.BufferedReader;
import java.io.FileReader;
import org.json.JSONObject;
/**
 *
 * @author Thais Santorum
 */



public class EmailConfig {

    private static EmailConfig instance;
    private String senderEmail;

    private EmailConfig() {
        loadConfig();
    }

    public static EmailConfig getInstance() {
        if (instance == null) {
            instance = new EmailConfig();
        }
        return instance;
    }

    private void loadConfig() {
        try (BufferedReader br = new BufferedReader(new FileReader("email_config.json"))) {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                json.append(line);
            }
            JSONObject obj = new JSONObject(json.toString());
            senderEmail = obj.getString("senderEmail");
        } catch (Exception e) {
            senderEmail = "default@clinic.com";
        }
    }

    public String getSenderEmail() {
        return senderEmail;
    }
}

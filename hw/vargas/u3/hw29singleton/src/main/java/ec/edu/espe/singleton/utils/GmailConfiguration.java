package ec.edu.espe.singleton.utils;
import org.bson.Document;
import java.util.List;


/**
 *
 * @author César Vargas, Paradigm, @ESPE
 */
public class GmailConfiguration {
     
    private static GmailConfiguration instance;
    
    private String senderEmail;
    private String appPassword;
    private String smtpHost;
    private String smtpPort;
    private String smtpAuth;
    private String smtpStartTls;
    private String socketFactoryPort;
    private String socketFactoryClass;

    private GmailConfiguration() {
        loadConfigurationFromDatabase();
    }

    public static synchronized GmailConfiguration getInstance() {
        if (instance == null) {
            instance = new GmailConfiguration();
        }
        return instance;
    }

    private void loadConfigurationFromDatabase() {
        MongoManager mongoManager = new MongoManager();
        
        List<Document> results = mongoManager.find("system_config", new Document());

        if (results != null && !results.isEmpty()) {
            Document configDoc = results.get(0); 
            
            this.senderEmail = configDoc.getString("sender_email");
            this.appPassword = configDoc.getString("app_password");
            this.smtpHost = configDoc.getString("smtp_host");
            this.smtpPort = configDoc.getString("smtp_port");
            this.smtpAuth = configDoc.getString("smtp_auth");
            this.socketFactoryPort = configDoc.getString("socket_factory_port");
            this.socketFactoryClass = configDoc.getString("socket_factory_class");
            
            if (this.smtpHost == null) this.smtpHost = "smtp.gmail.com";
            if (this.smtpPort == null) this.smtpPort = "465";
            if (this.socketFactoryClass == null) this.socketFactoryClass = "javax.net.ssl.SSLSocketFactory";
            
        } else {
            System.err.println("Advertencia: No se encontró configuración de email en la base de datos.");
        }
    }

    public String getSenderEmail() { return senderEmail; }
    public String getAppPassword() { return appPassword; }
    public String getSmtpHost() { return smtpHost; }
    public String getSmtpPort() { return smtpPort; }
    public String getSmtpAuth() { return smtpAuth; }
    public String getSocketFactoryPort() { return socketFactoryPort; }
    public String getSocketFactoryClass() { return socketFactoryClass; }
}



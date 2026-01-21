import { MongoManager } from './MongoManager.js';

export class GmailConfiguration {
    static #instance = null;

    constructor() {
        if (GmailConfiguration.#instance) {
            throw new Error("Use GmailConfiguration.getInstance()");
        }
        this.senderEmail = null;
        this.appPassword = null;
        this.smtpHost = "smtp.gmail.com";
        this.smtpPort = 465;
        this.configLoaded = false;
    }

    static getInstance() {
        if (!GmailConfiguration.#instance) {
            GmailConfiguration.#instance = new GmailConfiguration();
        }
        return GmailConfiguration.#instance;
    }

    async loadConfigurationFromDatabase() {
        if (this.configLoaded) return;

        const mongoManager = new MongoManager();
        const results = await mongoManager.find("system_config", {});

        if (results && results.length > 0) {
            const configDoc = results[0];
            this.senderEmail = configDoc.sender_email;
            this.appPassword = configDoc.app_password;
            this.smtpHost = configDoc.smtp_host || "smtp.gmail.com";
            this.smtpPort = parseInt(configDoc.smtp_port) || 465;
            this.configLoaded = true;
        } else {
            console.error("Advertencia: No se encontró configuración de email en la base de datos.");
        }
        
        await mongoManager.close();
    }
}
import nodemailer from 'nodemailer';
import { GmailConfiguration } from './GmailConfiguration.js';

export class AppointmentNotificationService {
    constructor() {
        this.config = GmailConfiguration.getInstance();
    }

    async init() {
        await this.config.loadConfigurationFromDatabase();
        
        this.transporter = nodemailer.createTransport({
            host: this.config.smtpHost,
            port: this.config.smtpPort,
            secure: true, 
            auth: {
                user: this.config.senderEmail,
                pass: this.config.appPassword,
            },
        });
    }

    async sendReservationConfirmation(recipientEmail, patientName, appointmentDetails) {
        if (!this.config.senderEmail || !this.config.appPassword) {
            console.error("Error: Credenciales no configuradas.");
            return;
        }

        const mailOptions = {
            from: this.config.senderEmail,
            to: recipientEmail,
            subject: 'Confirmación de Cita Médica - Clínica Toamedical',
            text: this.#buildEmailBody(patientName, appointmentDetails)
        };

        try {
            await this.transporter.sendMail(mailOptions);
            console.log(`Notificación enviada correctamente a: ${recipientEmail}`);
        } catch (error) {
            console.error(`Error al enviar el correo: ${error.message}`);
        }
    }

    #buildEmailBody(name, details) {
        return `Estimado/a ${name},

Su cita ha sido reservada exitosamente.
Fecha de la cita agendada: ${details}

Por favor, llegue 10 minutos antes.
Atentamente,
Tu clínica de confianza Toamedical.`;
    }
}
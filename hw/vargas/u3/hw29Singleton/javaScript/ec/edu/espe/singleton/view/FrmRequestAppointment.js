import readlineSync from 'readline-sync';
import { MongoManager } from '../utils/MongoManager.js';
import { GUIValidation } from '../utils/GUIValidation.js';
import { AppointmentNotificationService } from '../utils/AppointmentNotificationService.js';
import { Appointment } from '../model/Appointment.js';
import { DateModel } from '../model/DateModel.js';

export class FrmRequestAppointment {

    constructor() {
        this.mongoManager = new MongoManager();
    }

    async setVisible(visible) {
        if (!visible) return;

        console.clear();
        console.log("=========================================");
        console.log("          CLÍNICA TOAMEDICAL            ");
        console.log("            AGENDAR CITA                ");
        console.log("=========================================");

        await this.handleForm();
    }

    async handleForm() {
        const appId = readlineSync.question('ID de la cita: ');
        if (!GUIValidation.validateOnlyNumbers(appId, "ID Cita")) return this.retry();

        const patId = readlineSync.question('ID del paciente: ');
        if (!GUIValidation.validateOnlyNumbers(patId, "ID Paciente")) return this.retry();

        const docId = readlineSync.question('ID del doctor: ');
        if (!GUIValidation.validateOnlyNumbers(docId, "ID Doctor")) return this.retry();

        const dateStr = readlineSync.question('Fecha (YYYY-MM-DD): ');
        const dateObj = new Date(dateStr);
        if (isNaN(dateObj.getTime()) || !GUIValidation.validateDateRange(dateObj)) return this.retry();

        const hour = readlineSync.questionInt('Hora (7-21): ');
        if (hour < 7 || hour > 21) { console.log("Hora fuera de rango."); return this.retry(); }

        const minute = readlineSync.questionInt('Minutos (0-59): ');
        
        const customDate = new DateModel(
            dateObj.getDate() + 1, 
            dateObj.getMonth() + 1,
            dateObj.getFullYear(),
            hour,
            minute
        );
        
        const appointment = new Appointment(parseInt(appId), customDate, parseInt(patId), parseInt(docId));
        
        console.log("\n--- Confirmar Datos ---");
        console.log(appointment.toString());
        
        if (readlineSync.keyInYN('¿Guardar Cita?')) {
            await this.saveAppointment(appointment, dateObj);
        } else {
            console.log("Operación cancelada.");
        }
        
        await this.mongoManager.close();
    }

    async saveAppointment(appointment, jsDateObj) {
        try {
            if (await this.checkIdExists(appointment.appointmentId)) {
                console.log(`Error: El ID de cita ${appointment.appointmentId} ya existe.`);
                return;
            }
            if (!(await this.checkPatientExists(appointment.patientId))) {
                console.log(`Error: El paciente ${appointment.patientId} no existe.`);
                return;
            }
            if (!(await this.checkDoctorExists(appointment.doctorId))) {
                console.log(`Error: El doctor ${appointment.doctorId} no existe.`);
                return;
            }
            if (await this.checkScheduleConflict(appointment.doctorId, jsDateObj, appointment.date.hour, appointment.date.minute)) {
                console.log("Error: El doctor ya tiene cita en ese horario.");
                return;
            }

            const dateDoc = this.mongoManager.createDateDocument(jsDateObj, appointment.date.hour, appointment.date.minute);
            
            const docToSave = {
                appointmentId: appointment.appointmentId,
                patientId: appointment.patientId,
                doctorId: appointment.doctorId,
                status: "Agendado",
                date: dateDoc
            };

            await this.mongoManager.insert("appointments", docToSave);
            
            const formattedDate = `${appointment.date.day}/${appointment.date.month}/${appointment.date.year} a las ${appointment.date.hour}:${appointment.date.minute}`;
            this.sendNotificationInBackground(String(appointment.patientId), formattedDate);

            console.log(">> EXITO: La cita fue guardada.");

        } catch (e) {
            console.error("Error guardando cita:", e);
        }
    }

    retry() {
        console.log("\nIntente nuevamente...");
        setTimeout(() => this.setVisible(true), 1500);
    }

    async checkIdExists(id) {
        const res = await this.mongoManager.find("appointments", { appointmentId: id });
        return res.length > 0;
    }
    async checkPatientExists(id) {
        const res = await this.mongoManager.find("patients", { patientId: id });
        return res.length > 0;
    }
    async checkDoctorExists(id) {
        const res = await this.mongoManager.find("doctors", { doctorId: id });
        return res.length > 0;
    }
    async checkScheduleConflict(docId, dateObj, hour, minute) {
        const dateStr = dateObj.toISOString().split('T')[0];
        const timeStr = `${String(hour).padStart(2,'0')}:${String(minute).padStart(2,'0')}`;
        
        const filter = {
            doctorId: docId,
            "date.date": dateStr,
            "date.time": timeStr
        };
        const res = await this.mongoManager.find("appointments", filter);
        return res.length > 0;
    }

    sendNotificationInBackground(patientId, dateInfo) {
        (async () => {
            try {
                const service = new AppointmentNotificationService();
                await service.init(); 

                const patIdInt = parseInt(patientId);
                const email = await this.mongoManager.getEmail("patients", "patientId", patIdInt);
                const name = await this.mongoManager.getInfo("patients", "patientId", patIdInt, "fullName");

                if (email && name) {
                    await service.sendReservationConfirmation(email, name, dateInfo);
                } else {
                    console.log("No se pudo notificar: Falta email o nombre.");
                }
            } catch (e) {
                console.error("Error bg notification:", e);
            }
        })();
    }
}
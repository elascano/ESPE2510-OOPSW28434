import EmailConfig from "./EmailConfig.js";

export default class EmailService {
  static async sendEmail(to, message) {
    const config = await EmailConfig.getInstance();

    console.log("----- EMAIL SENT -----");
    console.log("From:", config.senderEmail);
    console.log("To:", to);
    console.log("Message:", message);
    console.log("----------------------");
  }
}

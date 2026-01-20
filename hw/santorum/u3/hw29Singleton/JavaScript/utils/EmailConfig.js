class EmailConfig {
  static instance;

  constructor(senderEmail) {
    if (EmailConfig.instance) {
      return EmailConfig.instance;
    }

    this.senderEmail = senderEmail;
    EmailConfig.instance = this;
  }

  static async getInstance() {
    if (!EmailConfig.instance) {
      const response = await fetch("../email_config.json");
      const data = await response.json();
      new EmailConfig(data.senderEmail);
    }
    return EmailConfig.instance;
  }
}

export default EmailConfig;

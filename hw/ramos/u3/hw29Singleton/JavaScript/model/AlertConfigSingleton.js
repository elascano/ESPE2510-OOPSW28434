const fs = require('fs');
const path = require('path');

class AlertConfigSingleton {
    static instance;

    constructor() {
        this.filePath = path.join(__dirname, '../data/alert_config.json');
        this.alertDays = 3;
        this.load();
    }

    static getInstance() {
        if (!AlertConfigSingleton.instance) {
            AlertConfigSingleton.instance = new AlertConfigSingleton();
        }
        return AlertConfigSingleton.instance;
    }

    getAlertDays() {
        return this.alertDays;
    }

    setAlertDays(days) {
        this.alertDays = days;
        this.save();
    }

    load() {
        if (!fs.existsSync(this.filePath)) {
            this.save();
            return;
        }
        const data = JSON.parse(fs.readFileSync(this.filePath));
        this.alertDays = data.alertDays;
    }

    save() {
        fs.mkdirSync(path.dirname(this.filePath), { recursive: true });
        fs.writeFileSync(this.filePath, JSON.stringify(
            { alertDays: this.alertDays }, null, 2
        ));
    }
}

module.exports = AlertConfigSingleton;

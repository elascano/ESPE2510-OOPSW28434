const Photographer = require("../model/Photographer");
const JsonFileUtil = require("../utils/JsonFileUtil");

class PhotographerController {

    register(name, specialty, experienceText, rateText) {

        const experience = parseInt(experienceText);
        const hourlyRate = parseFloat(rateText);

        if (isNaN(experience) || isNaN(hourlyRate)) {
            throw new Error("Experience and hourly rate must be numeric");
        }

        const photographer = new Photographer(
            name,
            specialty,
            experience,
            hourlyRate
        );

        JsonFileUtil.getInstance().save(photographer);
    }
}

module.exports = PhotographerController;

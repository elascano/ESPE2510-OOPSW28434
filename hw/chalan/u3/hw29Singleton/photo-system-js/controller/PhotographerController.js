const Photographer = require("../model/Photographer");
const JsonFileUtil = require("../utils/JsonFileUtil");
const MongoDbUtil = require("../utils/MongoDbUtil");

class PhotographerController { // 

    async register(name, specialty, experienceText, rateText) {
        const experience = parseInt(experienceText);
        const hourlyRate = parseFloat(rateText);

        if (isNaN(experience) || isNaN(hourlyRate)) {
            throw new Error("Values must be numeric");
        }

        const photographer = new Photographer(name, specialty, experience, hourlyRate);

        // 
        JsonFileUtil.getInstance().save(photographer);
        await MongoDbUtil.getInstance().save(photographer);
    }

    async getPhotographers() {
        return await MongoDbUtil.getInstance().getAll(); 
    }

    
    async findPhotographer(name) {
        return await MongoDbUtil.getInstance().getOne(name); 
    }
}

module.exports = PhotographerController;
const fs = require('fs').promises
const Chicken = require('./Chicken');
const ChickenCoop = require('./ChickenCoop');

class ChickenCoopManagement{

    static async saveChickenDataToCsv(listCoops, filname = "chickenRegistration.csv"){
        const fieldTitle = ["ID", "NAME", "COLOR", "AGE", "IS MOLTING", "COOP ID"];
        const allChickenData = [];
        for (const coop of listCoops){
            if (coop instanceof ChickenCoop){
                const coopId = coop.getChickenCoopNumber();
                for (const chicken of coop.getChickenCoop()){
                    if (chicken instanceof Chicken){
                        const chickenData = {
                            "ID": chicken.getId(),
                            "NAME": chicken.getName(),
                            "COLOR": chicken.getColor(),
                            "AGE": chicken.getAge(),
                            "IS MOLTING": chicken.getIsMolting(),
                            "COOP ID": coopId
                        };
                        allChickenData.push(chickenData);
                    }
                }
            }
        }
        if (allChickenData.length === 0){
            console.log("There is no chicken data to save");
            return;
        }

        try {
            const csvContent = this.convertToCsv(allChickenData, fieldTitle, ";");
            await fs.writeFile(filname, csvContent, 'utf-8');

        } catch (e){
            console.error(`Erro de guardado ${e.message}`);
        }
    }

    static convertToCsv(chickenData, headers, delimiter){
        const headersRow = headers.join(delimiter);
        const chickenDataRows = chickenData.map(row => 
            headers.map(headers => row[headers]).join(delimiter)
        );
        return [headersRow, ...chickenDataRows].join('\n');
    }
}

module.exports = ChickenCoopManagement;
    


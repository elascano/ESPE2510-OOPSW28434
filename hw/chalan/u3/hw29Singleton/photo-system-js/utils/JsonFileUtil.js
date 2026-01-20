const fs = require("fs");

class JsonFileUtil {

    constructor() {
        if (JsonFileUtil.instance) {
            return JsonFileUtil.instance;
        }

        this.filePath = "photographers.json";
        JsonFileUtil.instance = this;
    }

    static getInstance() {
        if (!JsonFileUtil.instance) {
            JsonFileUtil.instance = new JsonFileUtil();
        }
        return JsonFileUtil.instance;
    }

    save(photographer) {
        let data = [];

        if (fs.existsSync(this.filePath)) {
            const fileContent = fs.readFileSync(this.filePath);
            data = JSON.parse(fileContent);
        }

        data.push(photographer);

        fs.writeFileSync(this.filePath, JSON.stringify(data, null, 2));
        console.log("Photographer saved to JSON");
    }
}

module.exports = JsonFileUtil;

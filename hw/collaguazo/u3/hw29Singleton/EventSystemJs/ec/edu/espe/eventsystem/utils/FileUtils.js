import fs from "fs";

export class FileUtils {

    static readJSON(path) {
        const data = fs.readFileSync(path, "utf-8");
        return JSON.parse(data);
    }

    static writeJSON(path, data) {
        fs.writeFileSync(path, JSON.stringify(data, null, 2));
    }
}

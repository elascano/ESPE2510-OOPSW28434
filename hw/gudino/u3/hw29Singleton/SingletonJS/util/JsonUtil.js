import fs from "fs";

const FILE_PATH = "./products.json";

export default class JsonUtil {
    static saveProducts(products) {
        fs.writeFileSync(FILE_PATH, JSON.stringify(products, null, 2));
    }

    static loadProducts() {
        if (!fs.existsSync(FILE_PATH)) return null;
        const data = fs.readFileSync(FILE_PATH);
        return JSON.parse(data);
    }
}

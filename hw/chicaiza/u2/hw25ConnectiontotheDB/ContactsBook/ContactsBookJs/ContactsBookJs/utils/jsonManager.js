const fs = require("fs");
const path = require("path");

class JsonManager {
    static saveContact(contact) {
        const dataDir = path.join(__dirname, "..", "data"); // Carpeta data
        const jsonPath = path.join(dataDir, "contacts.json");

        // Crear carpeta si no existe
        if (!fs.existsSync(dataDir)) {
            fs.mkdirSync(dataDir);
        }

        let data = [];

        // Leer JSON si existe
        if (fs.existsSync(jsonPath)) {
            const fileContent = fs.readFileSync(jsonPath, "utf8");
            data = JSON.parse(fileContent || "[]");
        }

        // Agregar nuevo contacto
        data.push(contact);

        // Guardar en archivo
        fs.writeFileSync(jsonPath, JSON.stringify(data, null, 2));

        console.log("Contacto guardado en contacts.json");
    }
}

module.exports = JsonManager;

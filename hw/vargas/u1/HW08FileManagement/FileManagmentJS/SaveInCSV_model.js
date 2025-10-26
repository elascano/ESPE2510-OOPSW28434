import fs from 'fs';
import path from 'path';
import { fileURLToPath } from 'url'; 

export class File {
    constructor(fileName, headers) {
        const __filename = fileURLToPath(import.meta.url); 
        const __dirname = path.dirname(__filename);
    
        this.fullPath = path.join(__dirname, fileName); 
        
        this.fileName = fileName;
        this.headers = headers;
        this.data = []; 
    }

    addRegister(register) {
        this.data.push(register);
    }

    saveDatainCSV() {
        try {
            let csvContent = this.headers.join(",") + "\n";
            
            this.data.forEach(register => {
                const values = this.headers.map(header => {
                    let value = register[header];
            
                    if (typeof value === 'string' && value.includes(',')) {
                        value = `"${value}"`; 
                    }
                    return value;
                });
                csvContent += values.join(",") + "\n";
            });
            
            fs.writeFileSync(this.fullPath, csvContent, 'utf8');
            console.log("Data saved successfully in " + this.fileName);
            
        } catch (error) {
            console.error("Error saving data in CSV file: ", error);
        }
    }
}
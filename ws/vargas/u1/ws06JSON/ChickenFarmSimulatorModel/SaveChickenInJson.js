import * as fs from 'fs/promises';
import * as path from 'path';
import { fileURLToPath } from 'url'; 

export class ChickenSave {
  #filePath; 
  #dirPath; 

  constructor(filename = 'coops.json', dataDirectory = 'data') {
        
        const __filename = fileURLToPath(import.meta.url); 
        const __dirname = path.dirname(__filename);
        this.#dirPath = path.resolve(path.join(__dirname, '..', dataDirectory)); 
        this.#filePath = path.join(this.#dirPath, filename);
        
        console.log(`Save path set to: ${this.#filePath}`); 
  }

  async save(coops) {
    try {
      await fs.mkdir(this.#dirPath, { recursive: true });

      const serializableData = coops.map(coop => ({
        id: coop.getId(),
        name: coop.getName(),
        
        chickens: coop.getChickens().map(chicken => ({
          id: chicken._id, 
          name: chicken._name,
          color: chicken._color,
          age: chicken._age,
          isMolting: chicken._isMolting
        }))
      }));

      const data = JSON.stringify(serializableData, null, 2); 
      
      await fs.writeFile(this.#filePath, data, 'utf-8');
      
      console.log(`Coop and Chicken data successfully saved to: ${this.#filePath}`);
    } catch (error) {
      console.error('Error saving coop data:', error.message);
      throw new Error('Failed to serialize and save data.');
    }
  }

  async load() {
    try {
      const data = await fs.readFile(this.#filePath, 'utf-8');
      const loadedCoops = JSON.parse(data);
      
      console.log(`Coop and Chicken data successfully loaded from: ${this.#filePath}`);
      
      return loadedCoops; 
    } catch (error) {
      if (error.code === 'ENOENT') {
        console.log(`File ${this.#filePath} not found. Returning empty array.`);
        return [];
      }
      
      console.error('Error loading or parsing coop data:', error.message);
      throw new Error('Failed to deserialize data.');
    }
  }
}
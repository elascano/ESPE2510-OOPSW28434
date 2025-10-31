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


  /**
   * Read the JSON file, verify its existence, and parse its contents.
   * @returns {Promise<Array<Object>>} 
   * @private
   */
  async #readAndParseFile() {
    try {
      const data = await fs.readFile(this.#filePath, 'utf-8');
      return JSON.parse(data);
    } catch (error) {
      if (error.code === 'ENOENT') {
        console.log(`File ${this.#filePath} not found. Returning empty array.`);
        return [];
      }
      console.error('Error loading or parsing coop data:', error.message);
      throw new Error('Failed to deserialize data.');
    }
  }

  /**
   * Save data in de Json File.
   * @param {Array<Object>} data 
   * @private
   */
  async #saveDataToFile(data) {
    try {
      await fs.mkdir(this.#dirPath, { recursive: true });
      const jsonData = JSON.stringify(data, null, 2);
      await fs.writeFile(this.#filePath, jsonData, 'utf-8');
      console.log(`Data successfully saved to: ${this.#filePath}`);
    } catch (error) {
      console.error('Error saving data:', error.message);
      throw new Error('Failed to save data to file.');
    }
  }

  async save(coops) {
    try {
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

      await this.#saveDataToFile(serializableData);

      console.log(`Coop and Chicken data successfully serialized and saved.`);
    } catch (error) {
      console.error('Error saving coop data:', error.message);
      throw new Error('Failed to serialize and save data.');
    }
  }

  async load() {
    const loadedCoops = await this.#readAndParseFile();
    console.log(`Coop and Chicken data successfully loaded from: ${this.#filePath}`);
    return loadedCoops;
  }


  /**
   * Search for a cooperative (coop) or a chicken (chicken) by its ID.
   * Verify the existence of the file and the data.
   * @param {string | number} id 
   * @param {'coop' | 'chicken'} type 
   * @returns {Promise<Object | null>} 
   */
  async findCoopOrChickenById(id, type) {
    const data = await this.#readAndParseFile();

    if (data.length === 0) {
      console.log('No data loaded to search.');
      return null;
    }

    if (type === 'coop') {
      const coop = data.find(c => c.id === id);
      if (coop) {
        console.log(`Coop found with ID: ${id}`);
        return coop;
      }
    } else if (type === 'chicken') {
      for (const coop of data) {
        const chicken = coop.chickens.find(c => c.id === id);
        if (chicken) {
          console.log(`Chicken found with ID: ${id} in Coop: ${coop.name}`);
          return { coop, chicken };
        }
      }
    } else {
      throw new Error("Invalid type for search. Must be 'coop' or 'chicken'.");
    }

    console.log(`${type} with ID ${id} not found.`);
    return null;
  }
  
  /**
   * Update existing data (coop or chicken) by its ID.
   * @param {string | number} id 
   * @param {'coop' | 'chicken'} type 
   * @param {Object} updateData 
   * @returns {Promise<Object>} 
   */
  async updateData(id, type, updateData) {
    const data = await this.#readAndParseFile();

    if (data.length === 0) {
      throw new Error('No data loaded. Cannot update.');
    }

    let updatedItem = null;
    let dataModified = false;

    if (type === 'coop') {
      const coopIndex = data.findIndex(c => c.id === id);
      if (coopIndex !== -1) {
        data[coopIndex] = { ...data[coopIndex], ...updateData, id: data[coopIndex].id }; 
        updatedItem = data[coopIndex];
        dataModified = true;
      }
    } else if (type === 'chicken') {
      for (const coop of data) {
        const chickenIndex = coop.chickens.findIndex(c => c.id === id);
        if (chickenIndex !== -1) {
          coop.chickens[chickenIndex] = { 
            ...coop.chickens[chickenIndex], 
            ...updateData, 
            id: coop.chickens[chickenIndex].id 
          }; 
          updatedItem = coop.chickens[chickenIndex];
          dataModified = true;
          break; 
        }
      }
    } else {
      throw new Error("Invalid type for update. Must be 'coop' or 'chicken'.");
    }

    if (!updatedItem) {
      throw new Error(`${type} with ID ${id} not found for update.`);
    }

    if (dataModified) {
        await this.#saveDataToFile(data);
        console.log(`${type} with ID ${id} successfully updated.`);
    }

    return updatedItem;
  }


  /**
   * Delete an existing data (coop or chicken) by its ID.
   * @param {string | number} id 
   * @param {'coop' | 'chicken'} type 
   * @returns {Promise<boolean>} 
   */
  async deleteData(id, type) {
    const data = await this.#readAndParseFile();

    if (data.length === 0) {
      console.log('No data loaded. Nothing to delete.');
      return false;
    }

    let dataModified = false;

    if (type === 'coop') {
      const initialLength = data.length;
      const newData = data.filter(c => c.id !== id);
      if (newData.length < initialLength) {
        data.splice(0, data.length, ...newData);
        dataModified = true;
      }
    } else if (type === 'chicken') {
      for (const coop of data) {
        const initialLength = coop.chickens.length;
        const newChickens = coop.chickens.filter(c => c.id !== id);
        if (newChickens.length < initialLength) {
          coop.chickens.splice(0, coop.chickens.length, ...newChickens);
          dataModified = true;
          break;
        }
      }
    } else {
      throw new Error("Invalid type for delete. Must be 'coop' or 'chicken'.");
    }

    if (dataModified) {
      await this.#saveDataToFile(data);
      console.log(`${type} with ID ${id} successfully deleted.`);
      return true;
    } else {
      console.log(`${type} with ID ${id} not found for deletion.`);
      return false;
    }
  }
}
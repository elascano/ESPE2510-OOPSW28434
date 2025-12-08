const { MongoClient } = require('mongodb');

class ContactManager {
    
    static uri = "mongodb+srv://Cesar:Cesar2006@cluster0.tgbv2qc.mongodb.net/";
    static dbName = "ContactsBookDB";
    static collectionName = "contacts";

    static async save(contact) {
        const client = new MongoClient(this.uri);
        
        try {
            await client.connect();
            const db = client.db(this.dbName);
            const collection = db.collection(this.collectionName);

            const result = await collection.insertOne(contact);
            
            if (result.insertedId) {
                console.log(`Contact saved with ID: ${result.insertedId}`);
                return true;
            }
            return false;

        } catch (error) {
            console.error("Error saving to MongoDB:", error);
            return false;
        } finally {
            await client.close();
        }
    }
}
module.exports = ContactManager;

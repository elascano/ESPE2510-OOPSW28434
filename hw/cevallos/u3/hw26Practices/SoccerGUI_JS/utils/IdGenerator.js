// utils/IdGenerator.js
import MongoDBConnection from './MongoDBConnection.js';

class IdGenerator {
    static async getNextId(collectionName) {
        try {
            const db = await MongoDBConnection.getConnection();
            const collection = db.collection(collectionName);
            
            // Encontrar el máximo ID actual
            const result = await collection
                .aggregate([
                    {
                        $group: {
                            _id: null,
                            maxId: { $max: "$id" }
                        }
                    }
                ])
                .toArray();
            
            if (result.length > 0 && result[0].maxId) {
                return result[0].maxId + 1;
            }
            
            return 1; // Si no hay documentos, empezar con 1
            
        } catch (error) {
            console.error("Error al generar ID:", error);
            // Fallback: contar documentos
            try {
                const db = await MongoDBConnection.getConnection();
                const collection = db.collection(collectionName);
                const count = await collection.countDocuments();
                return count + 1;
            } catch {
                return 1;
            }
        }
    }
}

export default IdGenerator;
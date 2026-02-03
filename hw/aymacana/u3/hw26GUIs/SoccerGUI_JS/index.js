// index.js (Main)
import ConsoleInterface from './view/ConsoleInterface.js';
import MongoDBConnection from './utils/MongoDBConnection.js';

async function main() {
    try {
        console.log('🚀 Iniciando Sistema de Equipos de Fútbol...\n');
        
        console.log('🔗 Conectando a MongoDB...');
        await MongoDBConnection.getConnection();
        
        const app = new ConsoleInterface();
        await app.showMainMenu();
        
    } catch (error) {
        console.error('❌ Error fatal:', error.message);
        await MongoDBConnection.closeConnection();
        process.exit(1);
    }
}

// Manejar Ctrl+C para cerrar conexión
process.on('SIGINT', async () => {
    console.log('\n\n👋 Saliendo del sistema...');
    await MongoDBConnection.closeConnection();
    process.exit(0);
});

// Ejecutar aplicación
main();
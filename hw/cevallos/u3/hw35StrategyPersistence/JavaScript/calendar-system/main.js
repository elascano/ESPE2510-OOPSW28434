const readline = require('readline');
const EventController = require('./controller/EventController');
const StrategyFactory = require('./factory/StrategyFactory');

class CalendarConsoleApp {
    constructor() {
        this.controller = new EventController();
        this.rl = readline.createInterface({
            input: process.stdin,
            output: process.stdout
        });
        
        this.menuOptions = {
            '1': 'Agregar evento',
            '2': 'Actualizar evento',
            '3': 'Eliminar evento',
            '4': 'Buscar evento',
            '5': 'Ver todos los eventos',
            '6': 'Cambiar tipo de almacenamiento',
            '7': 'Salir'
        };
    }

    async start() {
        console.clear();
        console.log('📅 ====================================');
        console.log('   SISTEMA CALENDAR MVC + STRATEGY');
        console.log('====================================\n');
        
        await this.showMenu();
    }

    async showMenu() {
        console.log('\nMENU PRINCIPAL:');
        console.log('────────────────');
        
        for (const [key, value] of Object.entries(this.menuOptions)) {
            console.log(`${key}. ${value}`);
        }
        
        this.rl.question('\nSeleccione una opción: ', async (choice) => {
            await this.handleChoice(choice);
        });
    }

    async handleChoice(choice) {
        switch(choice) {
            case '1':
                await this.addEvent();
                break;
            case '2':
                await this.updateEvent();
                break;
            case '3':
                await this.deleteEvent();
                break;
            case '4':
                await this.readEvent();
                break;
            case '5':
                await this.showAllEvents();
                break;
            case '6':
                await this.changeStorage();
                break;
            case '7':
                this.exit();
                return;
            default:
                console.log('\n❌ Opción no válida. Intente de nuevo.');
                await this.showMenu();
                return;
        }
        
        // Volver al menú después de cada operación
        this.rl.question('\nPresione Enter para continuar...', () => {
            this.showMenu();
        });
    }

    async addEvent() {
        console.log('\n➕ AGREGAR EVENTO');
        console.log('────────────────');
        
        try {
            const id = await this.questionAsync('ID del evento: ');
            const name = await this.questionAsync('Nombre (solo letras): ');
            const date = await this.questionAsync('Fecha (YYYY-MM-DD): ');
            
            const result = await this.controller.addEvent(id, name, date);
            
            if (result.success) {
                console.log('\n✅ Evento agregado exitosamente:');
                console.log(result.event.toString());
            } else {
                console.log(`\n❌ Error: ${result.error}`);
            }
        } catch (error) {
            console.log(`\n❌ Error: ${error.message}`);
        }
    }

    async updateEvent() {
        console.log('\n✏️  ACTUALIZAR EVENTO');
        console.log('───────────────────');
        
        try {
            const id = await this.questionAsync('ID del evento a actualizar: ');
            
            // Primero verificar si existe
            const readResult = await this.controller.readEvent(id);
            
            if (!readResult.success || !readResult.event) {
                console.log(`\n⚠️  No se encontró evento con ID: ${id}`);
                return;
            }
            
            console.log(`\nEvento actual: ${readResult.event.toString()}`);
            
            const name = await this.questionAsync('Nuevo nombre (solo letras): ');
            const date = await this.questionAsync('Nueva fecha (YYYY-MM-DD): ');
            
            const result = await this.controller.updateEvent(id, name, date);
            
            if (result.success) {
                console.log('\n✅ Evento actualizado exitosamente:');
                console.log(result.event.toString());
            } else {
                console.log(`\n❌ Error: ${result.error}`);
            }
        } catch (error) {
            console.log(`\n❌ Error: ${error.message}`);
        }
    }

    async deleteEvent() {
        console.log('\n🗑️  ELIMINAR EVENTO');
        console.log('────────────────');
        
        try {
            const id = await this.questionAsync('ID del evento a eliminar: ');
            
            const confirm = await this.questionAsync(`¿Está seguro de eliminar el evento ${id}? (s/n): `);
            
            if (confirm.toLowerCase() === 's') {
                const result = await this.controller.deleteEvent(id);
                
                if (result.success) {
                    console.log(`\n✅ Evento ${id} eliminado exitosamente`);
                } else {
                    console.log(`\n❌ No se pudo eliminar el evento ${id}`);
                }
            } else {
                console.log('\n⚠️  Operación cancelada');
            }
        } catch (error) {
            console.log(`\n❌ Error: ${error.message}`);
        }
    }

    async readEvent() {
        console.log('\n🔍 BUSCAR EVENTO');
        console.log('────────────────');
        
        try {
            const id = await this.questionAsync('ID del evento a buscar: ');
            
            const result = await this.controller.readEvent(id);
            
            if (result.success && result.event) {
                console.log('\n✅ Evento encontrado:');
                console.log(result.event.toString());
            } else {
                console.log(`\n⚠️  No se encontró evento con ID: ${id}`);
            }
        } catch (error) {
            console.log(`\n❌ Error: ${error.message}`);
        }
    }

    async showAllEvents() {
        console.log('\n📋 TODOS LOS EVENTOS');
        console.log('───────────────────');
        
        const result = await this.controller.getAllEvents();
        
        if (result.success) {
            if (result.events.length === 0) {
                console.log('No hay eventos almacenados.');
            } else {
                console.log(`Total de eventos: ${result.events.length}\n`);
                
                result.events.forEach((event, index) => {
                    console.log(`${index + 1}. ${event.toString()}`);
                });
            }
        } else {
            console.log(`\n❌ Error: ${result.error}`);
        }
    }

    async changeStorage() {
        console.log('\n🔄 CAMBIAR TIPO DE ALMACENAMIENTO');
        console.log('────────────────────────────────');
        
        console.log('Tipos disponibles:');
        console.log('1. JSON');
        console.log('2. CSV');
        console.log('3. MongoDB');
        
        const choice = await this.questionAsync('\nSeleccione tipo (1-3): ');
        
        let storageType;
        switch(choice) {
            case '1':
                storageType = StrategyFactory.StorageType.JSON;
                break;
            case '2':
                storageType = StrategyFactory.StorageType.CSV;
                break;
            case '3':
                storageType = StrategyFactory.StorageType.MONGODB;
                break;
            default:
                console.log('\n❌ Opción no válida');
                return;
        }
        
        try {
            this.controller.setStorageStrategy(storageType);
            console.log(`\n✅ Almacenamiento cambiado a: ${storageType}`);
        } catch (error) {
            console.log(`\n❌ Error: ${error.message}`);
        }
    }

    questionAsync(prompt) {
        return new Promise((resolve) => {
            this.rl.question(prompt, resolve);
        });
    }

    exit() {
        console.log('\n👋 Saliendo del sistema Calendar...');
        console.log('¡Hasta pronto!');
        this.rl.close();
        process.exit(0);
    }
}

// Inicializar la aplicación
async function main() {
    const app = new CalendarConsoleApp();
    
    // Manejar Ctrl+C para salir limpiamente
    process.on('SIGINT', () => {
        console.log('\n\n👋 Interrupción detectada. Saliendo...');
        app.rl.close();
        process.exit(0);
    });
    
    await app.start();
}

// Ejecutar la aplicación
if (require.main === module) {
    main().catch(console.error);
}

module.exports = CalendarConsoleApp;
/**
 * main.js
 * Archivo principal para inicializar la simulación y ejecutarla.
 */
const AntSimulator = require('./AntSimulator');
const Colony = require('./Colony');
const AntEater = require('./AntEater');
const FoodPile = require('./FoodPile');
const GroundCell = require('./GroundCell'); // Necesario para la lógica de añadir comida

// --- Parámetros de Simulación ---
const SIM_WIDTH = 25;
const SIM_HEIGHT = 25;
const SIM_TICKS = 300;
const TICK_DURATION = 50; // en milisegundos

async function main() {
    try {
        console.log('\n======================================================');
        console.log('         Iniciando Simulación de Ecosistema de Hormigas');
        console.log('======================================================');

        // 1. Inicializar Simulador
        const simulator = new AntSimulator(SIM_WIDTH, SIM_HEIGHT, TICK_DURATION);

        // 2. Crear Colonias
        const colony1 = new Colony('Colonia Norte', 5, 5, simulator);
        const colony2 = new Colony('Colonia Sur', 20, 20, simulator);
        simulator.addColony(colony1);
        simulator.addColony(colony2);
        console.log(` >> Dos colonias creadas: ${colony1.name} y ${colony2.name}.`);

        // 3. Crear Osos Hormigueros
        const antEater1 = new AntEater(12, 12);
        const antEater2 = new AntEater(8, 18);
        simulator.addAntEater(antEater1);
        simulator.addAntEater(antEater2);
        console.log(` >> Dos osos hormigueros (${antEater1.constructor.name}) agregados.`);

        // 4. Agregar Puntos de Comida Aleatorios
        let foodPileCount = 0;
        for (let i = 0; i < 20; i++) {
            const x = Math.floor(Math.random() * SIM_WIDTH);
            const y = Math.floor(Math.random() * SIM_HEIGHT);
            const cell = simulator.getCell(x, y);
            if (cell) {
                const amount = 15 + Math.floor(Math.random() * 30);
                cell.addFood(amount);
                foodPileCount++;
            }
        }
        console.log(` >> ${foodPileCount} pilas de comida distribuidas aleatoriamente.`);

        // 5. Estado Inicial
        const totalAntsInitial = simulator.colonies.reduce((sum, col) => sum + col.ants.length, 0);
        console.log(' --- Estado Inicial ---');
        console.log(`   - Total de Hormigas: ${totalAntsInitial}`);
        
        // 6. Ejecutar
        await simulator.runSimulation(SIM_TICKS); 
        
        // 7. Estado Final
        const totalAntsFinal = simulator.colonies.reduce((sum, col) => sum + col.ants.length, 0);
        console.log('\n======================================================');
        console.log('               Simulación Completada');
        console.log('======================================================');
        console.log(`   - **Hormigas Sobrevivientes**: ${totalAntsFinal}`);
        console.log(`   - **Comida en Nido ${colony1.name}**: ${colony1.nest.getFoodAmount()}mg`);
        console.log(`   - **Comida en Nido ${colony2.name}**: ${colony2.nest.getFoodAmount()}mg`);
        
        console.log('\n --- Estadísticas de Osos Hormigueros ---');
        simulator.antEaters.forEach((eater, index) => {
            console.log(`   - Oso ${index + 1} (${eater.state}): comió ${eater.antsEaten} hormigas`);
        });
        
    } catch (error) {
        console.error('\n *** ERROR FATAL EN LA SIMULACIÓN ***:', error);
    }
}

main();
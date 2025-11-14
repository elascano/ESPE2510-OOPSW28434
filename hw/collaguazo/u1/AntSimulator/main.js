const AntSimulator = require('./AntSimulator');
const Colony = require('./Colony');
const AntEater = require('./AntEater');

async function main() {
    try {
        console.log('Iniciando simulación de hormigas...');

        // Crear simulador
        const simulator = new AntSimulator(20, 20, 50);
        console.log('Simulador inicializado');

        // Crear colonias
        const colonyNorth = new Colony('Colonia Norte', 5, 5, simulator);
        const colonySouth = new Colony('Colonia Sur', 15, 15, simulator);
        simulator.addColony(colonyNorth);
        simulator.addColony(colonySouth);
        console.log('Colonias agregadas al simulador');

        // Crear osos hormigueros
        const eater1 = new AntEater(10, 10);
        const eater2 = new AntEater(8, 12);
        simulator.addAntEater(eater1);
        simulator.addAntEater(eater2);
        console.log('Osos hormigueros colocados');

        // Agregar comida aleatoria
        let totalFoodPiles = 0;
        for (let i = 0; i < 10; i++) {
            const x = Math.floor(Math.random() * 20);
            const y = Math.floor(Math.random() * 20);
            const cell = simulator.getCell(x, y);
            if (cell) {
                cell.addFood(10 + Math.floor(Math.random() * 20));
                totalFoodPiles++;
            }
        }

        // Agregar comida alrededor de los nidos
        const nests = [[5, 5], [15, 15]];
        for (const [nx, ny] of nests) {
            for (let dx = -3; dx <= 3; dx++) {
                for (let dy = -3; dy <= 3; dy++) {
                    if (dx === 0 && dy === 0) continue;
                    const cell = simulator.getCell(nx + dx, ny + dy);
                    if (cell && Math.random() > 0.3) {
                        cell.addFood(8 + Math.floor(Math.random() * 15));
                        totalFoodPiles++;
                    }
                }
            }
        }
        console.log(${totalFoodPiles} pilas de comida colocadas (incluyendo cerca de nidos));

        // Estado inicial
        console.log('Estado inicial:');
        console.log(`  - Colonias: ${simulator.colonies.length}`);
        console.log(`  - Osos hormigueros: ${simulator.antEaters.length}`);
        console.log(`  - Hormigas totales: ${simulator.colonies.reduce((sum, col) => sum + col.ants.length, 0)}`);

        // Contar comida cerca de los nidos
        let foodNearNorth = 0;
        let foodNearSouth = 0;
        for (let dx = -2; dx <= 2; dx++) {
            for (let dy = -2; dy <= 2; dy++) {
                const cellNorth = simulator.getCell(5 + dx, 5 + dy);
                const cellSouth = simulator.getCell(15 + dx, 15 + dy);
                if (cellNorth && cellNorth.getFoodAmount() > 0) foodNearNorth++;
                if (cellSouth && cellSouth.getFoodAmount() > 0) foodNearSouth++;
            }
        }
        console.log(`  - Comida cerca de Colonia Norte: ${foodNearNorth} celdas`);
        console.log(`  - Comida cerca de Colonia Sur: ${foodNearSouth} celdas`);

        // Ejecutar simulación
        console.log('Ejecutando simulación...');
        await simulator.runSimulation(200);
        console.log('Simulación completada');

        // Estado final
        console.log('Estado final:');
        console.log(`  - Hormigas sobrevivientes: ${simulator.colonies.reduce((sum, col) => sum + col.ants.length, 0)}`);
        console.log(`  - Comida en nido Norte: ${colonyNorth.nest.getFoodAmount()}mg`);
        console.log(`  - Comida en nido Sur: ${colonySouth.nest.getFoodAmount()}mg`);
        console.log(`  - Hormigas en Colonia Norte: ${colonyNorth.ants.length}`);
        console.log(`  - Hormigas en Colonia Sur: ${colonySouth.ants.length}`);

        // Estadísticas de osos hormigueros
        console.log('Estadísticas de osos hormigueros:');
        simulator.antEaters.forEach((eater, i) => {
            console.log(`  - Oso ${i + 1}: comió ${eater.antsEaten} hormigas`);
        });

    } catch (err) {
        console.error('Error durante la simulación:', err);
        console.error('Stack trace:', err.stack);
    }
}

main();

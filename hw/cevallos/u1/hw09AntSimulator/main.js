const AntSimulator = require('./AntSimulator');
const Colony = require('./Colony');
const AntEater = require('./AntEater');

async function main() {
    try {
        console.log(' Iniciando simulación de hormigas...');

        
        const simulator = new AntSimulator(20, 20, 50);
        console.log(' Simulador creado');

        
        const colony1 = new Colony('Colonia Norte', 5, 5, simulator);
        const colony2 = new Colony('Colonia Sur', 15, 15, simulator);
        console.log(' Colonias creadas');

        simulator.addColony(colony1);
        simulator.addColony(colony2);

        
        const antEater1 = new AntEater(10, 10);
        const antEater2 = new AntEater(8, 12);
        console.log(' Osos hormigueros creados');

        simulator.addAntEater(antEater1);
        simulator.addAntEater(antEater2);

        
        let foodCount = 0;
        for (let i = 0; i < 10; i++) {
            const x = Math.floor(Math.random() * 20);
            const y = Math.floor(Math.random() * 20);
            const cell = simulator.getCell(x, y);
            if (cell) {
                cell.addFood(10 + Math.floor(Math.random() * 20));
                foodCount++;
            }
        }
        console.log(` ${foodCount} pilas de comida agregadas aleatoriamente`);

        
        const nestPositions = [[5, 5], [15, 15]];
        for (const [nestX, nestY] of nestPositions) {
            for (let i = -3; i <= 3; i++) {
                for (let j = -3; j <= 3; j++) {
                    if (i === 0 && j === 0) continue; 
                    const x = nestX + i;
                    const y = nestY + j;
                    const cell = simulator.getCell(x, y);
                    if (cell && Math.random() > 0.3) { 
                        cell.addFood(8 + Math.floor(Math.random() * 15));
                        foodCount++;
                    }
                }
            }
        }
        console.log(` ${foodCount} pilas de comida totales (incluyendo cerca de nidos)`);

        
        console.log(' Estado inicial:');
        console.log(`   - Colonias: ${simulator.colonies.length}`);
        console.log(`   - Osos hormigueros: ${simulator.antEaters.length}`);
        console.log(`   - Hormigas totales: ${simulator.colonies.reduce((sum, col) => sum + col.ants.length, 0)}`);
        
        
        let foodNearNest1 = 0;
        let foodNearNest2 = 0;
        
        for (let i = -2; i <= 2; i++) {
            for (let j = -2; j <= 2; j++) {
                const cell1 = simulator.getCell(5 + i, 5 + j);
                const cell2 = simulator.getCell(15 + i, 15 + j);
                if (cell1 && cell1.getFoodAmount() > 0) foodNearNest1++;
                if (cell2 && cell2.getFoodAmount() > 0) foodNearNest2++;
            }
        }
        console.log(`   - Comida cerca de Colonia Norte: ${foodNearNest1} celdas`);
        console.log(`   - Comida cerca de Colonia Sur: ${foodNearNest2} celdas`);
        
        
        console.log(' Ejecutando simulación...');
        await simulator.runSimulation(200); 
        
        console.log(' Simulación completada');
        console.log(' Estado final:');
        console.log(`   - Hormigas sobrevivientes: ${simulator.colonies.reduce((sum, col) => sum + col.ants.length, 0)}`);
        console.log(`   - Comida en nido Norte: ${colony1.nest.getFoodAmount()}mg`);
        console.log(`   - Comida en nido Sur: ${colony2.nest.getFoodAmount()}mg`);
        console.log(`   - Hormigas en Colonia Norte: ${colony1.ants.length}`);
        console.log(`   - Hormigas en Colonia Sur: ${colony2.ants.length}`);
        
        
        console.log(' Estadísticas de osos hormigueros:');
        simulator.antEaters.forEach((eater, index) => {
            console.log(`   - Oso ${index + 1}: comió ${eater.antsEaten} hormigas`);
        });
        
    } catch (error) {
        console.error(' Error en la simulación:', error);
        console.error('Stack trace:', error.stack);
    }
}

main();
/**
 * Colony.js
 * Clase que agrupa un nido y sus hormigas.
 */
const Nest = require('./Nest');
const Ant = require('./Ant');

class Colony {
    /**
     * @param {string} name - Nombre de la colonia.
     * @param {number} nestX - Coordenada X del nido.
     * @param {number} nestY - Coordenada Y del nido.
     * @param {AntSimulator} simulator - Referencia al simulador.
     */
    constructor(name, nestX, nestY, simulator) {
        this.name = name;
        this.simulator = simulator;
        this.nest = new Nest(nestX, nestY, this);
        this.ants = [];
        this.initialAnts = 5; 
        
        // Inicializar hormigas
        for (let i = 0; i < this.initialAnts; i++) {
            this.createAnt();
        }
    }

    /**
     * Crea una nueva hormiga y la coloca en el nido.
     * @returns {Ant} La nueva hormiga.
     */
    createAnt() {
        const ant = new Ant(this.nest.x, this.nest.y, this);
        this.ants.push(ant);
        
        const cell = this.simulator.getCell(this.nest.x, this.nest.y);
        if (cell) {
            cell.addResident(ant); // Usar addResident
        }
        
        return ant;
    }

    /**
     * Actualiza el estado de todas las hormigas de la colonia.
     */
    update(tick) {
        for (let i = this.ants.length - 1; i >= 0; i--) {
            const ant = this.ants[i];
            ant.update(tick, this.simulator);
            
            // Lógica de eliminación por inanición
            if (ant.weight <= 0) {
                const cell = this.simulator.getCell(ant.x, ant.y);
                if (cell) {
                    cell.removeResident(ant); // Usar removeResident
                }
                this.ants.splice(i, 1);
            }
        }
    }

    /**
     * Elimina una hormiga del array (ej. comida por un oso hormiguero).
     * @param {Ant} ant - La hormiga a eliminar.
     */
    removeAnt(ant) {
        const index = this.ants.indexOf(ant);
        if (index > -1) {
            this.ants.splice(index, 1);
        }
    }
}

module.exports = Colony;
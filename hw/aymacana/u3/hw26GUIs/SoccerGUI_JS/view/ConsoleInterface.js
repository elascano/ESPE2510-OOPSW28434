// view/ConsoleInterface.js
import inquirer from 'inquirer';
import SoccerTeamController from '../controller/SoccerTeamController.js';
import MongoDBConnection from '../utils/MongoDBConnection.js';

class ConsoleInterface {
    constructor() {
        this.controller = new SoccerTeamController();
    }

    async showMainMenu() {
        console.clear();
        console.log('╔════════════════════════════════════════╗');
        console.log('║    SISTEMA DE EQUIPOS DE FÚTBOL       ║');
        console.log('╠════════════════════════════════════════╣');
        console.log('║  1. Agregar nuevo equipo              ║');
        console.log('║  2. Buscar equipo por ID              ║');
        console.log('║  3. Ver todos los equipos             ║');
        console.log('║  4. Actualizar equipo                 ║');
        console.log('║  5. Eliminar equipo                   ║');
        console.log('║  6. Ver estadísticas                  ║');
        console.log('║  7. Salir                             ║');
        console.log('╚════════════════════════════════════════╝');

        const { option } = await inquirer.prompt([
            {
                type: 'input',
                name: 'option',
                message: 'Seleccione una opción (1-7):',
                validate: input => {
                    const num = parseInt(input);
                    return (num >= 1 && num <= 7) || 'Por favor ingrese un número entre 1 y 7';
                }
            }
        ]);

        await this.handleOption(parseInt(option));
    }

    async handleOption(option) {
        switch (option) {
            case 1:
                await this.addTeam();
                break;
            case 2:
                await this.searchTeam();
                break;
            case 3:
                await this.showAllTeams();
                break;
            case 4:
                await this.updateTeam();
                break;
            case 5:
                await this.deleteTeam();
                break;
            case 6:
                await this.showStatistics();
                break;
            case 7:
                await this.exit();
                return;
        }

        // Volver al menú principal
        await this.pressToContinue();
        await this.showMainMenu();
    }

    async addTeam() {
        console.clear();
        console.log('╔════════════════════════════════════════╗');
        console.log('║         AGREGAR NUEVO EQUIPO          ║');
        console.log('╚════════════════════════════════════════╝\n');

        const answers = await inquirer.prompt([
            {
                type: 'input',
                name: 'teamName',
                message: 'Nombre del equipo:',
                validate: input => input.trim() ? true : 'El nombre es requerido'
            },
            {
                type: 'input',
                name: 'coachName',
                message: 'Nombre del entrenador:',
                validate: input => input.trim() ? true : 'El nombre del entrenador es requerido'
            },
            {
                type: 'input',
                name: 'neighborhoodCity',
                message: 'Ciudad/Barrio:',
                validate: input => input.trim() ? true : 'La ciudad/barrio es requerido'
            },
            {
                type: 'input',
                name: 'numberOfPlayers',
                message: 'Número de jugadores (4-26):',
                validate: input => {
                    const num = parseInt(input);
                    return (num >= 4 && num <= 26) || 'Debe ser un número entre 4 y 26';
                }
            }
        ]);

        const result = await this.controller.addTeam(
            answers.teamName,
            answers.coachName,
            answers.neighborhoodCity,
            parseInt(answers.numberOfPlayers)
        );

        console.log('\n' + result.message);
        if (result.success && result.data) {
            console.log(result.data.displayDetails());
        }
    }

    async searchTeam() {
        console.clear();
        console.log('╔════════════════════════════════════════╗');
        console.log('║         BUSCAR EQUIPO POR ID          ║');
        console.log('╚════════════════════════════════════════╝\n');

        const { id } = await inquirer.prompt([
            {
                type: 'input',
                name: 'id',
                message: 'Ingrese el ID del equipo:',
                validate: input => {
                    const num = parseInt(input);
                    return (num > 0) || 'El ID debe ser un número mayor a 0';
                }
            }
        ]);

        const result = await this.controller.getTeam(parseInt(id));
        
        console.log('\n' + result.message);
        if (result.success && result.data) {
            console.log(result.data.displayDetails());
        }
    }

    async showAllTeams() {
        console.clear();
        console.log('╔════════════════════════════════════════╗');
        console.log('║         TODOS LOS EQUIPOS             ║');
        console.log('╚════════════════════════════════════════╝\n');

        const result = await this.controller.getAllTeams();
        
        console.log(result.message + '\n');
        
        if (result.success && result.data.length > 0) {
            result.data.forEach(team => {
                console.log(team.toString());
            });
            console.log(`\nTotal: ${result.data.length} equipos`);
        } else {
            console.log('No hay equipos registrados.');
        }
    }

    async updateTeam() {
        console.clear();
        console.log('╔════════════════════════════════════════╗');
        console.log('║           ACTUALIZAR EQUIPO           ║');
        console.log('╚════════════════════════════════════════╝\n');

        // Primero buscar el equipo
        const { id } = await inquirer.prompt([
            {
                type: 'input',
                name: 'id',
                message: 'Ingrese el ID del equipo a actualizar:',
                validate: input => {
                    const num = parseInt(input);
                    return (num > 0) || 'El ID debe ser un número mayor a 0';
                }
            }
        ]);

        const searchResult = await this.controller.getTeam(parseInt(id));
        
        if (!searchResult.success) {
            console.log('\n' + searchResult.message);
            return;
        }

        console.log('\n' + searchResult.data.displayDetails());
        console.log('\nIngrese los nuevos valores (deje vacío para mantener el actual):\n');

        const answers = await inquirer.prompt([
            {
                type: 'input',
                name: 'teamName',
                message: `Nuevo nombre [${searchResult.data.teamName}]:`,
                default: searchResult.data.teamName
            },
            {
                type: 'input',
                name: 'coachName',
                message: `Nuevo entrenador [${searchResult.data.coachName}]:`,
                default: searchResult.data.coachName
            },
            {
                type: 'input',
                name: 'neighborhoodCity',
                message: `Nueva ciudad [${searchResult.data.neighborhoodCity}]:`,
                default: searchResult.data.neighborhoodCity
            },
            {
                type: 'input',
                name: 'numberOfPlayers',
                message: `Nuevo número de jugadores [${searchResult.data.numberOfPlayers}]:`,
                validate: input => {
                    if (input === '') return true; // Permitir vacío
                    const num = parseInt(input);
                    return (num >= 4 && num <= 26) || 'Debe ser un número entre 4 y 26';
                },
                default: searchResult.data.numberOfPlayers.toString()
            }
        ]);

        const updateResult = await this.controller.updateTeam(
            parseInt(id),
            answers.teamName,
            answers.coachName,
            answers.neighborhoodCity,
            answers.numberOfPlayers ? parseInt(answers.numberOfPlayers) : undefined
        );

        console.log('\n' + updateResult.message);
        if (updateResult.success && updateResult.data) {
            console.log(updateResult.data.displayDetails());
        }
    }

    async deleteTeam() {
        console.clear();
        console.log('╔════════════════════════════════════════╗');
        console.log('║            ELIMINAR EQUIPO            ║');
        console.log('╚════════════════════════════════════════╝\n');

        const { id } = await inquirer.prompt([
            {
                type: 'input',
                name: 'id',
                message: 'Ingrese el ID del equipo a eliminar:',
                validate: input => {
                    const num = parseInt(input);
                    return (num > 0) || 'El ID debe ser un número mayor a 0';
                }
            }
        ]);

        const { confirm } = await inquirer.prompt([
            {
                type: 'confirm',
                name: 'confirm',
                message: '¿Está seguro de eliminar este equipo?',
                default: false
            }
        ]);

        if (confirm) {
            const result = await this.controller.deleteTeam(parseInt(id));
            console.log('\n' + result.message);
        } else {
            console.log('\n❌ Eliminación cancelada');
        }
    }

    async showStatistics() {
        console.clear();
        console.log('╔════════════════════════════════════════╗');
        console.log('║            ESTADÍSTICAS               ║');
        console.log('╚════════════════════════════════════════╝\n');

        const result = await this.controller.getStatistics();
        console.log(result.message);
    }

    async pressToContinue() {
        console.log('\n──────────────────────────────────────────');
        await inquirer.prompt([
            {
                type: 'input',
                name: 'continue',
                message: 'Presione Enter para continuar...'
            }
        ]);
    }

    async exit() {
        console.log('\n👋 Saliendo del sistema...');
        await MongoDBConnection.closeConnection();
        process.exit(0);
    }
}

export default ConsoleInterface;
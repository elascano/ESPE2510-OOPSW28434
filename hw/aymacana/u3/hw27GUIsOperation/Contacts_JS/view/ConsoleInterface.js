// view/ConsoleInterface.js
import inquirer from 'inquirer';
import ContactController from '../controller/ContactController.js';
import MongoDBConnection from '../utils/MongoDBConnection.js';

class ConsoleInterface {
    constructor() {
        this.controller = new ContactController();
    }

    async showMainMenu() {
        console.clear();
        console.log('╔════════════════════════════════════════╗');
        console.log('║    SISTEMA DE AGREGAR CONTACTOS        ║');
        console.log('╠════════════════════════════════════════╣');
        console.log('║  1. Agregar nuevo equipo               ║');
        console.log('║  2. Salir                              ║');
        console.log('╚════════════════════════════════════════╝');

        const { option } = await inquirer.prompt([
            {
                type: 'input',
                name: 'option',
                message: 'Seleccione una opción (1-2):',
                validate: input => {
                    const num = parseInt(input);
                    return (num >= 1 && num <= 2) || 'Por favor ingrese un número entre 1 y 2';
                }
            }
        ]);

        await this.handleOption(parseInt(option));
    }

    async handleOption(option) {
    switch (option) {
        case 1:
            await this.addTeam();
            // Después de agregar, preguntar si quiere continuar
            await this.pressToContinue();
            await this.showMainMenu(); // Volver al menú
            break;
        case 2:
            await this.exit();
            break;
        default:
            console.log('Opción no válida');
            await this.pressToContinue();
            await this.showMainMenu();
            break;
    }
}

    async addTeam() {
        console.clear();
        console.log('╔════════════════════════════════════════╗');
        console.log('║         AGREGAR NUEVO CONTACTO         ║');
        console.log('╚════════════════════════════════════════╝\n');

        const answers = await inquirer.prompt([
            {
                type: 'input',
                name: 'name',
                message: 'Nombre del contacto:',
                validate: input => input.trim() ? true : 'El nombre es requerido'
            },
            {
                type: 'input',
                name: 'phone',
                message: 'Numero de telefono:',
                validate: input => input.trim() ? true : 'El numero de telefono es requerido'
            },
            {
                type: 'input',
                name: 'email',
                message: 'Email:',
                validate: input => input.trim() ? true : 'Email es requerido'
            },
            {
                type: 'input',
                name: 'addres',
                message: 'Direccion:',
                validate: input => {
                    const num = parseInt(input);
                    return (num >= 4 && num <= 26) || 'Addres es requerido';
                }
            }
        ]);

        const result = await this.controller.addTeam(
            answers.name,
            answers.phone,
            answers.email,
            parseInt(answers.addres)
        );

        console.log('\n' + result.message);
        if (result.success && result.data) {
            console.log(result.data.displayDetails());
        }
    }

async pressToContinue() {
    console.log('\n');
    await inquirer.prompt([
        {
            type: 'input',
            name: 'continue',
            message: 'Presiona Enter para continuar...'
        }
    ]);
}

    async exit() {
        console.log('\nSaliendo del sistema...');
        await MongoDBConnection.closeConnection();
        process.exit(0);
    }
}

export default ConsoleInterface;
const readline = require('readline');
const mongoose = require('mongoose');
const StudentController = require('./ec/edu/espe/schoolsystem/controller/StudentController');
const consoleTable = require('console.table');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

const controller = new StudentController();
const args = process.argv.slice(2);
let commands = null;
const commandsArg = args.find(a => a.startsWith('--commands'));
if (commandsArg) {
    const val = commandsArg.includes('=') ? commandsArg.split('=')[1] : null;
    if (val) commands = val.split(',').map(s => s.trim());
}
const scriptArg = args.find(a => a.startsWith('--script'));
if (scriptArg && !commands) {
    const val = scriptArg.includes('=') ? scriptArg.split('=')[1] : null;
    if (val) {
        const fs = require('fs');
        try {
            const content = fs.readFileSync(val, 'utf8');
            commands = content.split(/\r?\n/).map(s => s.trim()).filter(Boolean);
        } catch (err) {
            console.error('Error reading script file:', err.message);
        }
    }
}
let cmdIndex = 0;

function showMenu() {
    console.log('\n===== SCHOOL SYSTEM MENU =====');
    console.log('1. Register student');
    console.log('2. List students');
    console.log('3. Delete student by ID');
    console.log('4. Show total tuition');
    console.log('0. Exit');
}


function questionAsync(query) {
    
    if (commands && cmdIndex < commands.length) {
        const response = commands[cmdIndex++];
        
        console.log(response);
        return Promise.resolve(response);
    }
    return new Promise((resolve) => rl.question(query, resolve));
}

async function main() {
    try {
        await controller.connectDB();

        let option = '';
        do {
            showMenu();
            option = await questionAsync('Select an option: ');

            switch (option) {
                case '1': {
                    const name = await questionAsync('Enter name: ');
                    const grade = await questionAsync('Enter grade: ');
                    const tuitionStr = await questionAsync('Enter tuition: ');
                    const tuition = parseFloat(tuitionStr);
                    await controller.createStudent(name, grade, tuition);
                    break;
                }
                case '2': {
                    let students = await controller.getAllStudents();
                    // Ensure plain JSON-serializable objects (strip prototypes/internal props)
                    students = students.map(s => JSON.parse(JSON.stringify(s)));
                    console.table(students, ['id', 'name', 'grade', 'tuition']);
                    break;
                }
                case '3': {
                    const idStr = await questionAsync('Enter ID to delete: ');
                    const id = parseInt(idStr);
                    await controller.deleteStudent(id);
                    break;
                }
                case '4': {
                    const total = await controller.calculateTotalIncome();
                    console.log(`Total tuition: $${total}`);
                    break;
                }
                case '0':
                    console.log('Exiting...');
                    break;
                default:
                    console.log('Invalid option');
            }
        } while (option !== '0');
    } catch (err) {
        console.error('Fatal error in main():', err);
    } finally {
        rl.close();
        try { await controller.disconnect(); } catch (e) { mongoose.connection.close(); }
    }
}

main();

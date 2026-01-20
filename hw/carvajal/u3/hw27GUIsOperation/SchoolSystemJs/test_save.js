const StudentController = require('./ec/edu/espe/schoolsystem/controller/StudentController');

async function run() {
    const controller = new StudentController();
    try {
        await controller.connectDB();
        const id = await controller.createStudent('TestUser', 'A', 123.45);
        console.log('Created student with id', id);
    } catch (err) {
        console.error('Test save failed:', err);
    } finally {
        await controller.disconnect();
    }
}

run();

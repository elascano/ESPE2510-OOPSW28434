const StudentController = require('./ec/edu/espe/schoolsystem/controller/StudentController');

(async () => {
  const controller = new StudentController();
  try {
    await controller.connectDB();
    let students = await controller.getAllStudents();
    students = students.map(s => (s.toObject ? s.toObject() : s));
    console.table(students, ['id', 'name', 'grade', 'tuition']);
  } catch (err) {
    console.error('Error listing students:', err);
  } finally {
    await controller.disconnect();
  }
})();

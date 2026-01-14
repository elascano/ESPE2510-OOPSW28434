// ec/edu/espe/schoolsystem/controller/StudentController.js

const mongoose = require('mongoose');
const Student = require('../model/Student');

// Schema for MongoDB
const studentSchema = new mongoose.Schema({
    id: Number,
    name: String,
    grade: String,
    tuition: Number
});

// Model: explicitly use "students" collection
const StudentModel = mongoose.model('Student', studentSchema, 'students');

class StudentController {

    // Connect to MongoDB Atlas
    async connectDB() {
        try {
            mongoose.set('strictQuery', false);
            await mongoose.connect('mongodb+srv://Gabriel:Gabriel2007@cluster0.dgdm9az.mongodb.net/');
            console.log('Connected to MongoDB Atlas (database: school)');
            mongoose.connection.on('error', (err) => console.error('Mongoose connection error:', err));
            mongoose.connection.on('disconnected', () => console.log('Mongoose disconnected'));
        } catch (err) {
            console.error('Error connecting to MongoDB:', err);
        }
    }

    // Generate next consecutive ID
    async getNextId() {
        const lastStudent = await StudentModel.findOne().sort({ id: -1 });
        return lastStudent ? lastStudent.id + 1 : 1;
    }

    // Create a new student
    async createStudent(name, grade, tuition) {
        try {
            const nextId = await this.getNextId();
            const student = new Student(nextId, name, grade, tuition);
            const mongoStudent = new StudentModel(student);
            await mongoStudent.save();
            console.log(`Student created with ID ${nextId}`);
            return nextId;
        } catch (err) {
            console.error('Error creating student:', err);
            throw err;
        }
    }

    // Get all students
    async getAllStudents() {
        // Use .lean() to return plain JS objects (not Mongoose Documents)
        // so that console.table shows the actual fields instead of internal
        // Mongoose properties like $__ / $isNew / _doc.
        return await StudentModel.find({}, { _id: 0, __v: 0 }).lean();
    }

    // Delete student by ID
    async deleteStudent(id) {
        const result = await StudentModel.deleteOne({ id: id });
        if (result.deletedCount === 0) {
            console.log(`No student found with ID ${id}`);
        } else {
            console.log(`Student with ID ${id} deleted`);
        }
    }

    // Calculate total tuition
    async calculateTotalIncome() {
        const students = await this.getAllStudents();
        return students.reduce((acc, s) => acc + (s.tuition || 0), 0);
    }

    async disconnect() {
        try {
            await mongoose.connection.close();
            console.log('Disconnected from MongoDB');
        } catch (err) {
            console.error('Error disconnecting from MongoDB:', err);
        }
    }
}

module.exports = StudentController;

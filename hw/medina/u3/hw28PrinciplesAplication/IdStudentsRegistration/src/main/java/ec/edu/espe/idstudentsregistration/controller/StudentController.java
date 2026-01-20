package ec.edu.espe.idstudentsregistration.controller;

import ec.edu.espe.idstudentsregistration.model.Student;
import ec.edu.espe.idstudentsregistration.model.StudentRepository;

import java.util.List;

/**
 * Controller: recibe datos de la View y llama al Repository.
 * Aquí va la "lógica mínima": validaciones simples y decisiones.
 */
public class StudentController {

    private final StudentRepository repository;

    public StudentController() {
        this.repository = new StudentRepository();
    }

    // ====== C ======
    public boolean createStudent(Student s) {
        // CAMBIA AQUÍ: validaciones según tu examen (ej: no vacío, rango)
        if (s.getId() == null || s.getId().isBlank()) return false;
        if (s.getName() == null || s.getName().isBlank()) return false;
        if (s.getAge() < 0) return false;

        return repository.create(s);
    }

    // ====== R ======
    public Student readStudentById(String id) {
        if (id == null || id.isBlank()) return null;
        return repository.readById(id);
    }

    public List<Student> readAllStudents() {
        return repository.readAll();
    }

    // ====== U ======
    public boolean updateStudent(Student s) {
        if (s.getId() == null || s.getId().isBlank()) return false;
        return repository.update(s);
    }

    // ====== D ======
    public boolean deleteStudent(String id) {
        if (id == null || id.isBlank()) return false;
        return repository.delete(id);
    }
}

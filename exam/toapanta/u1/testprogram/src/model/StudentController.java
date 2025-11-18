
package model;

import model.Student;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
/**
 *
 * @author Toapanta Adrian
 */



public class StudentController {


    private final ArrayList<Student> studentList = new ArrayList<>();

    private int nextId = 101; 

    /**
     * Agrega un nuevo estudiante a la lista (Rubro 4: Data input).
     */
    public void addStudent(String name, String major, double gpa) {
        Student newStudent = new Student(nextId++, name, major, gpa);
        studentList.add(newStudent);
        System.out.println("-> Student ID " + newStudent.getId() + " added.");
    }
    
    public ArrayList<Student> getStudentList() {
        return studentList;
    }


    public String listToJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(studentList);
    }

    public ArrayList<Student> jsonToList(String jsonString) {
        Gson gson = new Gson();

        java.lang.reflect.Type studentListType = 
            new com.google.gson.reflect.TypeToken<ArrayList<Student>>() {}.getType();
        return gson.fromJson(jsonString, studentListType);
    }
}
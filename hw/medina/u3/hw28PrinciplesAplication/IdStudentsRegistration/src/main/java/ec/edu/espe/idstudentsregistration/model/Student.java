package ec.edu.espe.idstudentsregistration.model;

/**
 * ENTIDAD (POJO)
 * CAMBIA AQUÍ si en el examen te inventan otros atributos
 * Ejemplo: Product { id, name, price } etc.
 */
public class Student {
    // CAMBIA AQUÍ: define el "identificador" que usarás para buscar/actualizar/borrar.
    // En Mongo puedes usar _id, pero para examen es fácil usar "id" int o String.
    private String id;

    // CAMBIA AQUÍ: atributos inventados
    private String name;
    private String email;
    private int age;

    public Student() {}

    public Student(String id, String name, String email, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}

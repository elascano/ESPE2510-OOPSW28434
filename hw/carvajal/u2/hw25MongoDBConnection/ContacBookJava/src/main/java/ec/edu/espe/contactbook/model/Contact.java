package ec.edu.espe.contactbook.model;

/**
 *
 * @author Josue Carvajal, THE ART OF PROGRAMMING, @ESPE
 */

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Contact con birthDate persistido (ISO yyyy-MM-dd). getAge() lo calcula dinámicamente.
 */
public class Contact {

    private int id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;          // persistimos como ISO string en Mongo
    private String typeOfContact;         // family, friend, job, unknown
    private String sex;                   // male, female
    private List<String> hobbies;
    private String comments;

    public Contact() {
        this.hobbies = new ArrayList<>();
    }

    public Contact(int id, String firstName, String lastName, LocalDate birthDate,
                   String typeOfContact, String sex, List<String> hobbies, String comments) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
        setTypeOfContact(typeOfContact);
        setSex(sex);
        setHobbies(hobbies);
        setComments(comments);
    }

 
    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("El ID debe ser positivo.");
        this.id = id;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty())
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        this.firstName = firstName.trim();
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty())
            throw new IllegalArgumentException("El apellido no puede estar vacío.");
        this.lastName = lastName.trim();
    }

    public void setBirthDate(LocalDate birthDate) {
        if (birthDate == null) throw new IllegalArgumentException("birthDate es requerido.");
        if (birthDate.isAfter(LocalDate.now())) throw new IllegalArgumentException("birthDate no puede ser en el futuro.");
        this.birthDate = birthDate;
    }

    public void setTypeOfContact(String typeOfContact) {
        if (typeOfContact == null) throw new IllegalArgumentException("Tipo de contacto requerido.");
        String low = typeOfContact.trim().toLowerCase();
        if (!low.equals("family") && !low.equals("friend") && !low.equals("job") && !low.equals("unknown"))
            throw new IllegalArgumentException("Tipo de contacto inválido.");
        this.typeOfContact = low;
    }

    public void setSex(String sex) {
        if (sex == null) throw new IllegalArgumentException("Sexo requerido.");
        String low = sex.trim().toLowerCase();
        if (!low.equals("male") && !low.equals("female")) throw new IllegalArgumentException("Sexo inválido.");
        this.sex = low;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = (hobbies == null) ? new ArrayList<>() : hobbies;
    }

    public void setComments(String comments) {
        this.comments = (comments == null) ? "" : comments.trim();
    }

   
    public int getId() {
        return id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public LocalDate getBirthDate() {
        return birthDate;
    }
    
    public String getTypeOfContact() { 
        return typeOfContact;
    }
    
    public String getSex() {
        return sex; 
    }
    
    public List<String> getHobbies() { 
        return hobbies; 
    }
    
    public String getComments() {
        return comments;
    }


    public int getAge() {
        if (birthDate == null) return 0;
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", age=" + getAge() +
                ", typeOfContact='" + typeOfContact + '\'' +
                ", sex='" + sex + '\'' +
                ", hobbies=" + hobbies +
                ", comments='" + comments + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;
        return id == contact.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

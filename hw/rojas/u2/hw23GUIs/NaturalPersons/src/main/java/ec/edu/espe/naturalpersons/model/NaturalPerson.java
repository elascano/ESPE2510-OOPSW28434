package ec.edu.espe.naturalpersons.model;

import java.util.Date;

/**
 *
 * @author JOSUE
 */
public class NaturalPerson {
    private String identification;
    private String nationality;
    private Date birthDate;
    private String occupation;
    private String gender;

    public NaturalPerson(String identification, String nationality, Date birthDate, String occupation, String gender) {
        this.identification = identification;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.occupation = occupation;
        this.gender = gender;
    }

    
    
    /**
     * @return the identification
     */
    public String getIdentification() {
        return identification;
    }

    /**
     * @param identification the identification to set
     */
    public void setIdentification(String identification) {
        this.identification = identification;
    }

    /**
     * @return the nationality
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * @param nationality the nationality to set
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * @return the birthDate
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate the birthDate to set
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * @return the occupation
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * @param occupation the occupation to set
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    
}

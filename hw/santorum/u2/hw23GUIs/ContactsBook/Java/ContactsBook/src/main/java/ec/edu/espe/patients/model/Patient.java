package ec.edu.espe.patients.model;

import java.awt.List;
import java.util.ArrayList;

/**
 *
 * @author Thsi Santórum, Paradigm, @ESPE
 */
public class Patient {
    private int patientId;
    private String fullName;
    private String gender;
    private String phone;
    private String address;

    public Patient(int patientId, String fullName, String gender, String phone, String address) {
        this.patientId = patientId;
        this.fullName = fullName;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Patient{" + "patientId=" + patientId + ", fullName=" + fullName + ", gender=" + gender + ", phone=" + phone + ", address=" + address + '}';
    }

    
    
    
    
    
    
    
    /**
     * @return the patientId
     */
    public int getPatientId() {
        return patientId;
    }

    /**
     * @param patientId the patientId to set
     */
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
        
    }


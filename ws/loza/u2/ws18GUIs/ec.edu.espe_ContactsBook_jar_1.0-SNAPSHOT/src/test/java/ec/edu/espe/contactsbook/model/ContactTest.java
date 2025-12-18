/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package ec.edu.espe.contactsbook.model;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Steven Loza @ESPE
 */
public class ContactTest {
    
    public ContactTest() {
    }

    @org.junit.Test
    public void testGetId() {
        System.out.println("getId");
        Contact instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        Contact instance = null;
        instance.setId(id);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        Contact instance = null;
        String expResult = "";
        String result = instance.getFirstName();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testSetFirstName() {
        System.out.println("setFirstName");
        String firstName = "";
        Contact instance = null;
        instance.setFirstName(firstName);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testGetLastName() {
        System.out.println("getLastName");
        Contact instance = null;
        String expResult = "";
        String result = instance.getLastName();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testSetLastName() {
        System.out.println("setLastName");
        String lastName = "";
        Contact instance = null;
        instance.setLastName(lastName);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testGetAge() {
        System.out.println("getAge");
        Contact instance = null;
        int expResult = 0;
        int result = instance.getAge();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testSetAge() {
        System.out.println("setAge");
        int age = 0;
        Contact instance = null;
        instance.setAge(age);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testGetTypeOfContact() {
        System.out.println("getTypeOfContact");
        Contact instance = null;
        String expResult = "";
        String result = instance.getTypeOfContact();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testSetTypeOfContact() {
        System.out.println("setTypeOfContact");
        String typeOfContact = "";
        Contact instance = null;
        instance.setTypeOfContact(typeOfContact);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testGetSex() {
        System.out.println("getSex");
        Contact instance = null;
        String expResult = "";
        String result = instance.getSex();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testSetSex() {
        System.out.println("setSex");
        String sex = "";
        Contact instance = null;
        instance.setSex(sex);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testGetHobbies() {
        System.out.println("getHobbies");
        Contact instance = null;
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.getHobbies();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testSetHobbies() {
        System.out.println("setHobbies");
        ArrayList<String> hobbies = null;
        Contact instance = null;
        instance.setHobbies(hobbies);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testGetComments() {
        System.out.println("getComments");
        Contact instance = null;
        String expResult = "";
        String result = instance.getComments();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testSetComments() {
        System.out.println("setComments");
        String comments = "";
        Contact instance = null;
        instance.setComments(comments);
        fail("The test case is a prototype.");
    }
    
}

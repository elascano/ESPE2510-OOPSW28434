/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Mateo Cevallos Object Masters
 */
public class basicOperationTest {
    
    public basicOperationTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of add method, of class basicOperation.
     */
    @Test
    public void testAddNegatives() {
        System.out.println("add");
        float addend1 = -1.0F;
        float addend2 = -2.0F;
        basicOperation instance = new basicOperation();
        float expResult = -3.0F;
        float result = instance.add(addend1, addend2);
        assertEquals(expResult, result, 0);
    }
    
    @Test
    public void testAdd() {
        System.out.println("add");
        float addend1 = 1.2F;
        float addend2 = 2.4F;
        basicOperation instance = new basicOperation();
        float expResult = 3.6F;
        float result = instance.add(addend1, addend2);
        assertEquals(expResult, result, 0);
    }
}

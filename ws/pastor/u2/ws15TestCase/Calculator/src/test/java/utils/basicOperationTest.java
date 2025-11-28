package utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Mathews Pastor, POOwer Ranger of Programing, @ESPE
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
    public void testAdd() {
        System.out.println("add");
        float addend1 = 1.2F;
        float addend2 = 2.4F;
        basicOperation instance = new basicOperation();
        float expResult = 3.6F;
        float result = instance.add(addend1, addend2);
        assertEquals(expResult, result, 0);
    }
    @Test
    public void testAddNegative() {
        System.out.println("add");
        float addend1 = -1.0F;
        float addend2 = -2.0F;
        basicOperation instance = new basicOperation();
        float expResult = -3.0F;
        float result = instance.add(addend1, addend2);
        assertEquals(expResult, result, 0);
    }
    
}

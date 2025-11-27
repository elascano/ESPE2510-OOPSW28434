package Test;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
 import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.basicOperation;
import utils.basicOperation;


/**
 *
 * @author Adrian Toapanta, Student OOP, @ESPE
 */
public class basicOperationTest {
    
    public basicOperationTest() {
    }
    


    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    @Test
    public void testAdd() {
        System.out.println("add");
        float addend1 = 1.2F;
        float addend2 = 2.4F;
        basicOperation instance = new basicOperation();
        float expResult = 3.6F;
        float result = instance.add(addend1, addend2);
        assertEquals(expResult, result, 2);
    }

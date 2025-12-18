package Utils;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Mikael Hidalgo
 */
public class basicOperationTest {
    
    public basicOperationTest() {
    }

    @BeforeAll
    public static void setUpClass() throws Exception {
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
        float addend1 = 1.2F; //round
        float addend2 = 2.4F; //round
        basicOperation instance = new basicOperation();
        float expResult = 3.6F; //round
        float result = instance.add(addend1, addend2);
        assertEquals(expResult, result, 0);
    }
    
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
}


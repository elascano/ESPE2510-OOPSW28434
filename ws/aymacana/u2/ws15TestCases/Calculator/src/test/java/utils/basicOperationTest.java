package utils;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
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

    /**
     * Test of add method, of class basicOperation.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        float addend1 = 1.2F;
        float addent2 = 2.4F;
        basicOperation instance = new basicOperation();
        float expResult = 3.6F;
        float result = instance.add(addend1, addent2);
        assertEquals(expResult, result, 0);

    }

    @Test
    public void testAddNegatives() {
        System.out.println("add");
        float addend1 = -1.0F;
        float addent2 = -2.0F;
        basicOperation instance = new basicOperation();
        float expResult = -3.0F;
        float result = instance.add(addend1, addent2);
        assertEquals(expResult, result, 0);

    }

}

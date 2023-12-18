package logic;


import org.junit.Test;

import static logic.CalculationType.*;
import static org.junit.Assert.*;

/**
 * Tests published with the assignments. Just examples for how the methods can be called, doesn't even get close to
 * properly testing the functionality of the class.
 *
 * @author cei
 */
public class PubCalculationTypeTest {

    @Test
    public void testValidOperandsN_AdditionValid() {
        assertTrue(ADDITION.validOperandsN(1, 2));
        assertTrue(ADDITION.validOperandsN(2, 1));
    }

    @Test
    public void testCalculate_Addition() {
        assertEquals(8, ADDITION.calculate(5, 3));
    }
}

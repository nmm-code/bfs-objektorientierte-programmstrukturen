package ueb;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Mit der Aufgabe 4 herausgegebene Testklasse für Methoden der Klasse {@code Element}.
 * PubTests müssen zu Beginn der Abgabe erfolgreich sein, sonst kann kein Testat für diese Aufgabe erworben werden.
 *
 * @author klk
 */
public class PubElement4Test {

    @Test
    public void testIsPredecessor_Is() {
        Element el = new Element('a');
        assertTrue(el.isPredecessor('b'));
    }

    @Test
    public void testIsEqual() {
        Element el = new Element('a');
        Element elOther = new Element('a');
        assertTrue(el.isEqual(elOther));
    }


}

package ueb;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Tests für die Klasse Element und isEqual() der Aufgabe 4.
 *
 * @author Max,Nima
 */

public class ReqSetTest {

    /**
     * Erzeugt eine Menge mit den Werten.
     *
     * @param values Wert(e) des Elements/der Elemente
     * @return Menge mit den Werten enthalten
     *
     * @author cei, klk
     */
    private Set createSet(char... values) {
        Set set = new Set();
        //  Elemente zufügen
        for (char value : values) {
            set.addElement(value);
        }
        return set;
    }

    @Test
    public void testIsEqual_EqualSets_withNull() {
        Set set1 = new Set();
        assertFalse(set1.isEqual(null));
    }

    @Test
    public void testIsEqual_EqualSets_EmptySet() {
        Set set1 = new Set();
        Set set2 = new Set();
        assertTrue(set1.isEqual(set2));
    }

    //TODO 2 done schreiben Sie folgende Tests in ReqSetTest und kopieren Sie die zugehörigen TODOs hinein
    //TODO 2 done schreiben Sie einen Test {"erni"}.diff({"bob"}) sollte {"erni"} herauskommen

    @Test
    public void test_diff_differentSet() {
        Set set1 = new Set("erni");
        Set set2 = new Set("bob");
        Set set3 = set1.diff(set2);
        assertTrue(set1.isEqual(set3));
    }

    //TODO 2 done schreiben Sie einen Test {"erni"}.diff({"bert"}) sollte {"ni"} herauskommen
    @Test
    public void test_diff_subSet() {
        Set set1 = new Set("erni");
        Set set2 = new Set("bert");
        Set set3 = set1.diff(set2);
        Set set4 = new Set("ni");
        assertTrue(set4.isEqual(set3));
    }

    //TODO 2 done schreiben Sie einen Test {"er"}.diff({"erni"}) sollte {} herauskommen
    @Test
    public void test_diff_subSet_twoChar_first() {
        Set set1 = new Set("er");
        Set set2 = new Set("erni");
        Set set3 = set1.diff(set2);
        Set set4 = new Set();
        assertTrue(set4.isEqual(set3));
    }

    //TODO 2 done schreiben Sie einen Test {}.diff({"erni"}) sollte {} herauskommen
    @Test
    public void test_diff_empty() {
        Set set1 = new Set();
        Set set2 = new Set("erni");
        Set set3 = set1.diff(set2);
        Set set4 = new Set();
        assertTrue(set4.isEqual(set3));
    }


}

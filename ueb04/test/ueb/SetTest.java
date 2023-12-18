package ueb;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Nima,Max
 */
public class SetTest {

    @Test
    public void testDisjunctiveSets() {
        Set set1 = new Set('a', 'b', 'c');
        Set set2 = new Set('1', '2', '3');
        char[] expected = new char[]{};
        set1 = set1.intersection(set2);
        assertArrayEquals("expected " + Arrays.toString(expected) + " but got " + set1.toString(), expected, set1.toArray());
    }

    @Test
    public void test_union() {
        Set set1 = new Set('a', 'b', 'c');
        Set set2 = new Set('1', '2', '3');
        Set set3 = new Set('a', 'b', 'c', '1', '2', '3');
        set1 = set1.union(set2);
        assertTrue(set1.isEqual(set3));
    }

    @Test
    public void test_union_Empty_first() {
        Set set1 = new Set();
        Set set2 = new Set('1', '2', '3');
        Set set3 = new Set('1', '2', '3');
        set1 = set1.union(set2);
        assertTrue(set1.isEqual(set3));
    }

    @Test
    public void test_union_Empty_second() {
        Set set1 = new Set('1', '2', '3');
        Set set2 = new Set();
        Set set3 = new Set('1', '2', '3');
        set1 = set1.union(set2);
        assertTrue(set1.isEqual(set3));
    }

    @Test
    public void test_union_SetEquals() {
        Set set1 = new Set('a', 'b', 'c');
        Set set2 = new Set('c', 'b', 'a');
        Set set3 = new Set('a', 'b', 'c');
        set1 = set1.union(set2);
        assertTrue(set1.isEqual(set3));
    }

    @Test
    public void test_intersection() {
        Set set1 = new Set('a', 'b', 'c');
        Set set2 = new Set('a', '2', '3');
        Set set3 = new Set('a');
        set1 = set1.intersection(set2);
        assertTrue(set1.isEqual(set3));
    }

    @Test
    public void test_intersection_SetEquals() {
        Set set1 = new Set('a', 'b', 'c');
        Set set2 = new Set('c', 'b', 'a');
        Set set3 = new Set('a', 'b', 'c');
        set1 = set1.intersection(set2);
        assertTrue(set1.isEqual(set3));
    }

    @Test
    public void test_intersection_Empty_first() {
        Set set1 = new Set();
        Set set2 = new Set('1', '2', '3');
        Set set3 = new Set();
        set1 = set1.intersection(set2);
        assertTrue(set1.isEqual(set3));
    }

    @Test
    public void test_intersection_Empty_second() {
        Set set1 = new Set('1', '2', '3');
        Set set2 = new Set();
        Set set3 = new Set();
        set1 = set1.intersection(set2);
        assertTrue(set1.isEqual(set3));
    }

    @Test
    public void test_diff() {
        Set set1 = new Set('a', 'b', 'c');
        Set set2 = new Set('1', '2', '3');
        Set set3 = new Set('a', 'b', 'c');
        set1 = set1.diff(set2);
        assertTrue(set1.isEqual(set3));
    }

    @Test
    public void test_diff_Empty_first() {
        Set set1 = new Set();
        Set set2 = new Set('1', '2', '3');
        Set set3 = new Set();
        set1 = set1.diff(set2);
        assertTrue(set1.isEqual(set3));
    }

    @Test
    public void test_diff_Empty_second() {
        Set set1 = new Set('1', '2', '3');
        Set set2 = new Set();
        Set set3 = new Set('1', '2', '3');
        set1 = set1.diff(set2);
        assertTrue(set1.isEqual(set3));
    }

    @Test
    public void test_diff_SetEquals() {
        Set set1 = new Set('a', 'b', 'c');
        Set set2 = new Set('b', 'a');
        Set set3 = new Set('c');
        set1 = set1.diff(set2);
        assertTrue(set1.isEqual(set3));
    }

    @Test
    public void test_symDiff() {
        Set set1 = new Set('a', 'b', 'c');
        Set set2 = new Set('1', '2', '3');
        Set set3 = new Set('a', 'b', 'c','1','2','3');
        set1 = set1.symDiff(set2);
        assertTrue(set1.isEqual(set3));
    }

    @Test
    public void test_symDiff_Empty_first() {
        Set set1 = new Set();
        Set set2 = new Set('1', '2', '3');
        Set set3 = new Set('1', '2', '3');
        set1 = set1.symDiff(set2);
        assertTrue(set1.isEqual(set3));
    }

    @Test
    public void test_symDiff_Empty_second() {
        Set set1 = new Set('1', '2', '3');
        Set set2 = new Set();
        Set set3 = new Set('1', '2', '3');
        set1 = set1.symDiff(set2);
        assertTrue(set1.isEqual(set3));
    }

    @Test
    public void test_symDiff_SetEquals() {
        Set set1 = new Set('a', 'b', 'c');
        Set set2 = new Set('b', 'a');
        Set set3 = new Set('c');
        set1 = set1.symDiff(set2);
        assertTrue(set1.isEqual(set3));
    }
    @Test
    public void test_ProperSubSet() {
        Set set1 = new Set('a', 'b', 'c');
        Set set2 = new Set('b', 'a');
        assertTrue(set2.isProperSubSet(set1));
    }
}

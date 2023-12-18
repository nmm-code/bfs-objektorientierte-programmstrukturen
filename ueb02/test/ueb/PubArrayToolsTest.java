package ueb;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author marcus, klk
 */
public class PubArrayToolsTest {

    @Test
    public void containsAt_First() {
        final int[] original = {0, 0, -1, -1, 1, 1};

        assertEquals(0, ArrayTools.containsAt(original, 0));
    }

    @Test
    public void containsAt_Middle() {
        final int[] original = {0, 0, -1, -1, 1, 1};

        assertEquals(2, ArrayTools.containsAt(original, -1));
    }

    @Test
    public void containsAt_Last() {
        final int[] original = {0, 0, -1, -1, 1, 1};

        assertEquals(4, ArrayTools.containsAt(original, 1));
    }

    @Test
    public void containsAt_Empty() {
        final int[] original = {};

        assertEquals(-1, ArrayTools.containsAt(original, 2));
        assertEquals(-1, ArrayTools.containsAt(original, -2));
    }

    @Test
    public void containsAt_MissingValue() {
        final int[] original = {0, 0, -1, -1, 1, 1};

        assertEquals(-1, ArrayTools.containsAt(original, 2));
        assertEquals(-1, ArrayTools.containsAt(original, -2));
    }

    @Test (expected = IllegalArgumentException.class)
    public void containsAt_Null() {
        int unused = ArrayTools.containsAt(null, 2);
    }

    //----------------------------------------------------------

    @Test
    public void deleteElementAt_Valid_First() {
        final int[] original = {0, 1, 2, 3, 4};
        assertArrayEquals(new int[]{1, 2, 3, 4}, ArrayTools.deleteElementAt(original, 0));
    }

    @Test
    public void deleteElementAt_Valid_InMiddle() {
        final int[] original = {0, 1, 2, 3, 4};
        assertArrayEquals(new int[]{0, 2, 3, 4}, ArrayTools.deleteElementAt(original, 1));
        assertArrayEquals(new int[]{0, 1, 3, 4}, ArrayTools.deleteElementAt(original, 2));
        assertArrayEquals(new int[]{0, 1, 2, 4}, ArrayTools.deleteElementAt(original, 3));
    }
    @Test
    public void deleteElementAt_Valid_Last() {
        final int[] original = {0, 1, 2, 3, 4};
        assertArrayEquals(new int[]{0, 1, 2, 3}, ArrayTools.deleteElementAt(original, 4));
    }

    //----------------------------------------------------------

    @Test
    public void insertElementAt_Valid_First() {
        final int[] original = {1, 2, 3, 4};
        assertArrayEquals(new int[]{0, 1, 2, 3, 4}, ArrayTools.insertElementAt(original, 0, 0));
    }

    @Test
    public void insertElementAt_Valid_InMiddle() {
        final int[] original = {1, 2, 3, 4};
        assertArrayEquals(new int[]{1, 0, 2, 3, 4}, ArrayTools.insertElementAt(original, 1, 0));
        assertArrayEquals(new int[]{1, 2, 0, 3, 4}, ArrayTools.insertElementAt(original, 2, 0));
        assertArrayEquals(new int[]{1, 2, 3, 0, 4}, ArrayTools.insertElementAt(original, 3, 0));
    }
    @Test
    public void insertElementAt_Valid_AtLast() {
        final int[] original = {1, 2, 3, 4};
        assertArrayEquals(new int[]{1, 2, 3, 4, 0}, ArrayTools.insertElementAt(original, 4, 0));
    }

    //----------------------------------------------------------

    @Test
    public void insertElementAt_EmptyArray_ValidIdx() {
        final int[] original = {};
        assertArrayEquals(new int[]{0}, ArrayTools.insertElementAt(original, 0, 0));
    }

    //----------------------------------------------------------

    @Test
    public void getLengthOfLongestArray_ContainsNull() {
        assertEquals("{ null } => -1", -1, ArrayTools.getLengthOfLongestArray(new int[][]{null}));
        assertEquals("{ null, null } => -1", -1, ArrayTools.getLengthOfLongestArray(new int[][]{null, null}));
    }

    @Test
    public void getLengthOfLongestArray_ContainsEmptyArray() {
        assertEquals("{ {} } => 0", 0, ArrayTools.getLengthOfLongestArray(new int[][]{{}}));
        assertEquals("{ null, {}, null } => 0", 0, ArrayTools.getLengthOfLongestArray(new int[][]{null, {}, null}));
    }

    @Test
    public void getLengthOfLongestArray_filledArrays() {
        assertEquals("{ { }, { 1 } } => 1", 1, ArrayTools.getLengthOfLongestArray(new int[][]{{}, {1}}));
        assertEquals("{ { }, { 1 } } => 1", 1, ArrayTools.getLengthOfLongestArray(new int[][]{{1}, {}}));
        assertEquals("{ { 1, 1 }, { } } => 2", 2, ArrayTools.getLengthOfLongestArray(new int[][]{{1, 1}, {}}));
        assertEquals("{ { }, { 1, 1 } } => 2", 2, ArrayTools.getLengthOfLongestArray(new int[][]{{}, {1, 1}}));
    }

}

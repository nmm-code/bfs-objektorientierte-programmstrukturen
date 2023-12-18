package ueb;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marcus, klk
 */
public class PubDataTest {
    
    @Test
    public void test0_getMap_createsADeepCopy() {
        int[][][] fstCall = Data.getMap();
        int[][][] sndCall = Data.getMap();
        
        assertNotSame("Root: Every call must create a new instance", fstCall, sndCall);
        
        for (int i = 0; i < fstCall.length; ++i) {
            assertNotSame("First level: Every call must create a new instance", fstCall[i], sndCall[i]);
            for (int j = 0; j < fstCall[i].length; ++j) {
                assertNotSame("Second level: Every call must create a new instance", fstCall[i][j], sndCall[i][j]);
            }
        }
    }
    
    @Test
    public void test0_getOrderSeries_createsADeepCopy() {
        int[][] fstCall = Data.getOrderSeries(0);
        int[][] sndCall = Data.getOrderSeries(0);
        
        assertNotSame("Root: Every call must create a new instance", fstCall, sndCall);
        
        for (int i = 0; i < fstCall.length; ++i) {
            assertNotSame("First level: Every call must create a new instance", fstCall[i], sndCall[i]);
        }
    }

    @Test (expected = IllegalArgumentException.class)
    public void getOrderSeries_IndexTooSmall() {
        int[][] unused = Data.getOrderSeries(-1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void getOrderSeries_IndexOneTooBig() {
        int[][] unused = Data.getOrderSeries(Data.getNoOfSeries());
    }

    @Test (expected = IllegalArgumentException.class)
    public void getOrderSeries_IndexTwoTooBig() {
        int[][] unused = Data.getOrderSeries(Data.getNoOfSeries() + 1);
    }

}

package ueb;

import org.junit.Assert;
import org.junit.Test;
import ueb.Position;
import ueb.RoofRoom;
import ueb.RoomUsage;
import ueb.Side;

import static org.junit.Assert.*;

public class PubRoofRoomTest {

    @Test
    public void constructByParam() {
        RoofRoom room = new RoofRoom(RoomUsage.STAIRWAY, new Position(0, 0), new Position(4, 4),
                Side.BOTTOM, 1);
        assertEquals(RoomUsage.STAIRWAY, room.getRoomUsage());
        assertEquals(new Position(0, 0), room.getPosTL());
        assertEquals(new Position(4, 4), room.getPosBR());
    }
    //------------------------------------------------------

    @Test
    public void constructByString() {
        RoofRoom room = new RoofRoom("sleep 0,0 4,4 BM 1");
        assertEquals(RoomUsage.SLEEP, room.getRoomUsage());
        assertEquals(new Position(0, 0), room.getPosTL());
        assertEquals(new Position(4, 4), room.getPosBR());
    }

    //------------------------------------------------------

    @Test
    public void getShortcut() {
        RoofRoom room = new RoofRoom("bath 0,0 4,4 BM 1");
        assertEquals("RR", room.getShortcut());
    }

    @Test
    public void calcLivingArea() {
        RoofRoom room = new RoofRoom("bath 20,50 65,70 LT 15");
        assertEquals(450, room.calcLivingArea());
    }

    //------------------------------------------------------

    @Test
    public void testToString() {
        RoofRoom room = new RoofRoom("bath 0,0 4,4 BM 1");
        Assert.assertEquals("RR bath 0,0 4,4 BM 1", room.toString());
    }

    //------------------------------------------------------
    /*
     assertTrue() wird im Folgenden genutzt, um deutlich zu machen, dass die Methode `equals()` getestet werden soll.
     */
    @Test
    public void testEquals_SameRoom() {
        RoofRoom roomA = new RoofRoom("bath 0,0 4,4 BM 1");
        Assert.assertTrue(roomA.toString() + " sollte sich selbst gleich sein", roomA.equals(roomA));
    }

    @Test
    public void testEquals_SameRooms() {
        RoofRoom roomA = new RoofRoom("bath 0,0 4,4 BM 1");
        RoofRoom roomB = new RoofRoom("bath 0,0 4,4 BM 1");
        Assert.assertTrue(roomA.toString() + " sollte gleich sein mit " + roomB.toString(), roomA.equals(roomB));
    }

    @Test
    public void testEquals_SameEffectiveAreaSizes_SquareToRect() {
        RoofRoom roomA = new RoofRoom("bath 20,30 40,50 LT 15");
        RoofRoom roomB = new RoofRoom("bath 20,40 30,80 RT 15");
        Assert.assertTrue(roomA.toString() + " sollte gleich sein mit " + roomB.toString(), roomA.equals(roomB));
    }

    @Test
    public void testEquals_Null() {
        RoofRoom roomA = new RoofRoom("bath 20,30 40,50 LT 15");
        Assert.assertFalse("`null` ist kein Raum", roomA.equals(null));
    }

    @Test
    public void testEquals_NoRoom() {
        RoofRoom roomA = new RoofRoom("bath 20,30 40,50 LT 15");
        Assert.assertFalse("\"roomB\" ist kein Raum", roomA.equals("roomB"));
    }

    @Test
    public void testEquals_DifferentUsage() {
        RoofRoom roomA = new RoofRoom("bath 20,30 40,50 LT 15");
        RoofRoom roomB = new RoofRoom("sleep 20,30 40,50 LT 15");
        Assert.assertFalse(roomA.toString() + " hat andere Usage als " + roomB.toString(), roomA.equals(roomB));
    }

    @Test
    public void testEquals_DifferentAreaSizes() {
        RoofRoom roomA = new RoofRoom("bath 20,30 40,50 LT 15");
        RoofRoom roomB = new RoofRoom("bath 10,30 40,50 LT 15");
        System.out.println();
        Assert.assertFalse(roomA.toString() + " hat andere Area als " + roomB.toString(), roomA.equals(roomB));
    }

    @Test
    public void testEquals_under1m() {
        RoofRoom roomA = new RoofRoom("bath 0,0 4,4 LT 4");
        assertEquals(0,roomA.calcLivingArea());
    }
    @Test
    public void testEquals_under1m_bigDis() {
        RoofRoom roomA = new RoofRoom("bath 0,0 4,4 LT 40");
        assertEquals(0,roomA.calcLivingArea());
    }
    @Test
    public void testEquals_negativeParms() {
        RoofRoom roomA = new RoofRoom("bath -30,-50 5,-30 TP 10");
        assertEquals(175,roomA.calcLivingArea());
    }
    @Test
    public void testEquals_smallerThan2m() {
        RoofRoom roomA = new RoofRoom("bath 0,0 10,20 TP 15");
        assertEquals(25,roomA.calcLivingArea());
    }


}
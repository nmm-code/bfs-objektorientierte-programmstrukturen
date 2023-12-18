package ueb;

import org.junit.Assert;
import org.junit.Test;
import ueb.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PubFlatTest {

    @Test
    public void constructEmptyFlat() {
        Flat flat = new Flat();
        assertEquals(0, flat.getCountOfRooms());
    }

    @Test
    public void constructFlatWithVarArgs() {
        Flat flat = new Flat(new Room("bath 0,0 2,2"),
                            new Room("cook 3,0 6,6"),
                            new Room("sleep 0,3 2,6")) ;
        assertEquals(3, flat.getCountOfRooms());
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructFlatWithVarArgs_Null() {
        Flat flat = new Flat(new Room("bath 0,0 2,2"),
                null,
                new Room("sleep 0,3 2,6")) ;
    }

    @Test
    public void constructFlatWithString() {
        Flat flat = new Flat("RO bath 0,0 2,2\n" +
                                    "RO cook 3,0 6,6\n" +
                                    "RO sleep 0,3 2,6") ;
        assertEquals(3, flat.getCountOfRooms());
    }

    //--------------------------------------------------------

    @Test
    public void add_ToEmpty() {
        Flat flat = new Flat();
        flat.add(new Room("live 0,0 6,6"));
        assertEquals(1, flat.getCountOfRooms());
    }
    @Test
    public void add_ToOneRoom() {
        Flat flat = new Flat(new Room("live 0,0 6,6"));
        flat.add(new Room("live 0,0 6,6"));
        assertEquals(2, flat.getCountOfRooms());
    }

    //--------------------------------------------------------

    @Test
    public void calcBaseArea() {
        Flat flat = new Flat("RO bath 0,0 2,2\n" +
                "RO cook 3,0 6,6\n" +
                "RO sleep 0,3 2,6") ;
        assertEquals(28, flat.calcBaseArea());
    }

    @Test
    public void calcEffectiveArea() {
        Flat flat = new Flat("RO bath 0,0 2,2\n" +
                "RO cook 3,0 6,6\n" +
                "RO sleep 0,3 2,6") ;
        assertEquals(28, flat.calcEffectiveArea());
    }

    @Test
    public void calcLivingArea() {
        Flat flat = new Flat("RO bath 0,0 2,2\n" +
                "RO cook 3,0 6,6\n" +
                "RO sleep 0,3 2,6") ;
        assertEquals(28, flat.calcLivingArea());
    }

    //--------------------------------------------------------

    @Test
    public void testEquals() {
        Flat flatA = new Flat("FS 2,2 4,4\n" +
                "CS climb 2,2 4,4\n" +
                "RR bath 20,50 65,70 RT 15");
        Flat flatB = new Flat("CS climb 2,2 4,4\n" +
                "FS 2,2 4,4\n" +
                "RO bath 20,50 35,80");
        assertEquals(flatA.getCountOfRooms(), flatB.getCountOfRooms());
        Assert.assertEquals(new FunctionalSpace("2,2 4,4"),       new CrawlSpace("climb 2,2 4,4"));
        Assert.assertEquals(new CrawlSpace("climb 2,2 4,4"),         new FunctionalSpace("2,2 4,4"));
        Assert.assertEquals(new RoofRoom("bath 20,50 65,70 RT 15"), new Room("bath 20,50 35,80"));
        //assertTrue() wird im Folgenden genutzt, um deutlich zu machen, dass die Methode `equals()` getestet werden soll.
        Assert.assertTrue(flatA.equals(flatB));    // assertTrue() wird hier genutzt, um deutlich zu machen, dass die Methode `equals()` getestet werden soll.
    }

    //--------------------------------------------------------

    @Test
    public void testToString() {
        //Zeilenendezeichen soll auf jeden Fall `\n` (LF) sein  und nicht `\r\n` (CRLF)
        Flat flat = new Flat("FS 2,2 4,4\n" +
                "CS store 2,2 4,4\n" +
                "RR bath 20,50 65,70 RT 10");
        String expected = "3 Räume mit Grundfläche 908, Nutzfläche 904 und Wohnfläche 600\n" +
                "FS climb 2,2 4,4\n" +
                "CS store 2,2 4,4\n" +
                "RR bath 20,50 65,70 RT 10\n" ;
        Assert.assertEquals(expected, flat.toString());
    }
}
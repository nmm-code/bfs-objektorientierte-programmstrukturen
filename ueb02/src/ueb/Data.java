package ueb;

/**
 * Enthält die zu analysierenden Daten.
 * In privaten Konstanten
 * - eine Karte mit Warenhäusern und ihren Produkten
 * - eine Liste von Bestelllisten, jede Bestellliste enthält mehrere Bestellungen,
 * jede Bestellung enthält die Angaben, welches Produkt wie oft geordert wird
 * <p>
 * In öffentlichen Konstanten ist die Reihenfolge der Dimensionen einer Bestellung abgelegt.
 * <p>
 * Über Getter werden die Merkmale der Konstanten, eine gewünschte Bestellliste
 * und auch die Kopie der Karte herausgegeben.
 *
 * @author Mo, klk, Max, Nima
 */
public class Data {

    // Sortierung einer Bestellung im Array, die Konstanten bestimmen die Dimension
    public static final int X = 0; //x-Koordinate der Lieferadresse (des Kunden)
    public static final int Y = 1; //y-Koordinate der Lieferadresse (des Kunden)
    public static final int ID = 2; //ID des Produktes in einer Bestellung
    public static final int CT = 3; //gewünschte Anzahl des Produktes in einer Bestellung
    /**
     * Die Simulation erfolgt auf einer zweidimensionalen Karte mit 10 Spalten und 7 Reihen.
     * Sie zeigt hier den initialen Zustand an. Eine Position kann leer sein
     * (leeres Array enthalten) oder ein Lager enthalten, dann ist dort eine
     * Angabe von Anzahlen von gelagerten Produkten. Ein Warenhaus muss nicht
     * jedes Produkt verfügbar haben.
     */
//    Karte, wie sie auf System.out ausgeben wird:
//    private static final int[][][] MAP_ROTATED = {
//        {{1,1,3,3,4,4,4,4}, {}, {}, {}, {} , {}, {}   , {}, {}, {}},
//        {{}               , {}, {}, {}, {} , {}, {}   , {}, {}, {}},
//        {{}               , {}, {}, {}, {} , {}, {}   , {}, {}, {}},
//        {{2,2}            , {}, {}, {}, {} , {}, {3,4}, {}, {}, {}},
//        {{}               , {}, {}, {}, {} , {}, {}   , {}, {}, {}},
//        {{}               , {}, {}, {}, {} , {}, {}   , {}, {}, {}},
//        {{}               , {}, {}, {}, {0}, {}, {}   , {}, {}, {}}
//    };

    // 10 Spalten, 7 Reihen, adressiert mit (x,y) oder (column, row)
    // eine Reihe in der Deklaration entspricht bei Zugriff/Ausgabe einer Spalte
    private static final int[][][] MAP = {
            {{1, 1, 3, 3, 4, 4, 4, 4}, {}, {}, {2, 2}, {}, {}, {}},
            {{}, {}, {}, {}, {}, {}, {}},
            {{}, {}, {}, {}, {}, {}, {}},
            {{}, {}, {}, {}, {}, {}, {}},
            {{}, {}, {}, {}, {}, {}, {0}},
            {{}, {}, {}, {}, {}, {}, {}},
            {{}, {}, {}, {3, 4}, {}, {}, {}},
            {{}, {}, {}, {}, {}, {}, {}},
            {{}, {}, {}, {}, {}, {}, {}},
            {{}, {}, {}, {}, {}, {}, {}}
    };
    /**
     * Die Bestelllisten. Jede Liste enthält die Bestellungen mehrerer Kunden.
     * Ein Kunde kann in einer Liste mehrere Bestellungen haben.
     * Ein Kunde wird durch seine Position identifiziert.
     * Eine Bestellung gehört zu einem Kunden und benennt das gewünschte Produkt und die gewünschte Anzahl.
     */
    private static final int[][][] ORDER_SERIES = {
            { //series 0
                    {1, 2, 0, 1}, //customer at (1,2) orders product 0, 1 times
            },
            { //series 1
                    {1, 2, 0, 1}, //customer at (1,2) orders product 0, 1 times
                    {1, 2, 1, 1}, //customer at (1,2) orders product 1, 1 times
                    {8, 5, 3, 3}  //customer at (8,5) orders product 3, 3 times
            },
            { //series 2
                    {7, 4, 4, 5}, //customer at (7,4) orders product 4, 5 times
                    {0, 6, 0, 1}  //customer at (0,6) orders product 0, 1 times
            },
            { //series 3
                    {7, 4, 0, 5} //customer at (7,4) orders product 0, 5 times
            }
    };

    /**
     * Liefert die Dimension der Karte.
     * Der erste Wert enthält die Anzahl der Spalten, der zweite die Anzahl der Reihen.
     *
     * @return die Dimension der Karte {Spaltenanzahl, Reihenanzahl}
     */
    public static int[] getMapDimensions() {
        return new int[]{MAP.length, MAP[0].length};
    }

    /**
     * Liefert die Anzahl der Bestelllisten.
     *
     * @return die Anzahl der Bestelllisten
     */
    public static int getNoOfSeries() {
        return ORDER_SERIES.length;
    }

    /**
     * Prüft, ob eine Position ein Lager enthält. Dies ist der Fall,
     * wenn dort Produkte vorliegen.
     *
     * @param posX x-Position der zu prüfenden Zelle
     * @param posY y-Position der zu prüfenden Zelle
     * @return true, wenn dort ein Lager ist
     */
    public static boolean isWarehouse(int posX, int posY) {
        return MAP[posX][posY].length > 0;
    }


    /** Erstellt eine tiefe Kopie vom ersten Parameter(array)
     *
     * @param array Vorlage array
     * @return res, Die tiefe Kopie des Array
     */
    private static int[][][] arrayCopy(int[][][] array) {
        int[][][] res = new int[array.length][][];
        for (int i = 0; i < array.length; i++) {
            res[i] = new int[array[i].length][];
            for (int j = 0; j < array[i].length; j++) {
                res[i][j] = new int[array[i][j].length];
                System.arraycopy(array[i][j], 0, res[i][j], 0, array[i][j].length);
            }
        }
        return res;
    }

    /**
     * Liefert die tiefe! Kopie einer Bestellliste.
     *
     * @param idx Index der gewünschten Bestellliste
     * @return die Kopie der Bestellliste
     * @throws IllegalArgumentException wenn der Index nicht valide ist
     */
    public static int[][] getOrderSeries(int idx) {
        if (idx <= -1 || idx >= ORDER_SERIES.length )
            throw new IllegalArgumentException("Index " + idx + " nicht valide");

        int[][][] res = arrayCopy(ORDER_SERIES);

        return res[idx];
    }

    /**
     * Liefert die tiefe! Kopie der Karte.
     *
     * @return die Kopie der Karte
     */
    public static int[][][] getMap() {

        return arrayCopy(MAP);
    }

}

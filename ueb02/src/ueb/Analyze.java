package ueb;

import java.util.Arrays;

import static ueb.Data.*;

/**
 * Methoden zum Transportieren von Produkten von Lagern zu Kunden per Drohne.
 *
 * @author Mo, klk, Max, Nima
 */
public class Analyze {

    /**
     * Zeichen zur Darstellung eines Lagers auf einer Karte
     */
    public static final String SIGN_WAREHOUSE = "W";

    /**
     * Zeichen zur Darstellung eines Kunden auf einer Karte
     */
    public static final String SIGN_CUSTOMER = "C";

    /**
     * Zeichen zur Darstellung eines leeren Platzes auf einer Karte
     */
    public static final String SIGN_EMPTY = "E";

    /**
     * Position der Ladestation der Drohne
     */
    private final static int[] POS_SERVICE = {0, 0};

    /**
     * die aktuell geflogenen Einheiten der Drohne
     */
    private static int units = 0;

    /**
     * die aktuelle Karte. Initial wird sie aus Data geholt
     */
    private static int[][][] tempMap = getMap();

    /**
     * die aktuelle Position der Drohne. Initial startet sie bei POS_SERVICE
     */
    private static int[] posDrohne = POS_SERVICE.clone();

    /**
     * Setzt jede Klassenvariable auf ihren Initialwert.
     */
    public static void resetToOrigState() {
        units = 0;
        tempMap = getMap();
        posDrohne = POS_SERVICE.clone();
    }

    /**
     * Bestimmt die maximale Breite der übergebenen Spalte für eine Ausgabe in Spalten.
     *
     * @param column zu untersuchende Spalte
     * @return das Maximum der breitesten Zelle in der gegebenen Spalte + 1 (für das Zeichen der Zelle
     * W, C oder E für Lager, Kunde oder leer)
     */
    public static int getPrintWidthPerColumn(int column) {
        int max = 0;

        if (tempMap != null && column > -1 && column < tempMap.length) {
            for (int i = 0; i < tempMap[0].length; i++) {
                if (tempMap[column][i].length >= max)
                    max = tempMap[column][i].length;
            }
        }
        return max + 1;
    }

    /**
     * Gibt den inhalt dem übergebenen Warenhaus im String-format zurück
     *
     * @param warehouse zu untersuchendes Warenhaus
     * @return res String mit den Werten des Warenhauses
     */
    private static String getArticles(int[] warehouse) {
        String res = "";
        for (int i : warehouse) {
            res = res + i;
        }
        return res;
    }

    /**
     * Gibt die aktuelle Karte auf sout aus. Die Ausgabe beginnt und endet mit
     * jeweils einer horizontalen Linie und die Position der Drone wird
     * unterhalb der Karte angegeben.
     * Die Konstanten zur Darstellung der Zellen müssen hier genutzt werden.
     */
    public static void printCurrentState() {
        System.out.println("-".repeat(39));
        for (int j = 0; j < tempMap[0].length; j++) {
            for (int i = 0; i < tempMap.length; i++) {
                if (isWarehouse(i,j) || tempMap[i][j].length > 0 ) {
                    if (!isWarehouse(i,j))
                        System.out.print(SIGN_CUSTOMER);
                    else
                        System.out.print(SIGN_WAREHOUSE);

                } else
                    System.out.print(SIGN_EMPTY);

                System.out.printf("%-" + getPrintWidthPerColumn(i) + "s", getArticles(tempMap[i][j]));
            }
            System.out.println();
        }
        System.out.printf("%nDrone now at %d/%d flew %d units%n", Analyze.posDrohne[0], Analyze.posDrohne[1], Analyze.units);
        System.out.println("-".repeat(39));
    }

    /**
     * Berechnet die euklidische Distanz zwischen zwei Punkten.
     * Diese Routine arbeitet für beliebige Positionen, ist also nicht auf die Karte aus Data beschränkt.
     * Math.sqrt(), Math.pow() and Math.ceil() werden genutzt.
     * d = √((rb - ra)² + (cb - ca)²)
     *
     * @param pos1 der erste Punkt
     * @param pos2 der zweite Punkt
     * @return die euklidische Distanz zwischen den Punkten,
     * Integer.MAX_VALUE, wenn ein Parameter kein Array mit zwei int-Werten ist
     */
    public static int calcDistanceBetween(int[] pos1, int[] pos2) {
        if ( pos1 == null || pos2 == null || pos1.length != 2 || pos2.length != 2) {
            return Integer.MAX_VALUE;
        } else
            return (int) Math.ceil(Math.sqrt(Math.pow((pos1[X] - pos2[X]), 2) + Math.pow((pos1[Y] - pos2[Y]), 2)));
    }

    /**
     * Prüft, ob die gegebene Position auf der Karte liegt.
     *
     * @param pos Position auf der Karte
     * @return true, wenn pos eine valide Position auf der Karte ist
     */
    public static boolean isValidPosition(int[] pos) {
        if (tempMap == null || pos == null ||  pos.length != 2)
            System.err.printf("%s sollte keine valide Position sein%n", Arrays.toString(pos));
        else
            return ((pos[X] < tempMap.length && pos[X] >= 0) && (pos[Y] < tempMap[0].length && pos[Y] >= 0));
        return false;
    }

    /**
     * Fliegt die Drohne von ihrer aktuellen Position zu einer übergebenen Position.
     * Gibt das Ziel und die geflogene (euklidische) Distanz aus.
     * Verändert die Variablen `units` und `posDrone`.
     * Ist die übergebene Position nicht valide, wird eine Meldung auf System.err ausgegeben.
     *
     * @param pos die anzufliegende Position
     */
    private static void flyDroneTo(int[] pos) {
        if (isValidPosition(pos)) {
            int distance = calcDistanceBetween(posDrohne, pos);
            units = units + distance;
            posDrohne = pos;
            System.out.printf("fly drone to %d/%d distance %d%n", posDrohne[X], posDrohne[Y], distance);
        }
    }

    /**
     * Transportiert ein Produkt einer Bestellung mit der Drohne zu einer Zielposition.
     * Die Drohne wird zu `from` geflogen, dort werden `count` Produkte `product` aufgenommen
     * und mit der Drohne zu `to` transportiert. Gibt es nicht genug Produkte bei `from`, wird
     * die noch nicht erfüllte Anzahl der Bestellung zurückgegeben.<br>
     * Beispiel: Es sollen 7 Produkte zu `to` transportiert werden, bei `from` sind nur 3 davon
     * vorhanden, dann ist der Rückgabewert dieser Methode 4.
     * <p>
     * Ist `from` oder `to` keine valide Position, wird eine Meldung auf System.err ausgegeben.
     * Ist das Produkt bei `from` gar nicht vorhanden, wird eine Meldung auf System.err ausgegeben.
     *
     * @param from    die Postion des Lagers, aus dem geholt werden soll
     * @param to      die Position des Kunden, zu dem transportiert werden soll
     * @param product das zu transportierende Produkt
     * @param count   Anzahl der zu transportierenden Produkte
     * @return Anzahl der noch zu transportierenden Produkte
     */
    private static int transportSameProducts(int[] from, int[] to, int product, int count) {
        boolean finish = false;
        int droneCount = 0;
        if (isValidPosition(from) && isValidPosition(to)) {
                // Einsammeln der Produkte
                flyDroneTo(from);
                droneCount = count;
                for (droneCount = count; droneCount > 0 && !finish; droneCount--) {
                    int idx = ueb.ArrayTools.containsAt(tempMap[from[X]][from[Y]], product);
                    if (idx != -1)
                        tempMap[from[X]][from[Y]] = ueb.ArrayTools.deleteElementAt(tempMap[from[X]][from[Y]], idx);
                    else {
                        finish = true;
                        droneCount++;
                    }
                }
                // Ausliefern der Produkte
                flyDroneTo(to);
                for (int j = 0; j < (count - droneCount); j++) {
                    tempMap[to[X]][to[Y]] =
                            ArrayTools.insertElementAt(tempMap[to[X]][to[Y]], 0, product);
                }

        }
        return droneCount;
    }

    /**
     * Bestimmt das dichteste Lager ausgehend von einer übergebenen Position, welches das übergebene Produkt enthält.
     *
     * @param pos     die Ausgangsposition
     * @param product das gesuchte Produkt
     * @return das am nächsten (euklidische Distanz) liegende Lager mit dem Produkt;
     * `null`, wenn kein Lager dieses Produkt hat
     */
    private static int[] findNearestWarehouse(int[] pos, int product) {
        int[] res = new int[2];
        int min = tempMap.length * tempMap[0].length;
        for (int j = 0; j < tempMap[0].length; j++) {
            for (int i = 0; i < tempMap.length; i++) {
                if (isWarehouse(i, j) && ArrayTools.containsAt(tempMap[i][j], product) != -1) {
                    if (min > calcDistanceBetween(new int[]{i, j}, pos)) {
                        min = calcDistanceBetween(new int[]{i, j}, pos);
                        res[0] = i;
                        res[1] = j;
                    }
                }
            }
        }
        return min == (tempMap.length * tempMap[0].length) ? null : res;
    }

    /**
     * Transportiert eine Bestellliste per Drone.
     * Arbeitet jede Bestellung einer Liste ab und gibt die Fluginformationen aus.
     * Das nächste Warenhaus mit dem Produkt wird gesucht, die bestellte Anzahl
     * zur Zieladresse geliefert. Hat das erste gefundene Lager nicht genug von
     * dem Produkt gelagert, werden nach dem Transport des ersten Teils der
     * Bestellung zum Kunden weitere Lager angeflogen.
     * Ist das Produkt in keinem der Lager (mehr) vorhanden, wird eine
     * Fehlermeldung auf System.err ausgegeben.
     * Nachdem alle Bestellungen abgearbeitet wurden, fliegt die Drohne zurück
     * zur Ladestation.
     *
     * @param orders die Bestellliste
     * @throws IllegalArgumentException, wenn als Liste `null` übergeben wurde
     */
    public static void transportOrdersOfOneSeries(int[][] orders) {
        for (int i = 0; i < orders.length; i++) {
            int count = orders[i][3];
            int[] address = new int[]{orders[i][0], orders[i][1]};
            int product = orders[i][2];
            System.out.printf("deliver %d of product %d to (%d/%d)%n", count, product, address[0], address[1]);
            while (count > 0) {
                int[] pos = findNearestWarehouse(posDrohne, product);
                if ( pos == null) {
                    System.err.printf("%nError: %d of product %d missing in warehouses%n", count, product);
                    count = 0;
                }else {
                    count = transportSameProducts(pos, address, product, count);
                }
            }
        }
        flyDroneTo(POS_SERVICE);
    }
}


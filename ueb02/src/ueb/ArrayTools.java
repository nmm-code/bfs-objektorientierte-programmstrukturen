package ueb;

/**
 * Hilfsmethoden zur Bearbeitung eines Arrays.
 *
 * @author Mo, klk, Max, Nima
 */
public class ArrayTools {

    /**
     * Bestimmt, ob ein bestimmter Wert in einem gegebenen Array vorhanden ist
     * und liefert den Index des ersten Vorkommens.
     *
     * @param array das gegebene Array
     * @param value der zu suchende Wert
     * @return die Position des ersten Vorkommens des Wertes, -1, wenn er nicht enthalten ist
     * @throws IllegalArgumentException wenn das gegebene Array `null` ist
     */
    public static int containsAt(int[] array, int value) {
        if (array == null)
            throw new IllegalArgumentException("Array ist `null`");
      
        int res = -1;
        for (int i = 0; i < array.length && res == -1; i++) {
            if (value == array[i])
                res = i;
        }
        return res;

    }

    /**
     * Erstellt ein neues Array gleich dem übergebenen
     * aber ohne den Eintrag an der gegebenen Position (ist also um eins kürzer).
     *
     * @param array das ursprüngliche Array
     * @param idx   der Index, an dem gelöscht werden soll
     * @return ein neues Array, gleich dem übergebenen aber ohne den Eintrag an der gegebenen Position
     * @throws IllegalArgumentException wenn für das Array `null` übergeben wird oder der Index nicht valide ist
     */
    public static int[] deleteElementAt(int[] array, int idx) {
        if (array == null || idx < 0 || idx >= array.length )
            throw new IllegalArgumentException("Array ist `null` oder idx nicht valide");
        int[] res = new int[array.length - 1];
        int j = 0;
        for (int i = 0; i < array.length; i++) {
            if (i != idx) {
                res[j] = array[i];
                j++;
            }
        }
        return res;
    }

    /**
     * Erstellt ein neues Array gleich dem übergebenen und fügt darin einen Wert am übergebenen Index ein.
     *
     * @param array das Array
     * @param idx   der Index, an dem eingefügt werden soll
     * @param value der einzufügende Wert
     * @return ein neues Array gleich dem übergebenen mit dem eingefügten Wert
     * @throws IllegalArgumentException wenn für das Array `null` übergeben wird oder der Index nicht valide ist
     */
    public static int[] insertElementAt(int[] array, int idx, int value) {
       
        if (array == null || idx < 0 || idx > array.length)
            throw new IllegalArgumentException("Array ist `null` oder idx nicht valide");

        int[] res = new int[array.length + 1];
        int j = 0;
        for (int i = 0; i < res.length; i++) {
            if (i != idx) {
                res[i] = array[j];
                j++;
            } else
                res[i] = value;
        }
        return res;
    }

    /**
     * Liefert die Länge des größten Arrays in dem Array.
     *
     * @param array 2-dimensionales Array, das untersucht werden soll
     * @return Länge des längsten Arrays in dem Array, -1, wenn nur `null`-Werte enthalten sind
     * @throws IllegalArgumentException wenn `null` oder ein Array der Länge 0 übergeben wurde
     */
    public static int getLengthOfLongestArray(int[][] array) {
        if (array == null || array.length == 0)
            throw new IllegalArgumentException("`null` oder ein Array der Länge 0 übergeben worden");

        int max = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                if (max < array[i].length)
                    max = array[i].length;
            }
        }
        return  max;
    }
}

package ueb;

/**
 * Anylsiert den Zahlenbereich start .. end <br>
 * Wenn diese Zahl eine Primzahl oder nagativ Zahl ist <br>
 * Gibt noch zusätzlich diese Eigenschaft aus <br>
 *     * Mirpzahl <br>
 *     * Primzwillig <br>
 *
 * Verschlüsselt diese noch mithilfe von Xor mit dem Schlüssel <br>
 *
 * @author Nima
 * @author Max
 */

public class Main {

    /**
     * Rechnet die Laenge der Zahl
     *
     * @param num uebergebene Zahl
     * @return die Laenge
     */
    private static int getLength(int num) {
        int res = num == 0 ? 1 : 0;

        if (num < 0) {
            res++;
            num = -num;
        }
        while (num > 0) {
            res++;
            num /= 10;
        }
        return res;
    }

    /**
     * Liefert den Maximalen Wert
     *
     * @param num1 erster Wert
     * @param num2 zweiter Wert
     * @return groesste Zahl
     */
    private static int getMax(int num1, int num2) {
        final int num1Length = getLength(num1);
        final int num2Length = getLength(num2);
        return num1Length > num2Length ? num1Length : num2Length;
    }

    /**
     * Ueberprueft num, ob die Zahl eine Primenzahl ist
     *
     * @param num die ueberpruefende Zahl
     * @return wahr / false
     */
    private static boolean isPrime(int num) {
        boolean prime = true;
        if (num != 2)
            if ((num < 2) || (num % 2 == 0)) {
                prime = false;
            } else {
                int i = 3;
                while ((i * i <= num) && prime) {
                    if (num % i == 0) {
                        prime = false;
                    }
                    i += 2;
                }
            }
        return prime;
    }

    /**
     * Verschlüsselt nach dem Schluessel und gibt die Zahl zurueck
     *
     * @param num eingabe
     * @param key schluessel
     * @return Verschluesselte Zahl
     */
    public static int encodeNumb(int num, int key) {
        for (int i = 0; i < 4; i++) {
            key = key | (key << 8);
        }
        return key ^ num;
    }


    /**
     * Liefert die Zahl mit umgedrehter Ziffernfolge
     *
     * @param num ueberpruefende Zahl
     * @return umgedrehte Zahl
     */
    private static int getReverse(int num) {
        int res = 0;
        while (num > 0) {
            res *= 10;
            res += num % 10;
            num /= 10;
        }
        return res;
    }

    /**
     * Ueberprueft ob der Schluessel im 8 bit Bereich liegt
     *
     * @param key der Schluessel
     * @return true / false
     */
    private static boolean isValidKey(int key) {
        return ((key >> 8) == 0);
    }

    /**
     * Liefert die binaere Darstellung der Zahl
     *
     * @param num eingabe
     * @return Binaerschreibweise als String
     */
    private static String toBitString(int num) {
        String res = "";
        int counter = 0;

        while (num != 0) {
            if ((num & 1) == 1)
                res = "1" + res;
            else
                res = "0" + res;

            num = num >>> 1;
            counter++;

            if ((num > 0) && (counter % 4 == 0))
                res = "_" + res;
        }

        return res;
    }

    /**
     * Ueberprueft ob sie einen Primzwilling hat
     *
     * @param num die ueberpruefende Zahl
     * @return true / false
     */
    public static boolean isPrimTwin(int num) {
        return isPrime(num) && (isPrime(num - 2) || isPrime(num + 2));
    }

    /**
     * Ueberprueft ob es eine Mirpzahl ist
     *
     * @param num die ueberpruefende Zahl
     * @return true / false
     */
    public static boolean isMirpNum(int num) {
        int reverse = getReverse(num);
        return isPrime(num) && isPrime(reverse) && (reverse != num);
    }

    /**
     * Hauptprogramm: <br>
     *
     * Initialisiert den Start und End bereich und der Schlüssel wird bestimmt <br>
     * Erstellt eine formatierte Ausgabe <br>
     *
     * @param args wird nicht verarbeitet
     */
    public static void main(String[] args) {
        final int start = -2;
        final int end = 4;
        final int key = 460;
        final int biggest = getMax(start, end);

        System.out.printf("Es werden Zahlen im Bereich %d .. %d untersucht.%n", start, end);
        System.out.println("Negative und Primzahlen werden ausgegeben.");

        boolean isKeyValid = isValidKey(key);
        if (!isKeyValid) System.out.print("WARNUNG: Verschlüsselung nicht möglich, ungültiger ");

        System.out.printf("Schlüssel = %d (%s)%n", key, toBitString(key));
        for (int i = start; i <= end; i++) {
            if ((i < 0) || isPrime(i)) {
                System.out.printf("%" + biggest + "d: ", i);
                System.out.printf("%8s ", isMirpNum(i) ? "Mirpzahl" : "");
                System.out.printf("%16s ", isPrimTwin(i) ? "hat Primzwilling" : "");
                if (isKeyValid) {
                    System.out.printf("verschlüsselt: %10d ", encodeNumb(i, key));
                    System.out.printf("(%39s)", toBitString(encodeNumb(i, key)));
                }
                System.out.println();
            }
        }
    }
}

package ueb;

/**
 * Für jede vorhandene Bestellliste in Data wird das Ausliefern per Drohne simuliert
 * und die Fahrtwege ausgegeben.
 *
 * @author Mo
 */
public class Main {

    /**
     * Für jede vorhandene Bestellliste in Data wird das Ausliefern per Drohne simuliert
     * und die Fahrtwege ausgegeben.
     *
     * @param args nicht genutzt
     */
    public static void main(String[] args) {

        for (int no = 0; no < Data.getNoOfSeries(); no++) {
            int[][] order = Data.getOrderSeries(no);
            Analyze.resetToOrigState();
            System.out.println("***************************************");
            System.out.println("Warehouses (initial):");
            Analyze.printCurrentState();
            System.out.println("Orders of series " + no + ":");
            Analyze.transportOrdersOfOneSeries(order);
            Analyze.printCurrentState();
        }
    }

}

package logic;

import java.util.Random;

/**
 * Die Klasse beschreibt eine simple mathematische Aufgabe
 *
 * @author nima, max
 */
public class MathProblem {

    /**
     * Die Random Konstante für die generierung
     */
    private final static Random random = new Random();

    /**
     * größte Zahl die erstellt werden kann
     * - 0..UPPER_BOUND-1
     */
    private final static int UPPER_BOUND = 100;

    /**
     * Erster Operand
     */
    private int firstOperand;

    /**
     * Zweiter Operand
     */
    private int secondOperand;

    /**
     * Operator für die Berechnung
     */
    private final CalculationType operator;

    /**
     * Erstellt eine Aufgabe so lange bis es valid ist
     *
     * @param op der Operator
     */
    public MathProblem(CalculationType op) {
        boolean finish = false;
        operator = op;
        while (!finish) {
            firstOperand = random.nextInt(UPPER_BOUND);
            secondOperand = random.nextInt(UPPER_BOUND);
            finish = operator.validOperandsN(firstOperand, secondOperand);
        }
    }

    /**
     * Constructor Function
     * Only for Tests
     * @param x first Operand
     * @param y second Operand
     * @param calculationType type
     */
    public MathProblem(int x, int y, CalculationType calculationType) {
        firstOperand = x;
        secondOperand = y;
        operator = calculationType;
    }

    /**
     * Berechnet firstOperand , SecondOperand anhang des Operators
     *
     * @return liefer das ergebnis
     */
    protected int solve() {
        return operator.calculate(firstOperand, secondOperand);
    }

    /**
     * @return liefert den ersten Operanden
     */
    public int getFstOperand() {
        return firstOperand;
    }

    /**
     * @return liefert den zweiten Operanden
     */
    public int getSndOperand() {
        return secondOperand;
    }

    /**
     * @return liefert den Operator. Die Sichtbarkeit ist uneingeschränkt.
     */
    public CalculationType getCalcType() {
        return operator;
    }

}

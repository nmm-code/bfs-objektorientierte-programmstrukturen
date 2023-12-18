package logic;

/**
 * Enumeration for the different, binary calculation types: addition, subtraction, multiplication and division.
 *
 * @author cei, Max, Nima
 */
public enum CalculationType {
    ADDITION,
    SUBTRACTION,
    MULTIPLICATION,
    DIVISION;

    /**
     * Calculates what the result of the calculation with the two given operands is. When the calculation type is
     * division and the second operand is 0, this will lead to an ArithmeticException, as this is not a valid operand.
     * To ensure that the calculation is valid, the user might want to call validOperandsInN before calling this method.
     *
     * @param x the first operand
     * @param y the second operand. Should not be 0, if division is calculation type.
     * @return the result of the calculation with the two given operands (e.g. x + y)
     */
    public int calculate(int x, int y) {
        return switch (this) {
            case ADDITION -> x + y;
            case SUBTRACTION -> x - y;
            case MULTIPLICATION -> x * y;
            case DIVISION -> x / y;
        };
    }

    /**
     * Checks if the given operands are valid for a calculation with only natural numbers (integers >= 0). Not only the
     * operands need to be valid natural numbers, the result of the calculation need to be natural number as well.
     *
     * @param x the first operand
     * @param y the second operand
     * @return if the two given operands enable a valid calculation in N
     */
    public boolean validOperandsN(int x, int y) {
        if(x < 0 || y < 0)
            return false;

        int res = 0;
        switch (this) {
            case ADDITION -> res = x + y;
            case SUBTRACTION -> res = x - y;
            case MULTIPLICATION -> res = x * y;
            case DIVISION -> {
                if (x == 0 || y == 0)
                    res = -1;
                else
                    res = x % y;
            }
        }
        return this == DIVISION ? res == 0 :  res >= 0;
    }


}

package logic;

/**
 * Used for testing. As we are not testing the gui, nothing happens in the methods.
 *
 * @author cei
 */
public class FakeGUI implements GUIConnector {
    @Override
    public void displayProblem(MathProblem problem) {
        //do nothing
    }

    @Override
    public void displaySolved(int correctSolution, int givenSolution, boolean solvedCorrectly) {
        //do nothing
    }

    @Override
    public void gameEnded(int correctlySolved) {
        //do nothing
    }
}

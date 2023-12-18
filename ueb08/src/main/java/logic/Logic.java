package logic;

import java.util.Stack;

/**
 * Logic for the game "little professor", which is a trainer for mental arithmetics. One instance of this is one round
 * of math problems. When the game is re-started, a new instance needs to be created.
 *
 * @author cei, nima, max
 */
public class Logic {

    /**
     * The number of math problems to be solved. In the original this is 5.
     */
    public static final int NUM_PROBLEMS = 5;

    /**
     * Number of problems that have so far been solved correctly.
     */
    private int numCorrectlySolved;
    /**
     * Math Problem that still need to be solved.
     */
    private final Stack<MathProblem> problems;
    /**
     * The connection to the gui.
     */
    private GUIConnector gui;

    /**
     * Creates a new round with a given calculation type. For a new round all problems that should be solved in this
     * round are created at the beginning.
     *
     * @param gui  the connection to the graphical user interface
     * @param type the calculation type for the five (NUM_PROBLEMS) problems. Only needed for creation of the math
     *             problems (so it is not necessary to remember this after creating all the problems)
     */
    public Logic(GUIConnector gui, CalculationType type) {
        this.gui = gui;
        this.problems = createProblems(type);
        numCorrectlySolved = 0;
        showNewTask();
    }

    /**
     * Erstellt für den Little Professor n-(NUM_PROBLEMS) viele Aufgaben(Mathproblem)
     * @param type
     * @return Stack mit den Aufgaben
     */
    private Stack<MathProblem> createProblems(CalculationType type) {
        Stack<MathProblem> problemStack = new Stack<>();
        for (int i = 0; i < NUM_PROBLEMS; i++) {
            MathProblem problem = new MathProblem(type);
            problemStack.push(problem);
        }
        return problemStack;
    }

    /**
     * Constructor ONLY used for testing. Possible to pass a different number of math problems than specified by the
     * constant of this class. May contain redundant code, when compared to the normal constructor of this class, but as
     * this is only for testing, this is acceptable.
     *
     * @param gui          reference to the gui
     * @param mathProblems variable number of math problems
     */
    Logic(GUIConnector gui, MathProblem... mathProblems) {
        this.gui = gui;
        this.problems = new Stack<>();
        for (MathProblem problem : mathProblems) {
            this.problems.push(problem);
        }
    }

    /**
     * Called when the user tries to solve the current math problem. If there are no problems left after the current one
     * has been solved, this method also informs the gui to ensure there are no more inputs.
     *
     * @param givenSolution the solution of the user for the current problem
     */
    public void solve(int givenSolution) {
        boolean solved = getFirstProblem().solve() == givenSolution;
        gui.displaySolved(getFirstProblem().solve(), givenSolution, solved);

        MathProblem problem = this.problems.peek();
        if (problem.solve() == givenSolution)
            numCorrectlySolved++;

        nextTask();
        if (this.problems.empty())
            gui.gameEnded(numCorrectlySolved);
        else
            showNewTask();
    }

    /**
     * ONLY for testing.
     *
     * @return the number of problems left that should be solved
     */
    int getAmountOfProblemsLeft() {
        return this.problems.size();
    }

    /**
     * ONLY for testing.
     *
     * @return the number of correctly solved problems
     */
    int getNumCorrectlySolved() {
        return this.numCorrectlySolved;
    }

    /**
     * Liefert das erste Problem
     *
     * @return das erste Problem
     */
    private MathProblem getFirstProblem() {
        return problems.peek();
    }

    /**
     * nimmt dem ersten Problem weg vom stapel
     */
    private void nextTask() {
        problems.pop();
    }

    /**
     * Zeigt das erste Problem vom Stapel an
     */
    private void showNewTask() {
        gui.displayProblem(getFirstProblem());
    }
}

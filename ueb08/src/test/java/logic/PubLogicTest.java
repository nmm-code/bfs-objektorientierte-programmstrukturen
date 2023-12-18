package logic;

import org.junit.Test;

import static logic.CalculationType.*;
import static org.junit.Assert.*;


/**
 * Some simple tests to test the Logic of the Little Professor.
 *
 * @author cei
 */
public class PubLogicTest {

    @Test
    public void testCorrectInitialValues_WRegularConstructor() {
        Logic logic = new Logic(new FakeGUI(), ADDITION);
        assertEquals(0, logic.getNumCorrectlySolved());
        assertEquals(Logic.NUM_PROBLEMS, logic.getAmountOfProblemsLeft());
    }

    @Test
    public void testCorrectInitialValues_WTestConstructor_NoMathProblems() {
        Logic logic = new Logic(new FakeGUI());
        assertEquals(0, logic.getNumCorrectlySolved());
        assertEquals(0, logic.getAmountOfProblemsLeft());
    }

    @Test
    public void testCorrectInitialValues_WTestConstructor_OneMathProblems() {
        Logic logic = new Logic(new FakeGUI(), new MathProblem(1, 2, ADDITION));
        assertEquals(0, logic.getNumCorrectlySolved());
        assertEquals(1, logic.getAmountOfProblemsLeft());
    }

    @Test
    public void testCorrectInitialValues_WTestConstructor_MultipleMathProblems() {
        Logic logic = new Logic(new FakeGUI(), new MathProblem(1, 2, ADDITION),
                new MathProblem(2, 2, ADDITION),
                new MathProblem(3, 2, ADDITION),
                new MathProblem(4, 2, ADDITION),
                new MathProblem(5, 2, ADDITION),
                new MathProblem(6, 2, ADDITION));
        assertEquals(0, logic.getNumCorrectlySolved());
        assertEquals(6, logic.getAmountOfProblemsLeft());
    }

    @Test
    public void testSolveOneWrong_WRegularConstructor() {
        Logic logic = new Logic(new FakeGUI(), ADDITION);
        logic.solve(-1); //can't be the solution as we are only adding up two natural numbers
        assertEquals(0, logic.getNumCorrectlySolved());
        assertEquals(Logic.NUM_PROBLEMS - 1, logic.getAmountOfProblemsLeft());
    }

    @Test
    public void testSolveOneCorrectly_WTestConstructor() {
        Logic logic = new Logic(new FakeGUI(), new MathProblem(1, 2, ADDITION));
        assertEquals(0, logic.getNumCorrectlySolved());
        assertEquals(1, logic.getAmountOfProblemsLeft());
        logic.solve(3);
        assertEquals(1, logic.getNumCorrectlySolved());
        assertEquals(0, logic.getAmountOfProblemsLeft());
    }


}

package gui;

import javafx.scene.control.*;
import logic.GUIConnector;
import logic.Logic;
import logic.MathProblem;

/**
 * Eine Klasse die GUI und logik verbindet, um es auf der GUI darzustellen
 *
 * @author nima, max
 */
public class JavaFXGui implements GUIConnector {
    /**
     * Label für die Aufgabe
     */
    private final Label lblTask;
    /**
     * Textfeld für die Lösung der Aufgabe
     */
    private final TextField txfResult;
    /**
     * Label für die alten Aufgaben
     */
    private final Label lblOldTasks;
    /**
     * Label für den Prozess, welche Aufgaben richtig gelöst wurden
     */
    private final Label lblProcess;
    /**
     * Button um die Aufgabe abzuschicken
     */
    private final Button btnSolve;

    /**
     * Konstruktor Funktion
     *
     * @param solve Button
     * @param task Aufgaben Label
     * @param res Textfeld
     * @param old alte Aufgaben
     * @param process emotes Feld
     */
    public JavaFXGui(Button solve, Label task, TextField res, Label old, Label process) {
        lblTask = task;
        txfResult = res;
        lblOldTasks = old;
        lblProcess = process;
        btnSolve = solve;
        txfResult.setDisable(false);
        btnSolve.setDisable(false);
        lblProcess.setText("");
        lblOldTasks.setText("");
    }
    @Override
    public void displayProblem(MathProblem problem) {
        String op = switch (problem.getCalcType()) {
            case ADDITION -> "+";
            case SUBTRACTION -> "-";
            case MULTIPLICATION -> "*";
            case DIVISION -> "/";
        };
        lblTask.setText(String.format("%2d %s %2d = ", problem.getFstOperand(), op, problem.getSndOperand()));
        txfResult.setText("");
    }

    @Override
    public void displaySolved(int correctSolution, int givenSolution, boolean solvedCorrectly) {
        String res = String.format("%s %4d %s", lblTask.getText(), givenSolution ,
                (solvedCorrectly ? "correct" : "was wrong, correct is %d".formatted(correctSolution)));
        lblOldTasks.setText(lblOldTasks.getText() + "\n" + res);
        lblProcess.setText(lblProcess.getText() + (solvedCorrectly ? "🌟" : "💩"));
    }

    @Override
    public void gameEnded(int correctlySolved) {
        txfResult.setDisable(true);
        btnSolve.setDisable(true);
        lblOldTasks.setText(lblOldTasks.getText() + "\n" + correctlySolved + "/" + Logic.NUM_PROBLEMS);
    }

}

package gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.converter.IntegerStringConverter;
import logic.CalculationType;
import logic.Logic;
import java.util.regex.Pattern;
import static logic.CalculationType.*;

/**
 * Kontroller Klasse die Events der GUI verarbeitet
 *
 * @author nima, max
 */
public class UserInterfaceController {

    /**
     * die Gruppe die den ausgewählt CalcType bestimmt
     */
    @FXML
    private ToggleGroup tglGrpCalcType;

    /**
     * Label für die Aufgabe
     */
    @FXML
    private Label lblTask;

    /**
     * Textfeld für die Lösung der Aufgabe
     */
    @FXML
    private TextField txfResult;

    /**
     * Button um die Aufgabe abzuschicken
     */
    @FXML
    private Button btnSolve;

    /**
     * Label für die alten Aufgaben
     */
    @FXML
    private Label lblOldTasks;

    /**
     * Label für den Prozess, welche Aufgaben richtig gelöst wurden
     */
    @FXML
    private Label lblProcess;

    /**
     * Logic für die verarbeitung
     */
    private Logic logic;

    /**
     * Initialisiert die Komponenten und die Logic,
     * wird ausgeführt wenn:
     *  -  eine Runde gestartet wird
     *  -  eine neue CalcType gewählt wird
     */
    @FXML
    public void initialize() {
        TextFormatter<Integer> formatter = new TextFormatter<>(
                new IntegerStringConverter(),
                null,
                (TextFormatter.Change c) -> Pattern.matches("\\d*", c.getText()) ? c : null);
        this.txfResult.setTextFormatter(formatter);

        JavaFXGui gui = new JavaFXGui(btnSolve, lblTask, txfResult, lblOldTasks, lblProcess);
        logic = new Logic(gui, getCurrCalcType());
    }

    /**
     * Eventmethode, für den Button,
     * wenn der gedrückt wird und wird nur ausgeführt, wenn eine Zahl eingeben wurde
     */
    @FXML
    void solve() {
        if (!txfResult.getText().equals(""))
            logic.solve(Integer.parseInt(txfResult.getText()));
    }

    /**
     * Getter Methode die CalcType zurückgibt welches ausgewählt wurde
     *
     * @return CalcType welches ausgewählt wurde
     */
    CalculationType getCurrCalcType() {
        RadioMenuItem selected = (RadioMenuItem) tglGrpCalcType.getSelectedToggle();
        return switch(selected.getText()) {
            case "Subtraction" -> SUBTRACTION;
            case "Multiplication" -> MULTIPLICATION;
            case "Division" -> DIVISION;
            default -> ADDITION;
        };
    }
}
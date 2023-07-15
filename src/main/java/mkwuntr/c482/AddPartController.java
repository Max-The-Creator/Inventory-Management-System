package mkwuntr.c482;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class AddPartController {

    @FXML
    private RadioButton inHouseRadioButton;

    @FXML
    private RadioButton outsourcedRadioButton;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField priceCostTextField;

    @FXML
    private TextField invTextField;

    @FXML
    private TextField maxTextField;

    @FXML
    private TextField minTextField;

    @FXML
    private TextField machineIDTextField;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    public void initialize() {
        // This method is called after all @FXML annotated members have been injected
        // Here you can add specific initialization code if needed
    }

    @FXML
    private void handleSaveButtonAction() {
        // Create a new part based on the text fields
        int id = Integer.parseInt(idTextField.getText());
        String name = nameTextField.getText();
        double price = Double.parseDouble(priceCostTextField.getText());
        int stock = Integer.parseInt(invTextField.getText());
        int min = Integer.parseInt(minTextField.getText());
        int max = Integer.parseInt(maxTextField.getText());

        // As Part is abstract, you should create a new instance of a concrete class that extends Part
        // Assume there is a class called InHousePart
        // Part newPart = new InHousePart(id, name, price, stock, min, max);

        // Add part to the inventory or wherever it needs to go...
    }

    @FXML
    private void handleCancelButtonAction() {
        // Code to handle cancel action goes here
    }
}

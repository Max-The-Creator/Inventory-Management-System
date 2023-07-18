package mkwuntr.c482;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;

import java.io.IOException;

/**
 * Controller for adding a new Part into the inventory.
 */
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
    private TextField inventoryTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextField maxTextField;

    @FXML
    private TextField minTextField;

    @FXML
    private TextField machineIdOrCompanyNameTextField;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Inventory inventory;

    @FXML
    private Label machineIDOrCompanyLabel;

    @FXML
    // Create ToggleGroup
    private ToggleGroup partTypeToggleGroup = new ToggleGroup();



    /**
     * Initialization method for the controller.
     * Sets up listeners and default states.
     */
    @FXML
    public void initialize() {
        inHouseRadioButton.setToggleGroup(partTypeToggleGroup);
        outsourcedRadioButton.setToggleGroup(partTypeToggleGroup);

        // Set In-House radio button as default
        inHouseRadioButton.setSelected(true);
        machineIDOrCompanyLabel.setText("Machine ID");

        // Add listener
        partTypeToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.equals(inHouseRadioButton)) {
                machineIDOrCompanyLabel.setText("Machine ID");
            } else {
                machineIDOrCompanyLabel.setText("Company");
            }
        });

        idTextField.setText(String.valueOf(Inventory.getNextPartID()));
        idTextField.setDisable(true);
    }

    /**
     * Handles the toggling of the radio buttons.
     */
    @FXML
    private void handlePartSourceToggle() {
        // Change the prompt text based on the selected radio button
        if (inHouseRadioButton.isSelected()) {
            machineIdOrCompanyNameTextField.setPromptText("Machine ID");
            machineIDOrCompanyLabel.setText("Machine ID");
        } else {
            machineIdOrCompanyNameTextField.setPromptText("Company Name");
            machineIDOrCompanyLabel.setText("Company Name");
        }
    }

    /**
     * Handles the action of the Save button.
     *
     */
    @FXML
    private void handleSaveButton() {
        // Create a new Part and save it to the parts list
        if (validateFields()) { // call validateFields() first
            if (validateInventory()) { // validate inventory if fields are valid
                if (inHouseRadioButton.isSelected()) {
                    InHouse newPart = new InHouse(
                            Integer.parseInt(idTextField.getText()),
                            nameTextField.getText(),
                            Double.parseDouble(priceTextField.getText()),
                            Integer.parseInt(inventoryTextField.getText()),
                            Integer.parseInt(minTextField.getText()),
                            Integer.parseInt(maxTextField.getText()),
                            Integer.parseInt(machineIdOrCompanyNameTextField.getText())
                    );
                    // set the fields for the newPart object
                    newPart.setName(nameTextField.getText());
                    newPart.setPrice(Double.parseDouble(priceTextField.getText()));
                    newPart.setStock(Integer.parseInt(inventoryTextField.getText()));
                    newPart.setMax(Integer.parseInt(maxTextField.getText()));
                    newPart.setMin(Integer.parseInt(minTextField.getText()));
                    newPart.setMachineId(Integer.parseInt(machineIdOrCompanyNameTextField.getText()));

                    inventory.addPart(newPart);
                } else {
                    Outsourced newPart = new Outsourced(
                            Integer.parseInt(idTextField.getText()),
                            nameTextField.getText(),
                            Double.parseDouble(priceTextField.getText()),
                            Integer.parseInt(inventoryTextField.getText()),
                            Integer.parseInt(minTextField.getText()),
                            Integer.parseInt(maxTextField.getText()),
                            machineIdOrCompanyNameTextField.getText()
                    );
                    // set the fields for the newPart object
                    newPart.setName(nameTextField.getText());
                    newPart.setPrice(Double.parseDouble(priceTextField.getText()));
                    newPart.setStock(Integer.parseInt(inventoryTextField.getText()));
                    newPart.setMax(Integer.parseInt(maxTextField.getText()));
                    newPart.setMin(Integer.parseInt(minTextField.getText()));
                    newPart.setCompanyName(machineIdOrCompanyNameTextField.getText());

                    inventory.addPart(newPart);
                }
                // Change scene back to main scene
                // close the window
                Stage stage = (Stage) saveButton.getScene().getWindow();
                stage.close();
            }
        }
    }

    /**
     * Handles the action of the Cancel button.
     *
     * @param event The event triggered by clicking the Cancel button.
     */
    @FXML
    private void handleCancelButton(ActionEvent event) {
        // Get the current stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Close the current stage
        stage.close();
    }

    /**
     * Validates that the inventory value is within the min and max values.
     *
     * @return boolean that indicates if the values are valid.
     */
    @FXML
    private boolean validateInventory() {
        int min = Integer.parseInt(minTextField.getText());
        int max = Integer.parseInt(maxTextField.getText());
        int inv = Integer.parseInt(inventoryTextField.getText());
        if (inv < min || inv > max) {
            displayErrorMessage("Inventory not within allowed range");
            return false;
        }
        return true;
    }

    /**
     * Validates that all required fields are filled out and in the correct format.
     *
     * @return boolean that indicates if the values are valid.
     */
    @FXML
    private boolean validateFields() {
        String name = nameTextField.getText();
        String price = priceTextField.getText();
        String inv = inventoryTextField.getText();
        String max = maxTextField.getText();
        String min = minTextField.getText();
        String machineIdOrCompanyName = machineIdOrCompanyNameTextField.getText();

        if (name == null || name.isBlank()) {
            displayErrorMessage("Name field must not be empty.");
            return false;
        }

        try {
            double priceValue = Double.parseDouble(price);
        } catch (NumberFormatException | NullPointerException e) {
            displayErrorMessage("Price must be a numeric value.");
            return false;
        }

        try {
            int invValue = Integer.parseInt(inv);
        } catch (NumberFormatException | NullPointerException e) {
            displayErrorMessage("Inventory must be an integer value.");
            return false;
        }

        try {
            int maxValue = Integer.parseInt(max);
        } catch (NumberFormatException | NullPointerException e) {
            displayErrorMessage("Max must be an integer value.");
            return false;
        }

        try {
            int minValue = Integer.parseInt(min);
        } catch (NumberFormatException | NullPointerException e) {
            displayErrorMessage("Min must be an integer value.");
            return false;
        }

        if(inHouseRadioButton.isSelected()){
            try {
                int machineId = Integer.parseInt(machineIdOrCompanyName);
            } catch (NumberFormatException | NullPointerException e) {
                displayErrorMessage("Machine ID must be an integer value.");
                return false;
            }
        } else {
            if(machineIdOrCompanyName == null || machineIdOrCompanyName.isBlank()){
                displayErrorMessage("Company Name must not be empty.");
                return false;
            }
        }

        return true;
    }

    /**
     * Displays an error message alert.
     *
     * @param message The error message to display.
     */
    @FXML
    private void displayErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


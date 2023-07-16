package mkwuntr.c482;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import model.InHouse;
import model.Inventory;
import model.Outsourced;

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



    // Initialize method
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

    @FXML
    private void handleSaveButton(ActionEvent event) {
        // Create a new Part and save it to the parts list
        if (validateInventory()) {
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
        }
        // Change scene back to main scene
        // close the window
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancelButton(ActionEvent event) {
        // Get the current stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Close the current stage
        stage.close();
    }

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

    @FXML
    private void displayErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


package mkwuntr.c482;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

public class ModifyPartController {

    @FXML
    private Label modifyPartLabel;

    @FXML
    private RadioButton inHouseRadioButton;

    @FXML
    private RadioButton outsourcedRadioButton;

    @FXML
    private ListView<?> listView;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField invTextField;

    @FXML
    private TextField priceCostTextField;

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
    private Label nameLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Label invLabel;

    @FXML
    private Label priceCostLabel;

    @FXML
    private Label maxLabel;

    @FXML
    private Label minLabel;

    @FXML
    private Label machineIdOrCompanyNameLabel;

    @FXML
    private Part currentPart;

    @FXML
    public void initializePart(Part part) {
        // Set current part
        this.currentPart = part;


        //Automatically set ID
        idTextField.setText(String.valueOf(part.getId()));
        idTextField.setDisable(true);

        // Populate fields with part data
        idTextField.setText(String.valueOf(part.getId()));
        nameTextField.setText(part.getName());
        invTextField.setText(String.valueOf(part.getStock()));
        priceCostTextField.setText(String.valueOf(part.getPrice()));
        maxTextField.setText(String.valueOf(part.getMax()));
        minTextField.setText(String.valueOf(part.getMin()));

        // If part is an instance of InHouse, select inHouseRadioButton and set machineId
        if (part instanceof InHouse) {
            inHouseRadioButton.setSelected(true);
            machineIdOrCompanyNameTextField.setText(String.valueOf(((InHouse) part).getMachineId()));
        }
        // If part is an instance of Outsourced, select outsourcedRadioButton and set companyName
        else if (part instanceof Outsourced) {
            outsourcedRadioButton.setSelected(true);
            machineIdOrCompanyNameTextField.setText(((Outsourced) part).getCompanyName());
        }

        ToggleGroup group = new ToggleGroup();
        this.inHouseRadioButton.setToggleGroup(group);
        this.outsourcedRadioButton.setToggleGroup(group);

        if (part instanceof InHouse) {
            this.inHouseRadioButton.setSelected(true);
        } else if (part instanceof Outsourced) {
            this.outsourcedRadioButton.setSelected(true);
        }

    }

    @FXML
    private void handlePartSourceToggle() {
        // Change the prompt text based on the selected radio button
        if (inHouseRadioButton.isSelected()) {
            machineIdOrCompanyNameTextField.setPromptText("Machine ID");
            machineIdOrCompanyNameLabel.setText("Machine ID");
        } else {
            machineIdOrCompanyNameTextField.setPromptText("Company Name");
            machineIdOrCompanyNameLabel.setText("Company Name");
        }
    }

    @FXML
    public void handleSaveButtonAction() {
        if (validateInventory()) {
            int id = Integer.parseInt(idTextField.getText());
            String name = nameTextField.getText();
            double price = Double.parseDouble(priceCostTextField.getText());
            int stock = Integer.parseInt(invTextField.getText());
            int min = Integer.parseInt(minTextField.getText());
            int max = Integer.parseInt(maxTextField.getText());

            if (inHouseRadioButton.isSelected()) {
                int machineId = Integer.parseInt(machineIdOrCompanyNameTextField.getText());
                Part newPart = new InHouse(id, name, price, stock, min, max, machineId);
                Inventory.updatePart(Inventory.getAllParts().indexOf(currentPart), newPart);
            } else {
                String companyName = machineIdOrCompanyNameTextField.getText();  // assume machineIdTextField is used for company name
                Part newPart = new Outsourced(id, name, price, stock, min, max, companyName);
                Inventory.updatePart(Inventory.getAllParts().indexOf(currentPart), newPart);
            }

            // close the window
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
        }
    }


    @FXML
    public void handleCancelButtonAction() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private boolean validateInventory() {
        int min = Integer.parseInt(minTextField.getText());
        int max = Integer.parseInt(maxTextField.getText());
        int inv = Integer.parseInt(invTextField.getText());
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

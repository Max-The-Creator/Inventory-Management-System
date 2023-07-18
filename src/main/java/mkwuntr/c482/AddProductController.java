package mkwuntr.c482;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Controller class for the Add Product screen of the application.
 */
public class AddProductController {

    @FXML
    private TextField searchPartTextField;

    @FXML
    private TableView<Part> allPartsTableView;

    @FXML
    private Button removeAssociatedPartButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button addButton;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField invTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextField maxTextField;

    @FXML
    private TextField minTextField;

    @FXML
    private TableView<Part> associatedPartsTableView;

    @FXML
    private TableColumn<Part, Integer> partIdColumn;

    @FXML
    private TableColumn<Part, String> partNameColumn;

    @FXML
    private TableColumn<Part, Integer> partInventoryLevelColumn;

    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    @FXML
    private TableColumn<Part, Integer> associatedPartIdColumn;

    @FXML
    private TableColumn<Part, String> associatedPartNameColumn;

    @FXML
    private TableColumn<Part, Integer> associatedPartInventoryLevelColumn;

    @FXML
    private TableColumn<Part, Double> associatedPartPriceColumn;

    @FXML
    private ObservableList<Part> tempAssociatedParts = FXCollections.observableArrayList();

    /**
     * Initializes the controller. This method is automatically called after the fxml file has been loaded.
     */
    @FXML
    public void initialize() {
        // Define how to populate cells in part table
        partIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        partNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        partInventoryLevelColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());
        partPriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());

        // Populate allPartsTableView with all available parts
        allPartsTableView.setItems(Inventory.getAllParts());

        // Define how to populate cells in part table
        associatedPartIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        associatedPartNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        associatedPartInventoryLevelColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());
        associatedPartPriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());

        // Populate associatedPartsTableView with the temporary associated parts list
        associatedPartsTableView.setItems(tempAssociatedParts);

        // Add text field listeners
        searchPartTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                allPartsTableView.setItems(Inventory.getAllParts());
            }
        });

        //Automatically generate ID
        idTextField.setText(String.valueOf(Inventory.getNextProductID()));
        idTextField.setDisable(true);

    }

    /**
     * Handles action on the Save button.
     * It validates the input fields, creates a new product, adds associated parts, and closes the screen.
     */
    @FXML
    private void handleSaveButtonAction() {
        //validate inventory
        if (validateFields() && validateInventory()) {
            // Create a new product based on the text fields
            int id = Integer.parseInt(idTextField.getText());
            String name = nameTextField.getText();
            double price = Double.parseDouble(priceTextField.getText());
            int stock = Integer.parseInt(invTextField.getText());
            int min = Integer.parseInt(minTextField.getText());
            int max = Integer.parseInt(maxTextField.getText());

            Product newProduct = new Product(id, name, price, stock, min, max, FXCollections.observableArrayList(tempAssociatedParts));




            // add associated parts to the new product
            for (Part part : tempAssociatedParts) {
                newProduct.addAssociatedPart(part);
            }

            // Add product
            Inventory.addProduct(newProduct);

            // Close screen
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
        }
    }

    /**
     * Handles action on the Cancel button.
     * Closes the current stage/screen.
     * @param event The action event that triggered this handler.
     */
    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        // Get the current stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Close the current stage
        stage.close();
    }

    /**
     * Handles action on the Add button.
     * Adds a selected part from allPartsTableView to the temporary associated parts list.
     */
    @FXML
    private void handleAddButtonAction() {
        // Get selected part from allPartsTableView
        Part selectedPart = allPartsTableView.getSelectionModel().getSelectedItem();

        // Check if a part is selected
        if (selectedPart != null) {
            // Add the selected part to the temporary associated parts list
            tempAssociatedParts.add(selectedPart);

            // Optional: Refresh associatedPartsTableView
            associatedPartsTableView.refresh();
        }
    }

    /**
     * Handles action on the Remove Associated Part button.
     * Removes a selected part from the associated parts list after confirmation.
     */
    @FXML
    private void handleRemoveAssociatedPartButtonAction() {
        // Get selected part from associatedPartsTableView
        Part selectedPart = associatedPartsTableView.getSelectionModel().getSelectedItem();

        // Check if a part is selected
        if (selectedPart != null) {
            // Confirmation dialog
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Please confirm that you want to remove the selected part");
            alert.setContentText("This action cannot be undone.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                // Remove the selected part from the temporary associated parts list
                tempAssociatedParts.remove(selectedPart);

                // Refresh associatedPartsTableView
                associatedPartsTableView.setItems(tempAssociatedParts);
            }
        }
    }

    /**
     * Searches for part based on either ID or Name. If no parts are found, an alert is shown.
     */
    @FXML
    private void searchPart() {
        String searchString = searchPartTextField.getText();
        ObservableList<Part> filteredParts;

        try {
            int id = Integer.parseInt(searchString);
            Part part = Inventory.lookupPart(id);
            filteredParts = FXCollections.observableArrayList();
            if (part != null) {
                filteredParts.add(part);
            }
        } catch (NumberFormatException e) {
            filteredParts = Inventory.lookupPart(searchString);
        }

        if (!filteredParts.isEmpty()) {
            allPartsTableView.setItems(filteredParts);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Search Result");
            alert.setHeaderText(null);
            alert.setContentText("No parts found");

            alert.showAndWait();
        }
    }

    /**
     * Validates that inventory is within bounds.
     * Shows error message if inventory is not within the min and max range.
     * @return true if inventory is within bounds, false otherwise.
     */
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

    /**
     * Validates the fields.
     * Checks that all required fields have appropriate values.
     * @return true if all fields are valid, false otherwise.
     */
    @FXML
    private boolean validateFields() {
        String name = nameTextField.getText();
        String price = priceTextField.getText();
        String inv = invTextField.getText();
        String max = maxTextField.getText();
        String min = minTextField.getText();

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
        return true;
    }

    /**
     * Displays error message dialog with the given message.
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

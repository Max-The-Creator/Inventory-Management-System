package mkwuntr.c482;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Part;
import model.Product;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Controller class for the Modify Product screen of the application.
 */
public class ModifyProductController {

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

    @FXML
    private Product productToModify;

    /**
     * Initializes the product details to be modified.
     *
     * @param product the product to modify
     */
    @FXML
    public void initializeProduct(Product product) {
        //Set current product
        this.productToModify = product;

        //Automatically set ID
        idTextField.setText(String.valueOf(product.getId()));
        idTextField.setDisable(true);

        //Populate fields with product data
        idTextField.setText(Integer.toString(product.getId()));
        nameTextField.setText(product.getName());
        priceTextField.setText(Double.toString(product.getPrice()));
        invTextField.setText(Integer.toString(product.getStock()));
        maxTextField.setText(Integer.toString(product.getMax()));
        minTextField.setText(Integer.toString(product.getMin()));

        //Populate associated parts
        tempAssociatedParts.addAll(product.getAssociatedParts());
        associatedPartsTableView.setItems(tempAssociatedParts);
        associatedPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Populate allPartsTableView with all available parts
        allPartsTableView.setItems(Inventory.getAllParts());
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Add text field listeners
        searchPartTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                allPartsTableView.setItems(Inventory.getAllParts());
            }
        });
    }

    /**
     * Handles the action of the save button. If all fields are valid, the product is updated.
     */
    @FXML
    public void handleSaveButtonAction() {
        if (validateFields() && validateInventory()) {
            //Update fields
            int id = Integer.parseInt(idTextField.getText());
            String name = nameTextField.getText();
            double price = Double.parseDouble(priceTextField.getText());
            int stock = Integer.parseInt(invTextField.getText());
            int min = Integer.parseInt(minTextField.getText());
            int max = Integer.parseInt(maxTextField.getText());

            Product newProduct = new Product(id, name, price, stock, min, max, FXCollections.observableArrayList(tempAssociatedParts));


            Inventory.updateProduct(Inventory.getAllProducts().indexOf(productToModify), newProduct);

            // Add updated associated parts
            for (Part part : tempAssociatedParts) {
                newProduct.addAssociatedPart(part);
            }

            // Close screen
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();

        }
    }

    /**
     * Handles the action of the cancel button. Closes the current stage.
     *
     * @param event the action event
     */
    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        // Get the current stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Close the current stage
        stage.close();
    }

    /**
     * Handles the action of the add button. Adds a part to the temporary associated parts list.
     */
    @FXML
    private void handleAddButtonAction() {
        // Get selected part from allPartsTableView
        Part selectedPart = allPartsTableView.getSelectionModel().getSelectedItem();

        // Check if a part is selected
        if (selectedPart != null) {
            // Add the selected part to the temporary associated parts list
            tempAssociatedParts.add(selectedPart);

            // Refresh associatedPartsTableView
            associatedPartsTableView.setItems(tempAssociatedParts);
        }
    }

    /**
     * Handles the action of the remove associated part button. Removes a part from the temporary associated parts list.
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
     * Handles the action of the search part button. Searches a part based on either ID or name.
     */
    @FXML
    private void handleSearchPartAction() {
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
     * Validates that the inventory is within the allowed range.
     *
     * @return true if inventory is valid, false otherwise
     */
    @FXML
    private boolean validateInventory() {
        int min = Integer.parseInt(minTextField.getText());
        int max = Integer.parseInt(maxTextField.getText());
        int inv = Integer.parseInt(invTextField.getText());

        if (max < min) {
            displayErrorMessage("Minimum cannot exceed maximum");
            return false;
        }

        if (inv < min || inv > max) {
            displayErrorMessage("Inventory not within allowed range");
            return false;
        }
        return true;
    }

    /**
     * Validates that all fields are correctly filled out.
     *
     * @return true if all fields are valid, false otherwise
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
     * Displays an error message in a dialog box.
     *
     * @param message the message to display
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


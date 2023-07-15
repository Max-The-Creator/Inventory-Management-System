package mkwuntr.c482;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Part;
import model.Product;

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
    public void initialize() {
        // This method is called after all @FXML annotated members have been injected
        // Here you can add specific initialization code if needed
    }

    @FXML
    private void handleSaveButtonAction() {
        // Create a new product based on the text fields
        int id = Integer.parseInt(idTextField.getText());
        String name = nameTextField.getText();
        double price = Double.parseDouble(priceTextField.getText());
        int stock = Integer.parseInt(invTextField.getText());
        int min = Integer.parseInt(minTextField.getText());
        int max = Integer.parseInt(maxTextField.getText());

        Product newProduct = new Product(id, name, price, stock, min, max);

        // Assume that the parts are already associated with the product
        // If not, you would need to associate them here

        // Add product to the inventory or wherever it needs to go...
    }

    @FXML
    private void handleCancelButtonAction() {
        // Code to handle cancel action goes here
    }

    @FXML
    private void handleAddButtonAction() {
        // Code to add a part to the associated parts goes here
        // Typically, you would get the selected part from allPartsTableView and add it to associatedPartsTableView
    }

    @FXML
    private void handleRemoveAssociatedPartButtonAction() {
        // Code to remove a part from the associated parts goes here
        // Typically, you would get the selected part from associatedPartsTableView and remove it
    }
}

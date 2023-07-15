package mkwuntr.c482;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ModifyProductController {

    @FXML
    private TextField searchTextField;

    @FXML
    private TableView<?> firstTableView;

    @FXML
    private TableColumn<?, ?> firstPartIdColumn;

    @FXML
    private TableColumn<?, ?> firstPartNameColumn;

    @FXML
    private TableColumn<?, ?> firstInventoryLevelColumn;

    @FXML
    private TableColumn<?, ?> firstPricePerUnitColumn;

    @FXML
    private Button removeAssociatedPartButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button addButton;

    @FXML
    private Label addProductLabel;

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
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label invLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label maxLabel;

    @FXML
    private Label minLabel;

    @FXML
    private TextField minTextField;

    @FXML
    private TableView<?> secondTableView;

    @FXML
    private TableColumn<?, ?> secondPartIdColumn;

    @FXML
    private TableColumn<?, ?> secondPartNameColumn;

    @FXML
    private TableColumn<?, ?> secondInventoryLevelColumn;

    @FXML
    private TableColumn<?, ?> secondPricePerUnitColumn;

    // Implement your own methods to handle button clicks and data processing.
    @FXML
    public void handleRemoveAssociatedPartButtonAction() {
        // Your code here
    }

    @FXML
    public void handleCancelButtonAction() {
        // Your code here
    }

    @FXML
    public void handleSaveButtonAction() {
        // Your code here
    }

    @FXML
    public void handleAddButtonAction() {
        // Your code here
    }
}

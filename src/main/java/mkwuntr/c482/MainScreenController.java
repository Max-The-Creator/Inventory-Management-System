package mkwuntr.c482;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Part;
import model.Product;
import model.Inventory;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.util.Optional;

public class MainScreenController {

    @FXML
    private TableView<Part> partTableView;

    @FXML
    private TableColumn<Part, Integer> partIdColumn;

    @FXML
    private TableColumn<Part, String> partNameColumn;

    @FXML
    private TableColumn<Part, Integer> partInventoryLevelColumn;

    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    @FXML
    private TableView<Product> productTableView;

    @FXML
    private TableColumn<Product, Integer> productIdColumn;

    @FXML
    private TableColumn<Product, String> productNameColumn;

    @FXML
    private TableColumn<Product, Integer> productInventoryLevelColumn;

    @FXML
    private TableColumn<Product, Double> productPriceColumn;

    @FXML
    private TextField partSearchTextField;

    @FXML
    private TextField productSearchTextField;

    @FXML
    private Button exitButton;

    @FXML
    private Button partAddButton, partModifyButton, partDeleteButton;

    @FXML
    private Button productAddButton, productModifyButton, productDeleteButton;

    // Initialize method to populate table views
    @FXML
    private void initialize() {
        // Define how to populate cells in part table
        partIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        partNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        partInventoryLevelColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());
        partPriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());

        // Populate the part table
        partTableView.setItems(Inventory.getAllParts());

        // Similar code for the product table...
        productIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        productNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        productInventoryLevelColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());
        productPriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());

        // Populate the product table
        productTableView.setItems(Inventory.getAllProducts());

        // Add text field listeners
        partSearchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                partTableView.setItems(Inventory.getAllParts());
            }
        });

        productSearchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                productTableView.setItems(Inventory.getAllProducts());
            }
        });
    }


    // Exits the program
    @FXML
    private void handleExit() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    //Opens Add Part Form
    @FXML
    private void handleAddPart() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addPart.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    //Opens Modify Part Form if there is a part selected
    @FXML
    private void handleModifyPart() throws IOException {
        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("modifyPart.fxml"));
            Scene scene = new Scene(loader.load());
            ModifyPartController controller = loader.getController();
            controller.initializePart(selectedPart);
            stage.setScene(scene);
            stage.show();
        }
    }

    //Deletes selected part
    @FXML
    private void handleDeletePart() {
        // Get the selected part from the TableView
        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();

        // If a part is selected
        if (selectedPart != null) {
            // Show a confirmation dialog
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Part");
            alert.setHeaderText("Are you sure you want to delete this part?");
            alert.setContentText("Click OK to confirm");

            Optional<ButtonType> result = alert.showAndWait();

            // If the user clicked OK, delete the part
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);
                partTableView.setItems(Inventory.getAllParts());  // Refresh the TableView
            }
        } else {
            // If no part is selected, show an error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Part Selected");
            alert.setContentText("Please select a part to delete.");
            alert.showAndWait();
        }
    }

    //Searches for part based on either ID or Name
    @FXML
    private void searchPart() {
        String searchString = partSearchTextField.getText();
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
            partTableView.setItems(filteredParts);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Search Result");
            alert.setHeaderText(null);
            alert.setContentText("No parts found");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleAddProduct() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addProduct.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleModifyProduct() throws IOException {
        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("modifyProduct.fxml"));
            Scene scene = new Scene(loader.load());
            ModifyProductController controller = loader.getController();
            //controller.setProduct(selectedProduct);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void searchProduct() {
        String searchString = productSearchTextField.getText();
        ObservableList<Product> filteredProducts;

        try {
            int id = Integer.parseInt(searchString);
            Product product = Inventory.lookupProduct(id);
            filteredProducts = FXCollections.observableArrayList();
            if (product != null) {
                filteredProducts.add(product);
            }
        } catch (NumberFormatException e) {
            filteredProducts = Inventory.lookupProduct(searchString);
        }

        if (!filteredProducts.isEmpty()) {
            productTableView.setItems(filteredProducts);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Search Result");
            alert.setHeaderText(null);
            alert.setContentText("No products found");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleDeleteProduct() {
        // Get the selected product from the TableView
        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();

        // If a product is selected
        if (selectedProduct != null) {
            // Show a confirmation dialog
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Product");
            alert.setHeaderText("Are you sure you want to delete this product?");
            alert.setContentText("Click OK to confirm");

            Optional<ButtonType> result = alert.showAndWait();

            // If the user clicked OK, delete the product
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deleteProduct(selectedProduct);
                productTableView.setItems(Inventory.getAllProducts());  // Refresh the TableView
            }
        } else {
            // If no product is selected, show an error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Product Selected");
            alert.setContentText("Please select a product to delete.");
            alert.showAndWait();
        }
    }



}

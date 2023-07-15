package mkwuntr.c482;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Part;
import model.Product;

import java.io.IOException;

public class MainScreenController {

    @FXML
    private TableView<Part> partTableView;

    @FXML
    private TableView<Product> productTableView;

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
        // Populate the TableView
        // You will need to call the methods that get your Parts and Products data and add them to the table views
        // partTableView.setItems(getPartsData());
        // productTableView.setItems(getProductsData());
    }

    // Add functionality to your buttons
    @FXML
    private void handleExit() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleAddPart() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addPart.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleModifyPart() throws IOException {
        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("modifyPart.fxml"));
            Scene scene = new Scene(loader.load());
            ModifyPartController controller = loader.getController();
        //    controller.setPart(selectedPart);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void handleDeletePart() {
        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            // Call your delete method here
            // deletePart(selectedPart);
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
        //    controller.setProduct(selectedProduct);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void handleDeleteProduct() {
        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            // Call your delete method here
            // deleteProduct(selectedProduct);
        }
    }
}

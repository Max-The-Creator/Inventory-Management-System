/**
 * Handle the action of the save button.
 * <p>
 * RUNTIME ERROR: The program would crash if the inventory field was left blank. The error was
 * corrected by introducing a validation method that checks if the fields are not empty or
 * invalid before parsing them into integers.
 * </p>
 *
 * @see #validateFields()
 * @see #validateInventory()
 */

/**
 * The main class for the inventory management application.
 * <p>
 * FUTURE ENHANCEMENT: Consider the addition of a feature to search for parts based on additional
 * criteria like price or date of addition. This will improve the user's ability to find parts
 * quickly and efficiently, especially when the inventory size grows larger.
 * </p>
 */

package mkwuntr.c482;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
module mkwuntr.c482 {
    requires javafx.controls;
    requires javafx.fxml;
            
    opens model to javafx.base;
    opens mkwuntr.c482 to javafx.fxml;
    exports mkwuntr.c482;
}
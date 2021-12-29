module com.oceanedit {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.oceanedit to javafx.fxml;
    exports com.oceanedit;
}
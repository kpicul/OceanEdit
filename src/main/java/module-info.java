module com.oceanedit {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.oceanedit to javafx.fxml;
    exports com.oceanedit;
    exports com.oceanedit.Controllers;
    opens com.oceanedit.Controllers to javafx.fxml;
}
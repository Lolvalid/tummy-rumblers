module com.example.tummyrumblers {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tummyrumblers to javafx.fxml;
    exports com.example.tummyrumblers;
}
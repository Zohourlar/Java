module com.example.porjetwass {
    requires javafx.controls;
    requires javafx.fxml;
    requires emoji.java;


    opens com.example.porjetwass to javafx.fxml;
    exports com.example.porjetwass;
}
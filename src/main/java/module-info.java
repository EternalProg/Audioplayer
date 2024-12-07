module com.dmitrii.audioplayer.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.dmitrii.audioplayer.demo to javafx.fxml;
    exports com.dmitrii.audioplayer.demo;
}
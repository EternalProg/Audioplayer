module com.dmitrii.audioplayer {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.dmitrii.audioplayer;
    opens com.dmitrii.audioplayer.controller to javafx.fxml;

    exports com.dmitrii.audioplayer;
    exports com.dmitrii.audioplayer.controller;
}
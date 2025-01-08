module com.dmitrii.audioplayer {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires jakarta.persistence;

    opens com.dmitrii.audioplayer to spring.core, spring.context;
    opens com.dmitrii.audioplayer.controller to javafx.fxml;
    opens com.dmitrii.audioplayer.model to spring.core, spring.context;

    exports com.dmitrii.audioplayer;
    exports com.dmitrii.audioplayer.controller;
}
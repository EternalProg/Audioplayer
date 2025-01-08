package com.dmitrii.audioplayer.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class AuthorizationController {
    // Fields
    @FXML
    private PasswordField loginPasswordField;

    @FXML
    private TextField loginUsernameField;

    @FXML
    private TextField regUsernameField;

    @FXML
    private PasswordField regPasswordField;

    @FXML
    private PasswordField regPasswordConfField;

    // Buttons
    @FXML
    private Button loginBtn;

    @FXML
    private Button toLoginBtn;

    @FXML
    private Button toRegisterBtn;

    // Panes
    @FXML
    private Pane loginPane;

    @FXML
    private Pane registerPane;

    @FXML
    public void initialize() {
        loginPane.setVisible(true);
        loginPane.setManaged(true);
        registerPane.setVisible(false);
        registerPane.setManaged(false);
    }

    public void togglePanes(ActionEvent event) {
        if (loginPane.isVisible()) {
            registerPane.setVisible(true);
            registerPane.setManaged(true);
            loginPane.setVisible(false);
            loginPane.setManaged(false);
        } else {
            loginPane.setVisible(true);
            loginPane.setManaged(true);
            registerPane.setVisible(false);
            registerPane.setManaged(false);
        }
    }

    public void login(ActionEvent event) {
        // Перевірка чи є такий юзер уже в базі

        boolean succesfullyLogin = false;
        // Перевірка пароля ні мінімум 6 символів
        if (regPasswordConfField.getText().isEmpty()) {
            System.out.println("Підтвердіть пароль");
        } else if (regPasswordField.getText().length() < 6) {
            System.out.println("Пароль повинен містити мінімум 6 символів");
        } else if (!regPasswordField.getText().equals(regPasswordConfField.getText())) {
            System.out.println("Паролі не співпадають");
        } else {
            // Запис в базу
        }
    }

    public void register(ActionEvent event) {

    }

}

package org.gift.terminal.controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.gift.terminal.SceneName;
import org.gift.terminal.ScreenController;
import org.gift.terminal.backoffice.core.impl.AdminImpl;
import org.gift.terminal.backoffice.core.manager.AdminManager;
import org.gift.terminal.common.constants.AccessLevel;
import org.gift.terminal.common.constants.UserStatus;
import org.gift.terminal.common.dto.UserDto;

public class RetailerController {
    private static final AdminManager admin = new AdminImpl();

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private CheckBox isAdmin;

    @FXML
    public void register() {
        if (validate()) {
            var user = new UserDto(email.getText(), password.getText(), 1L, 1L,
                    isAdmin.isSelected() ? AccessLevel.ADMIN : AccessLevel.MANAGER, UserStatus.INACTIVE);
            admin.createRetailer(user);
        }
    }

    @FXML
    public void back() {
        ScreenController.activate(SceneName.ADMIN.getValue());
    }

    @FXML
    public void edit() {
        if (validate()) {
            var user = new UserDto(email.getText(), password.getText(), 1L, 1L,
                    isAdmin.isSelected() ? AccessLevel.ADMIN : AccessLevel.MANAGER, UserStatus.INACTIVE);
            admin.editRetailer(user);
        }
    }

    private boolean validate() {
        if (email == null || password == null) {
            return false;
        }
        if ("".equals(email.getText()) || "".equals(password.getText())) {
            return false;
        }
        if (!email.getText().matches("[A-Za-z][A-Za-z0-9].+@[a-z].+[.][a-z].+")) {
            return false;
        }
        return true;
    }
}

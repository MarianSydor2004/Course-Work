package org.gift.terminal.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.gift.terminal.backoffice.core.impl.RegistrationImpl;
import org.gift.terminal.backoffice.core.manager.RegistrationManager;
import org.gift.terminal.common.constants.AccessLevel;
import org.gift.terminal.common.constants.UserStatus;
import org.gift.terminal.common.dto.UserDto;
import org.gift.terminal.SceneName;
import org.gift.terminal.ScreenController;

public class MenuController {
    private static final RegistrationManager registration = new RegistrationImpl();

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    public void logIn() {
          ScreenController.activate(SceneName.MANAGER.getValue());
      //  ScreenController.activate(SceneName.ADMIN.getValue());
//        if (validate()) {
//            var user = registration.logIn(formUserDto());
//            if (user != null) {
//                ScreenController.setUser(user);
//                email.setText("");
//                password.setText("");
//                switch (user.getAccessLevel()) {
//                    case ADMIN:
//                        ScreenController.activate(SceneName.ADMIN.getValue());
//                        break;
//                    case MANAGER:
//
//                        break;
//                    default:
//                }
//            }
//        }
    }

    private UserDto formUserDto() {
        return new UserDto(email.getText(), password.getText(), 0L, 0L, AccessLevel.UNKNOWN, UserStatus.ACTIVE);
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

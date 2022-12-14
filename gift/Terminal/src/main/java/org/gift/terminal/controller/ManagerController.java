package org.gift.terminal.controller;

import javafx.fxml.FXML;
import org.gift.terminal.SceneName;
import org.gift.terminal.ScreenController;

public class ManagerController {

    @FXML
    public void box() {
        ScreenController.activate(SceneName.BOX.getValue());
    }

    @FXML
    public void chocolate() {
        ScreenController.activate(SceneName.CHOCOLATE.getValue());
    }

    @FXML
    public void back() {
        ScreenController.activate(SceneName.MENU.getValue());
    }
}

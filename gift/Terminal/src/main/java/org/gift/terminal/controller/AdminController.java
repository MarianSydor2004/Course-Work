package org.gift.terminal.controller;

import javafx.fxml.FXML;
import org.gift.terminal.SceneName;
import org.gift.terminal.ScreenController;

public class AdminController {

    @FXML
    public void createRetailer() {
        ScreenController.activate(SceneName.CREATE_RETAILER.getValue());
    }

    @FXML
    public void editRetailer() {
        ScreenController.activate(SceneName.EDIT_RETAILER.getValue());
    }

    @FXML
    public void createShop() {
        ScreenController.activate(SceneName.CREATE_SHOP.getValue());
    }

    @FXML
    public void logOut() {
        ScreenController.activate(SceneName.MENU.getValue());
    }
}

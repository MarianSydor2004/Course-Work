package org.gift.terminal.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.gift.terminal.SceneName;
import org.gift.terminal.ScreenController;
import org.gift.terminal.backoffice.core.impl.AdminImpl;
import org.gift.terminal.backoffice.core.manager.AdminManager;
import org.gift.terminal.common.dto.ShopDto;

public class ShopController {
    private static final AdminManager admin = new AdminImpl();

    @FXML
    private TextField brand;

    @FXML
    private TextArea description;

    @FXML
    public void register() {
        if (validate()) {
            var shop = new ShopDto(0L, brand.getText(), description.getText());
            admin.createShop(shop);
        }
    }

    @FXML
    public void back() {
        ScreenController.activate(SceneName.ADMIN.getValue());
    }

    private boolean validate() {
        if (brand == null || description == null) {
            return false;
        }
        if ("".equals(brand.getText()) || "".equals(description.getText())) {
            return false;
        }
        return true;
    }
}

package org.gift.terminal.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.gift.terminal.SceneName;
import org.gift.terminal.ScreenController;
import org.gift.terminal.backoffice.core.impl.BoxImpl;
import org.gift.terminal.backoffice.core.manager.ItemManager;
import org.gift.terminal.common.constants.ItemCategory;
import org.gift.terminal.common.dto.items.BoxDto;
import org.gift.terminal.common.dto.items.ItemCommonDto;

public class BoxController {
    private final ItemManager<BoxDto> itemManager = new BoxImpl();

    @FXML
    private TextArea box;

    @FXML
    private TextField weight;

    @FXML
    private TextField code;

    @FXML
    private TextField colorCode;

    @FXML
    private TextField scale;

    @FXML
    private TextField width;

    @FXML
    private TextField length;

    @FXML
    private TextField color;

    @FXML
    private TextField price;

    @FXML
    private TextField brand;

    @FXML
    private TextField label;

    @FXML
    private TextArea description;

    @FXML
    public void updateList() {
        box.setText(itemManager.view().toString());
    }

    @FXML
    public void create() {
        itemManager.create(formDto());
    }

    @FXML
    public void edit() {
        itemManager.create(formDto());
    }

    @FXML
    public void delete() {
        itemManager.delete(formDto());
    }

    private BoxDto formDto() {
        return new BoxDto(Double.parseDouble(weight.getText()), code.getText(), Integer.parseInt(colorCode.getText(), 16),
                Double.parseDouble(scale.getText()), Double.parseDouble(width.getText()), Double.parseDouble(length.getText()), color.getText(),
                new ItemCommonDto(Double.parseDouble(price.getText()), true, ItemCategory.BOX, brand.getText(),
                label.getText(), description.getText()));
    }

    @FXML
    public void back() {
        ScreenController.activate(SceneName.MANAGER.getValue());
    }
}

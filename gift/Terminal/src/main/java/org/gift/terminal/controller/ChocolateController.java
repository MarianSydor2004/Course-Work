package org.gift.terminal.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.gift.terminal.SceneName;
import org.gift.terminal.ScreenController;
import org.gift.terminal.backoffice.core.impl.ChocolateImpl;
import org.gift.terminal.backoffice.core.manager.ItemManager;
import org.gift.terminal.common.constants.ItemCategory;
import org.gift.terminal.common.dto.items.ChocolateDto;
import org.gift.terminal.common.dto.items.ItemCommonDto;

import java.util.List;

public class ChocolateController {

    private final ItemManager<ChocolateDto> itemManager = new ChocolateImpl();

    @FXML
    private TextArea chocolate;

    @FXML
    private TextField weight;

    @FXML
    private TextField code;

    @FXML
    private TextField chocolateType;

    @FXML
    private TextField sugar;

    @FXML
    private TextField component;

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
        chocolate.setText(itemManager.view().toString());
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

    private ChocolateDto formDto() {
        return new ChocolateDto(Double.parseDouble(weight.getText()), code.getText(), Double.parseDouble(sugar.getText()), chocolateType.getText(), List.of(component.getText()),
                new ItemCommonDto(Double.parseDouble(price.getText()), true, ItemCategory.BOX, brand.getText(),
                        label.getText(), description.getText()));
    }

    @FXML
    public void back() {
        ScreenController.activate(SceneName.MANAGER.getValue());
    }
}

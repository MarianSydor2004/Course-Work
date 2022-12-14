package org.gift.terminal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

public class Gift extends Application {
    private static final Logger LOG = LogManager.getLogger();
    public static void main(String... args) {
        launch(args);
    }

    //Every controller has a set of command which is related to one screen it if command pattern
    @Override
    public void start(Stage stage) throws IOException {
        LOG.info("Program started");
        stage.setTitle("Gift");
        stage.setWidth(1060);
        stage.setHeight(620);
        stage.getIcons().add(getImage());
        initControllers();
        stage.setScene(ScreenController.getScene());
        stage.show();
    }

    private void initControllers() throws IOException {
        ScreenController.addScene(SceneName.MENU.getValue(),
                FXMLLoader.load(getClass().getResource(SceneName.MENU.getValue() + ".fxml")));
        ScreenController.addScene(SceneName.ADMIN.getValue(),
                FXMLLoader.load(getClass().getResource(SceneName.ADMIN.getValue() + ".fxml")));
        ScreenController.addScene(SceneName.MANAGER.getValue(),
                FXMLLoader.load(getClass().getResource(SceneName.MANAGER.getValue() + ".fxml")));
        ScreenController.addScene(SceneName.CREATE_RETAILER.getValue(),
                FXMLLoader.load(getClass().getResource(SceneName.CREATE_RETAILER.getValue() + ".fxml")));
        ScreenController.addScene(SceneName.EDIT_RETAILER.getValue(),
                FXMLLoader.load(getClass().getResource(SceneName.EDIT_RETAILER.getValue() + ".fxml")));
        ScreenController.addScene(SceneName.CREATE_SHOP.getValue(),
                FXMLLoader.load(getClass().getResource(SceneName.CREATE_SHOP.getValue() + ".fxml")));
        ScreenController.addScene(SceneName.BOX.getValue(),
                FXMLLoader.load(getClass().getResource(SceneName.BOX.getValue() + ".fxml")));
        ScreenController.addScene(SceneName.CHOCOLATE.getValue(),
                FXMLLoader.load(getClass().getResource(SceneName.CHOCOLATE.getValue() + ".fxml")));
        ScreenController.activate(SceneName.MENU.getValue());
    }

    private Image getImage() {
        InputStream icon = getClass().getResourceAsStream("img/icon.png");
        Image image = null;
        if (icon != null) {
            image = new Image(icon);
        }
        return image;
    }
}

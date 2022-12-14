package org.gift.terminal;

import javafx.scene.Parent;
import javafx.scene.Scene;
import org.gift.terminal.common.dto.UserDto;

import java.util.HashMap;

public final class ScreenController {
    private static final HashMap<String, Parent> screenMap = new HashMap<>();
    private static Scene scene;
    private static UserDto user;

    private ScreenController() {
    }

    public static void setUser(UserDto user) {
        ScreenController.user = user;
    }

    public static UserDto getUser() {
        return user;
    }

    public static Scene getScene() {
        return scene;
    }

    public static void addScene(String name, Parent parent) {
        screenMap.put(name, parent);
    }

    public static void removeScene(String name) {
        screenMap.remove(name);
    }

    public static void activate(String name) {
        var parent = screenMap.get(name);
        if (scene == null) {
            scene = new Scene(parent);
        }
        scene.setRoot(parent);
    }
}

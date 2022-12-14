package org.gift.terminal;

public enum SceneName {
    MENU("menu"),
    ADMIN("admin"),
    MANAGER("retailer"),
    CREATE_RETAILER("createRetailer"),
    EDIT_RETAILER("editRetailer"),
    CREATE_SHOP("createShop"),
    BOX("box"),
    CHOCOLATE("chocolate");

    private final String value;

    SceneName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

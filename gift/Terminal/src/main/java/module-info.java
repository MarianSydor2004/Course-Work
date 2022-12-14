module org.gift.terminal {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires java.sql;
    requires static lombok;

    opens org.gift.terminal to javafx.fxml;
    exports org.gift.terminal;
    opens org.gift.terminal.controller to javafx.fxml;
    exports org.gift.terminal.controller;
}
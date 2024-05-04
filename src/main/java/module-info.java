module org.culturetripp.tonactiviter {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires java.desktop;
    requires java.logging;
    requires java.prefs;
    requires java.xml;


    opens org.culturetripp.tonactiviter to javafx.fxml;
    exports org.culturetripp.tonactiviter;
}

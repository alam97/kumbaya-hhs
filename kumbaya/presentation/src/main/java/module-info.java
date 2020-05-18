module org.kumbaya.hhs {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires logic;
    opens org.kumbaya.hhs to javafx.fxml;
    exports org.kumbaya.hhs;
}
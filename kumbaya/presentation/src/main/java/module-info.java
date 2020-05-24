module org.kumbaya.hhs {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires logic;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    opens org.kumbaya.hhs to javafx.fxml;
    exports org.kumbaya.hhs;
}
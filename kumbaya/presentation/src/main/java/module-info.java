module org.kumbaya.hhs {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.kumbaya.hhs to javafx.fxml;
    exports org.kumbaya.hhs;
}
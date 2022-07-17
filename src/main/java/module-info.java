module com.ifc21.xve.hitungin.hitungin {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ifc21.xve.hitungin.hitungin to javafx.fxml;
    exports com.ifc21.xve.hitungin.hitungin;
}
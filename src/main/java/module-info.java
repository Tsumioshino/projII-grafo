module com.projII_grafo {
    requires java.sql;
    requires java.desktop;
    requires javafx.fxml;
    requires transitive javafx.controls;
    requires transitive javafx.graphics;
    
    exports com.projII_grafo;
    opens com.projII_grafo to javafx.fxml;

}

module edu.guilford {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.jsoup;
    requires javafx.graphics;

    opens edu.guilford to javafx.fxml;
    exports edu.guilford;
}

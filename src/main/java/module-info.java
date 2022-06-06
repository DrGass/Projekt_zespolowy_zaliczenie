module com.gambler.projekt_zespolowy_zaliczenie {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;


    opens com.gambler.projekt_zespolowy_zaliczenie to javafx.fxml;
    exports com.gambler.projekt_zespolowy_zaliczenie;
}
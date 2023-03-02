module com.example.demojavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires TrayNotification;
    requires mysql.connector.j;


    opens com.example.demojavafx to javafx.fxml;
    exports com.example.demojavafx;
}
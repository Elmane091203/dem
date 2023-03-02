package com.example.demojavafx;

import com.example.demojavafx.tools.Notification;
import com.example.demojavafx.tools.Outils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ConnexionController {
    private IUser userDao = new UserImpl();
    @FXML
    private Button connexionBtn;

    @FXML
    private TextField emailTfd;

    @FXML
    private PasswordField passwordTfd;

    @FXML
    void getLogin(ActionEvent event) throws IOException {
        String email = emailTfd.getText();
        String password = passwordTfd.getText();
        if (email.equals("") || password.equals("")) {
            Notification.NotifError("Warning", "Champs Obligatoires");
        } else {
            User user = userDao.seConnecter(email, password);
            if (user == null)
                Notification.NotifError("Erreur", "Email et/ou password Incorrects");
            else {
                try {
                    Notification.NotifSuccess("Success", "Connexion Reussie");
                    Outils.load(event, "CRUD", "/Fxml/Etudiant.fxml");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}


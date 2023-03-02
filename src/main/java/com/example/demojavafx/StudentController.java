package com.example.demojavafx;

import com.example.demojavafx.tools.Notification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentController {

    int index, id;

    @FXML
    private TableView<Student> table;

    @FXML
    private TextField txtName, txtMatiere;

    @FXML
    private TableColumn<Student, String> idCol, nameCol, matiereCol;

    private IStudent studentDao = new StudentImpl();

    @FXML
    private Button addBtn, updateBtn, deleteBtn;

    @FXML
    void Add(ActionEvent actionEvent) {
        String name, matiere;

        name = txtName.getText();
        matiere = txtMatiere.getText();

        if (name.isEmpty() || matiere.isEmpty()) {
            //show error alert
            Notification.NotifError( "Feilds are empty", "Please fill all the feilds");
        } else {
            Student student = new Student();
            student.setName(name);
            student.setMatiere(matiere);
            if(studentDao.add(student) == 1){
                Notification.NotifSuccess("Success", "Etudiant ajouté avec succes");
                reset();
                Table();
            }else{
                Notification.NotifError("Erreur","Insertion échouée !");
            }

        }
    }


    @FXML
    void Update(ActionEvent actionEvent) {
        //String name, matiere;
        index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            Notification.NotifError("Error", "Please select a record to update");
            return;
        }
        Student student = new Student();
        id = Integer.parseInt(String.valueOf(table.getItems().get(index).getId()));
        student.setId(String.valueOf(id));
        student.setName(txtName.getText());
        student.setMatiere(txtMatiere.getText());
        if(studentDao.update(student) == 1){
            Notification.NotifSuccess("Success", "Etudiant modifié avec succes");
            Table();
        }else{
            Notification.NotifError("Erreur","Modification échouée !");
        }


    }

    @FXML
    void Delete(ActionEvent actionEvent) {

        index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            Notification.NotifError("Erreur", "Please select a record to update");
            return;
        }
        id = Integer.parseInt(String.valueOf(table.getItems().get(index).getId()));
        if(studentDao.delete(id) == 1){
            Notification.NotifSuccess("Success", "Etudiant supprimé avec succes");
            reset();
            Table();
        }else{
            Notification.NotifError("Erreur","Suppression échouée !");
        }

    }


    public void reset() {
        txtName.setText("");
        txtMatiere.setText("");
    }

    public void Table() {
        //Connect();
        ObservableList<Student> students = FXCollections.observableArrayList();
        students.addAll(studentDao.list());

        TableColumn<Student, String> idCol = new TableColumn<>("id");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Student, String> nameCol = new TableColumn<>("name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Student, String> matiereCol = new TableColumn<>("matiere");
        matiereCol.setCellValueFactory(new PropertyValueFactory<>("matiere"));

        // Initialiser la table avec les colonnes et les données
        TableView<Student> table = new TableView<>();
        table.getColumns().addAll(idCol, nameCol, matiereCol);
        table.setItems(students);

        //display selected row data in textfields
        table.setRowFactory(tv -> {
            TableRow<Student> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    Student rowData = row.getItem();
                    index = row.getIndex();
                    id = Integer.parseInt(rowData.getId());
                    txtName.setText(rowData.getName());
                    txtMatiere.setText(rowData.getMatiere());
                }
            });
            return row;
        });


    }
}

package com.example.demojavafx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {

    private final SimpleStringProperty id;
    private final StringProperty name;
    private final StringProperty matiere;

    public Student() {
        id = new SimpleStringProperty(this, "id");
        name = new SimpleStringProperty(this, "name");
        matiere = new SimpleStringProperty(this, "matiere");
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty matiereProperty() {
        return matiere;
    }

    public String getMatiere() {
        return matiere.get();
    }

    public void setMatiere(String matiere) {
        this.matiere.set(matiere);
    }

}

package com.example.demojavafx;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentImpl implements IStudent {
    DBConnexion db = new DBConnexion();
    private int ok;
    private ResultSet rs;
    @Override
    public int add(Student s) {
        String sql = "INSERT INTO student VALUES(NULL, ?, ?)";
        try {
            //Initialisation de la requete
            db.initPrepar(sql);
            //Passage de valeurs
            db.getPstm().setString(1, s.getName());
            db.getPstm().setString(2, s.getMatiere());
            //Exécution de la requete
            ok = db.executeMaj();
            //Fermeture de la connexion
            db.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ok;
    }

    @Override
    public int update(Student s) {
        String sql = "UPDATE student SET name=?, matiere=? WHERE id=?";
        try{
            db.initPrepar(sql);
            db.getPstm().setString(1, s.getName());
            db.getPstm().setString(2, s.getMatiere());
            db.getPstm().setInt(5, Integer.parseInt(s.getId()));
            ok = db.executeMaj();
            db.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ok;
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM student WHERE id=?";
        try{
            db.initPrepar(sql);
            db.getPstm().setInt(1, id);
            ok = db.executeMaj();
            db.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ok;
    }

    @Override
    public List<Student> list() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student ORDER BY name ASC";
        try{
            db.initPrepar(sql);
            rs = db.executeSelect();
            while(rs.next()){
                Student student = new Student();
                student.setId(String.valueOf(rs.getInt(1)));
                student.setName(rs.getString(2));
                student.setMatiere(rs.getString(3));
                students.add(student);
            }
            db.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public Student get(int id) {
        Student student = null;
        String sql = "SELECT * FROM student WHERE id=?";
        try {
            db.initPrepar(sql);
            db.getPstm().setInt(1, id);
            rs = db.executeSelect();
            if(rs.next()){
                student = new Student();
                student.setId(String.valueOf(rs.getInt("id")));
                student.setName(rs.getString("name"));
                student.setMatiere(rs.getString("matiere"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return student;
    }
}

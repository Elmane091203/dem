package com.example.demojavafx;

import java.sql.ResultSet;

public class UserImpl implements IUser{
    DBConnexion db = new DBConnexion();
    @Override
    public User seConnecter(String  email, String password){
        User user = null;
        String sql = "SELECT * FROM user WHERE email = ? and password = ?";
        try{
            db.initPrepar(sql);
            db.getPstm().setString(1,email);
            db.getPstm().setString(2,password);
            ResultSet rs = db.executeSelect();
            while (rs.next()){
                user = new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)
                );
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
}

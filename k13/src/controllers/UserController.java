/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.User;
import utils.DatabaseUtils;
import java.util.ArrayList;

/**
 *
 * @author Hanny Yosephine
 * @author Corry Betriks Sitorus
 */
public class UserController {
    private DatabaseUtils conn;
    private ResultSet rs;
    private PreparedStatement pre;

    public UserController() {
        conn = new DatabaseUtils();
    }
    
    public Boolean register(User user){
        ArrayList<String> data = new ArrayList<>();
        data.add(user.getNama());
        data.add(user.getUsername());
        data.add(user.getKelas());
        data.add(user.getPassword());
        return conn.execute("INSERT INTO mahasiswa (nama, username, kelas, password) VALUES (?, ?, ?, ?)", data);
    }
    
    public User login(String username, String password) {
        ArrayList<String> data = new ArrayList<>();
        data.add(username);
        data.add(password);
        User result = null;
        
        rs = conn.executeSelect("SELECT id, nama, kelas FROM mahasiswa WHERE username = ? AND password = ? LIMIT 1", data);
        if (rs != null) {
            try {
                if(rs.next()) {
                    result = new User(Integer.valueOf(rs.getString(1)), 
                            rs.getString(2), 
                            rs.getString(3),
                            username,
                            password
                    );
                }
            }catch(SQLException e){}
        }
        return result;
    }
    
    public Boolean loginAdmin(String username, String password) {
        ArrayList<String> data = new ArrayList<>();
        data.add(username);
        data.add(password);
        rs = conn.executeSelect("SELECT * FROM admin WHERE username = ? AND password = ? LIMIT 1", data);
        
        if (rs != null) {
            try {
                return rs.next();
            }catch(SQLException e){}
        }
        return false;
    }
    
    public void close() {
        conn.stop();
    }
}

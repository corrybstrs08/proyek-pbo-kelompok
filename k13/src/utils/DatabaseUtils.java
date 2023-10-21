/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import com.mysql.jdbc.MySQLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Database;

/**
 *
 * @author Hanny Yosephine
 * @author Corry Betriks Sitorus
 */
public class DatabaseUtils {
    private Database config;
    private Connection dbConn;
    private Statement dbStmnt;
    private ResultSet dbRs;
    private PreparedStatement dbPre;

    public DatabaseUtils() {
        this.config = new Database(
                "localhost",
                3306,
                "root",
                "ifs21047",
                "k13"
        );
        try {
            dbConn = createConn();
            dbStmnt = dbConn.createStatement();
        } catch(SQLException e) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, e);
        }
        
        try {
            Class.forName(ConstUtil.DRIVER_MYSQL).newInstance();
        } catch(ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            ToolsUtil.Log("ERROR", ToolsUtil.errorMessage(100));
        }
    }
    
    public Connection getConnection() {
        return dbConn;
    }
    
    public Connection createConn() throws SQLException {
        if(this.config.getPort() > 0) {
            return DriverManager.getConnection("jdbc:mysql:" + "//"+ this.config.getHost() + ":"
            + this.config.getPort() + "/" + this.config.getDatabase()+ "?user=" + this.config.getUsername()
            + "&password=" + this.config.getPassword());
        } else {
           return DriverManager.getConnection("jdbc:mysql:" + "//"+ this.config.getHost() + "/"
            + this.config.getDatabase()+ "?user=" + this.config.getUsername()
            + "&password=" + this.config.getPassword());
        }
    }
    
    public boolean stop() {
        try{
            dbStmnt.close();
            dbConn.close();
            return true;
        }catch(SQLException e) {
            return false;
        }
    }
    
    public ResultSet executeSelect(String query) {
        try{
            dbRs = dbStmnt.executeQuery(query);
            return dbRs;
        }catch(SQLException e){
            return null;
        }
    }
    
    public boolean execute(String query){
        try{
            dbStmnt.executeUpdate(query);
            return true;
        }catch(SQLException e){
            return false;
        }
    }
    
    public ResultSet executeSelect(String query, ArrayList<String> data){
        try{
            dbPre = (PreparedStatement) dbConn.prepareStatement(query);
            for(int i=0; i<data.size(); i++){
                dbPre.setString((i+1), data.get(i));
            }
            dbRs = dbPre.executeQuery();
            return dbRs;
        }catch(SQLException e){
            return null;
        }
    }
    
    public boolean execute(String query, ArrayList<String> data){
        try{
            dbPre = (PreparedStatement) dbConn.prepareStatement(query);
            for(int i = 0; i<data.size(); i++){
                dbPre.setString((i+1), data.get(i));
            }
            dbPre.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println(e);
            return false;
        }
    }
}

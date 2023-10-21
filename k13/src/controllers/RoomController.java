/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.RequestRoom;
import models.Room;
import models.RoomToday;
import models.User;
import utils.DatabaseUtils;

/**
 *
 * @author Hanny Yosephine
 * @author Corry Betriks Sitorus
 */
public class RoomController {
    private DatabaseUtils conn;
    private ResultSet rs;
    private PreparedStatement pre;

    public RoomController() {
        conn = new DatabaseUtils();
    }
    
    public ArrayList<Room> getAllRoom() {
        ArrayList<Room> result = new ArrayList<>();
        rs = conn.executeSelect("SELECT id, nama_ruangan FROM ruangan");
        if (rs != null){
            try{
                while(rs.next()) {
                    Room room = new Room(
                            Integer.valueOf(rs.getString(1)), 
                            rs.getString(2)
                    );
                    result.add(room);
                }
            }catch(SQLException e){}
        }
        return result;
    }
    
    public ArrayList<RoomToday> getTodayList(String date) {
        ArrayList<RoomToday> result = new ArrayList<>();
        ArrayList<String> data = new ArrayList<>();
        data.add(date);
        rs = conn.executeSelect("SELECT ruangan.nama_ruangan, b.status, b.kelas FROM ruangan LEFT JOIN (SELECT request_room.id, id_ruangan, STATUS, mahasiswa.kelas FROM request_room JOIN mahasiswa ON id_mahasiswa = mahasiswa.id WHERE request_room.time = ? ORDER BY STATUS LIMIT 1) b ON ruangan.id = b.id_ruangan", data);
        if (rs != null) {
            try{
                while(rs.next()) {
                    String status, peminjam;
                    if(rs.getString(2) == null || rs.getString(2).equals("request")){
                        status = "Tersedia";
                        peminjam = "-";
                    }else {
                        status = "Terisi";
                        peminjam = rs.getString(3);
                    }
                    RoomToday room = new RoomToday(
                            rs.getString(1),
                            status,
                            peminjam
                    );
                    System.out.println(room.getStatus());
                    result.add(room);
                }
            }catch(SQLException e){}
        }
        return result;
    }
    
    public ArrayList<RequestRoom> getRequestList() {
        ArrayList<RequestRoom> result = new ArrayList<>();
        rs = conn.executeSelect("SELECT r.id, r.id_ruangan, ru.nama_ruangan, r.time, m.nama, m.kelas, r.reason FROM request_room r LEFT JOIN mahasiswa m ON r.id_mahasiswa = m.id JOIN ruangan ru ON r.id_ruangan = ru.id WHERE r.status = 'request' ORDER BY TIME ASC");
        if (rs != null) {
            try {
                while(rs.next()) {
                    RequestRoom room = new RequestRoom(
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7)
                    );
                    result.add(room);
                }
            }catch(SQLException e){
                System.out.println("SQL " + e);
            }
        }
        return result;
    }
    
    public Boolean isValidDate(String ruangan, String date) {
        ArrayList<String> data = new ArrayList<>();
        data.add(ruangan);
        data.add(date);
        data.add("accept");
        
        rs = conn.executeSelect("SELECT id FROM request_room WHERE id_ruangan = ? AND time = ? AND status = ? LIMIT 1", data);
        if (rs != null) {
            try{
                if(rs.next()) {
                    return false;
                }
            }catch(SQLException e) {}
        }
        return true;
    }
    
    public Boolean checkIfAlready(String id, String idRuangan, String date) {
        ArrayList<String> data = new ArrayList<>();
        data.add(id);
        data.add(idRuangan);
        data.add(date);
        
        rs = conn.executeSelect("SELECT id FROM request_room WHERE id_mahasiswa = ? AND id_ruangan = ? AND time = ? LIMIT 1", data);
        if (rs != null) {
            try{
                if(rs.next()) {
                    return false;
                }
            }catch(SQLException e) {}
        }
        return true;
    }
    
    public Boolean requestRoom(String idRuangan, String idMahasiswa, String date, String reason) {
        ArrayList<String> data = new ArrayList<>();
        data.add(idRuangan);
        data.add(idMahasiswa);
        data.add(date);
        data.add("request");
        data.add(reason);
        return conn.execute("INSERT INTO request_room(id_ruangan, id_mahasiswa, time, status, reason) VALUES(?, ?, ?, ?, ?)", data);
    }
    
    public Boolean rejectRequest(String id) {
        ArrayList<String> data = new ArrayList<>();
        data.add(id);
        return conn.execute("UPDATE request_room SET status = 'reject' WHERE id = ?", data);
    }
    
    public Boolean acceptRequest(String id, String idRuangan, String date) {
        ArrayList<String> data = new ArrayList<>();
        data.add(id);
        data.add(idRuangan);
        data.add(date);
        return conn.execute("UPDATE request_room SET STATUS = (CASE WHEN id = ? THEN 'accept' ELSE 'reject' END) WHERE id_ruangan = ? AND TIME = ?", data);
    }
    
}

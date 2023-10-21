/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Hanny Yosephine
 * @author Corry Betriks Sitorus
 */
public class RoomToday {
    private String name;
    private String status;
    private String peminjam;


    public RoomToday(String name, String status, String peminjam) {
        this.name = name;
        this.status = status;
        this.peminjam = peminjam;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPeminjam() {
        return peminjam;
    }

    public void setPeminjam(String peminjam) {
        this.peminjam = peminjam;
    }

}

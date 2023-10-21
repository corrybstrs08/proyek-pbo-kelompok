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
public class User {
    private Integer id;
    private String nama;
    private String kelas;
    private String username;
    private String password;

    public User(Integer id, String nama, String kelas, String username, String password) {
        this.id = id;
        this.nama = nama;
        this.kelas = kelas;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nama=" + nama + ", kelas=" + kelas + ", username=" + username + ", password=" + password + '}';
    }
    
    
}

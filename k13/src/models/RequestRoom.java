/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/*
 *
 * @author Hanny Yosephine
 * @author Corry Betriks Sitorus
 */
public class RequestRoom {
    private String id;
    private String idRuangan;
    private String namaRuangan;
    private String time;
    private String name;
    private String kelas;
    private String reason;

    public RequestRoom(String id, String idRuangan, String namaRuangan, String time, String name, String kelas, String reason) {
        this.id = id;
        this.idRuangan = idRuangan;
        this.namaRuangan = namaRuangan;
        this.time = time;
        this.name = name;
        this.kelas = kelas;
        this.reason = reason;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }
    
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdRuangan() {
        return idRuangan;
    }

    public void setIdRuangan(String idRuangan) {
        this.idRuangan = idRuangan;
    }

    public String getNamaRuangan() {
        return namaRuangan;
    }

    public void setNamaRuangan(String namaRuangan) {
        this.namaRuangan = namaRuangan;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   
    
    
}

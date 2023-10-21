/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author Hanny Yosephine
 * @author Corry Betriks Sitorus
 */
public class ToolsUtil {
    public static final void Log(String tag, String message) {
        System.out.println(tag + ": " + message);
    }
    
    public static final String errorMessage(int code){
        switch(code) {
            case 100: return "Driver MySql tidak ditemukan!";
        }
        return "";
    }
}

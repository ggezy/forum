/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forumbad;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author naya
 */
public class StoredProcedure {
    
    public static void main(String[] args) {
        
        try { 
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://192.168.1.69:3306/ebookshop";
            String username = "saitama";
            String password = "password";
            Class.forName(driver); 
            Connection conn = DriverManager.getConnection(url, username, password);     
            CallableStatement cs = conn.prepareCall("{ call addMovies(?,?) }");           
            cs.setString(1, "Harry Popot"); 
            cs.setInt(2, 50);
            cs.executeUpdate();
            
            System.out.println("Data Berhasil Di Update");
        } catch (Exception e) { 
            System.out.println(e);
        }
    }
    
}

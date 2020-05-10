/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forumbad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author naya
 */
public class ForumBAD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        createTable();
        insertData();
        getData();
    }
              
    private static Connection dbConnect() { //connect ke db
        try { 
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://192.168.1.69:3306/db_forum";
            String username = "saitama";
            String password = "password";
            Class.forName(driver); 
            Connection conn = DriverManager.getConnection(url, username, password);
            return conn;
        }catch(Exception e){ 
            System.out.println(e);
            return null;
        }
    }
    
    public static void createTable() { //membuat table baru
        try { 
            Connection conn = dbConnect(); 
            Statement stat = conn.createStatement(); 
            
            String query_table = "CREATE TABLE IF NOT EXISTS tb_movies(mov_id int NOT NULL PRIMARY KEY, title varchar(50), " + 
                    "producer varchar (50), price float)";

            stat.execute(query_table);
            System.out.println("Table Created");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
   public static void insertData() { 
       try {
           Connection conn = dbConnect();
           Statement stat = conn.createStatement();
           //insert query
           String query_insert1 = "INSERT INTO `tb_movies` VALUES ('1001', 'Die Ez', 'John', '100.99')";
           String query_insert2 = "INSERT INTO `tb_movies` VALUES ('1002', 'Mitu', 'McGeagle', '21.31')";
           
           stat.addBatch(query_insert1); 
           stat.addBatch(query_insert2);
           
           stat.executeBatch(); //execute query
           
       } catch (Exception e){ 
           System.out.println(e); 
       }

    }
   
   public static void getData() { 
       try {
           
            Connection conn = dbConnect(); 
            Statement stat = conn.createStatement(); 

            String query_getData = "SELECT * FROM `tb_movies`";

            ResultSet rs = stat.executeQuery(query_getData); 

            while(rs.next()) {
                 int id = rs.getInt("mov_id");
                 String title = rs.getString("title");
                 String author = rs.getString("producer");
                 Float price = rs.getFloat("price");

                 System.out.println(id + " " + title + " " + author + " " + price);
            }
       } catch(Exception e) { 
           System.out.println(e);
       }
   }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeemanager;
import java.util.UUID;
import java.sql.*;

/**
 *
 * @author Alessandro
 */
public class Helper 
{
    public static String getRandomString()
    {
        return UUID.randomUUID().toString();
    }
    
    public static Connection getDatabaseConnection() throws ClassNotFoundException, SQLException
    {
        //Importiamo il driver di mysql
       
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/coffee_manager","root",""); 
    }
}

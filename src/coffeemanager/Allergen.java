/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeemanager;
import java.sql.*;
import exceptions.AllergenNotFoundException;

/**
 *
 * @author Alessandro
 */
public class Allergen 
{
    protected int allergenId;
    protected String name;

    public Allergen(int allergenId) throws ClassNotFoundException, SQLException, AllergenNotFoundException 
    {
        //Genero lo UUID necessario per inserire la macchinetta nella base di dati
        String randomKey = Helper.getRandomString();
        
        //Importiamo il driver di mysql
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
        
         //Query che conta quanti allergeni sono presenti nel database, aventi come Id l'id passato come parametro
        String query = "SELECT COUNT(*) AS numero FROM allergens WHERE allergens_id=" + allergenId;
        //Estraggo i dati della query, associati ad un oggetto di tipo ResultSet
        ResultSet rs=stmt.executeQuery(query);
        
        //Leggo il primo ed unico risultato della query
        rs.next();
        
        //Verifico se c'è almeno un allergene con questo Id: se non ci sono allergeni,
        //Solleviamo debita eccezione
        if (rs.getInt(1) == 0)
        {
            throw new AllergenNotFoundException("L'allergene con l'id " + allergenId + " non è presente nel database");
        }
        
        query = "SELECT * FROM allergens WHERE allergens_id=" + allergenId;
        
        rs=stmt.executeQuery(query);
        
        //Leggo il primo ed unico risultato della query
        rs.next();
        
        this.allergenId = allergenId;
        this.name = rs.getString(2);
        
    }
    
    

    public Allergen(String name) throws ClassNotFoundException, SQLException 
    {
        this.name = name;
        
        this.allergenId = this.getAllergenIdFromDatabase();
    }
    
    private int getAllergenIdFromDatabase() throws ClassNotFoundException, SQLException
    {
        //Genero lo UUID necessario per inserire la macchinetta nella base di dati
        String randomKey = Helper.getRandomString();
        
        //Importiamo il driver di mysql
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
        //Genero la query da eseguire
        String query = "INSERT INTO allergens (name,randomkey) VALUES ('" + this.name + "','" + randomKey + "')";
        //Eseguo la query di inserimento sul DB
        stmt.executeUpdate(query);
        
        //Genero la query che estrae l'id della macchinetta appena inserita
        query = "SELECT allergens_id FROM allergens WHERE randomkey='" + randomKey + "'";
        //Estraggo i dati della query, associati ad un oggetto di tipo ResultSet
        ResultSet rs=stmt.executeQuery(query);
        
        //Leggo la prima riga dei risultati dello statement
        rs.next();
        
        //Converto in intero il primo campo del resultset relativo alla query precedente
        int retvalue =rs.getInt(1);
        
        //Cancello dal database la randomkey che non mi serve più
        query = "UPDATE allergens SET randomkey = NULL WHERE allergens_id = " + retvalue;
        stmt.executeUpdate(query);
        
        return retvalue;
    }
    

    public int getAllergenId() {
        return allergenId;
    }

    public void setAllergenId(int allergenId) {
        this.allergenId = allergenId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString()
    {
        return "Id allergene: "+ this.allergenId + "Nome allergene: " + this.name;
    }
    
}

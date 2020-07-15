/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeemanager;
import exceptions.IngredientNotFoundException;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Alessandro
 */
public class Ingredient 
{
    protected int ingredientId;
    protected float price;
    protected String name;
    protected int quantity;
    protected ArrayList<IngredientAllergens> allergensList;

    public Ingredient(int ingredientId) throws ClassNotFoundException, SQLException, IngredientNotFoundException 
    {
         Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
        
        //conteggio il numero di prodotti che hanno come Id il valore passato in ingresso
        //NB: questo valore può essere solo 0 o 1
        String query = "SELECT COUNT(*) AS numero FROM ingredients WHERE ingredients_id=" + ingredientId;
        ResultSet rs=stmt.executeQuery(query);
        
        rs.next();
        
        if (rs.getInt(1) == 0)
        {
            throw new IngredientNotFoundException("L'ingrediente con id " + ingredientId + " non è stato trovato");
        }
        
        //Estraggo tutti i dati relativi alla macchinetta esistente
        query = "SELECT * FROM ingredients WHERE ingredients_id=" + ingredientId;
        rs=stmt.executeQuery(query);
        
        rs.next();
        
        this.ingredientId = ingredientId;
        this.price = rs.getFloat(2);
        this.name = rs.getString(3);
        this.quantity = rs.getInt(4);
        
        this.loadAllergensList();
    }

    public Ingredient(float price, String name) throws ClassNotFoundException, SQLException 
    {
        this.price = price;
        this.name = name;
        
        this.quantity = 0;
        
        this.ingredientId = this.getIngredientIdFromDatabase();
    }
    
    public void loadAllergensList() throws ClassNotFoundException, SQLException
    {
        this.allergensList = new ArrayList();
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
        
        //Estraggo la lista degli ingredienti associati a questo prodotto
        String query = "SELECT ingredients_allergens_id, allergens_id FROM ingredients_allergens WHERE ingredients_id=" + this.ingredientId;
        ResultSet rs=stmt.executeQuery(query);
        while (rs.next())
        {
            this.allergensList.add(new IngredientAllergens(rs.getInt(1), rs.getInt(2)));
        }
    }
    
    private int getIngredientIdFromDatabase() throws ClassNotFoundException, SQLException
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
        String query = "INSERT INTO ingredients (price,name,quantity,randomkey) VALUES ('" + this.price + "','" + this.name + "',0,'" + randomKey + "')";
        //Eseguo la query di inserimento sul DB
        stmt.executeUpdate(query);
        
        //Genero la query che estrae l'id della macchinetta appena inserita
        query = "SELECT ingredients_id FROM ingredients WHERE randomkey='" + randomKey + "'";
        //Estraggo i dati della query, associati ad un oggetto di tipo ResultSet
        ResultSet rs=stmt.executeQuery(query);
        
        //Leggo la prima riga dei risultati dello statement
        rs.next();
        
        //Converto in intero il primo campo del resultset relativo alla query precedente
        int retvalue =rs.getInt(1);
        
        //Cancello dal database la randomkey che non mi serve più
        query = "UPDATE ingredients SET randomkey = NULL WHERE ingredients_id = " + retvalue;
        stmt.executeUpdate(query);
        
        return retvalue;
    }
    

    public int getIngredientId() 
    {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public String toString()
    {
        return "Id Ingrediente: "+ this.ingredientId + "Nome Ingrediente: " + this.name + " Quantità disponibile: " + this.quantity + " Prezzo unitario: " + this.price;
    }
}

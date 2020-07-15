/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeemanager;
import java.sql.*;
import java.util.ArrayList;
import exceptions.ProductNotFoundException;
/**
 *
 * @author Alessandro
 */
public class Product 
{
    protected float price;
    protected String name;
    protected int productId;
    
    protected ArrayList<ProductIngredients> ingredientsList;
    
    

    public Product(int productId) throws ClassNotFoundException, SQLException, ProductNotFoundException 
    {
         //Impostiamo la connessione al db Mysql
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
       
        //conteggio il numero di prodotti che hanno come Id il valore passato in ingresso
        //NB: questo valore può essere solo 0 o 1
        String query = "SELECT COUNT(*) AS numero FROM products WHERE products_id=" + productId;
        ResultSet rs=stmt.executeQuery(query);
        
        rs.next();
        if (rs.getInt(1) == 0)
        {
            throw new ProductNotFoundException("Il prodotto con id " + productId + " non è stato trovato");
        }
        
        //Estraggo tutti i dati relativi alla macchinetta esistente
        query = "SELECT * FROM products WHERE products_id=" + productId;
        rs=stmt.executeQuery(query);
        
        rs.next();
        
        this.productId = productId;
        this.price = rs.getFloat(2);
        this.name = rs.getString(3);

        this.loadIngredientsList();

    }
    
    

    public Product(float price, String name) throws ClassNotFoundException, SQLException 
    {
        this.price = price;
        this.name = name;
        
        this.productId = this.getProductIdFromDatabase();
        
    }
    
    public void loadIngredientsList() throws ClassNotFoundException, SQLException
    {
        //Istanziamo l'ArrayList ingredientsList
        this.ingredientsList = new ArrayList();
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
        
        //Estraggo la lista degli ingredienti associati a questo prodotto
        String query = "SELECT products_ingredients_id, ingredients_id FROM products_ingredients WHERE products_id=" + this.productId;
        ResultSet rs=stmt.executeQuery(query);
        while (rs.next())
        {
            this.ingredientsList.add(new ProductIngredients(rs.getInt(1), rs.getInt(2)));
        }
    }
    
    @Override
    public String toString()
    {
        return "Id prodotto: " + this.productId + " Nome: " + this.name + " Prezzo: " + this.price;
    }
    
    private int getProductIdFromDatabase() throws ClassNotFoundException, SQLException
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
        String query = "INSERT INTO products (price,name,randomkey) VALUES ('" + this.price + "','" + this.name + "','" + randomKey + "')";
        //Eseguo la query di inserimento sul DB
        stmt.executeUpdate(query);
        
        //Genero la query che estrae l'id della macchinetta appena inserita
        query = "SELECT products_id FROM products WHERE randomkey='" + randomKey + "'";
        //Estraggo i dati della query, associati ad un oggetto di tipo ResultSet
        ResultSet rs=stmt.executeQuery(query);
        
        //Leggo la prima riga dei risultati dello statement
        rs.next();
        
        //Converto in intero il primo campo del resultset relativo alla query precedente
        int retvalue =rs.getInt(1);
        
        //Cancello dal database la randomkey che non mi serve più
        query = "UPDATE products SET randomkey = NULL WHERE products_id = " + retvalue;
        stmt.executeUpdate(query);
        
        return retvalue;
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    
}

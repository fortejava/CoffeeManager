/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeemanager;
import java.sql.*;
import java.util.ArrayList;
import exceptions.CoffeeMachineNotFoundException;

/**
 *
 * @author Alessandro
 */
public class CoffeeMachine 
{
    protected int coffeeMachineId;
    protected String model;
    protected String brand;
    protected float credit;
    
    protected ArrayList<CoffeeMachineIngredients> ingredientsList;
    protected ArrayList<CoffeeMachineProducts> productsList;

    //Questo costruttore, tenterà di istanziare una macchinetta del caffè, a partire dal suo Id
    public CoffeeMachine(int coffeeMachineId) throws ClassNotFoundException, SQLException, CoffeeMachineNotFoundException 
    {
        //Impostiamo la connessione al db Mysql
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
       
        //conteggio il numero di macchinette che hanno come Id il valore passato in ingresso
        //NB: questo valore può essere solo 0 o 1
        String query = "SELECT COUNT(*) AS numero FROM coffee_machines WHERE coffee_machines_id=" + coffeeMachineId;
        ResultSet rs=stmt.executeQuery(query);
        
        rs.next();
        if (rs.getInt(1) == 0)
        {
            throw new CoffeeMachineNotFoundException("Macchinetta con id " + coffeeMachineId + " non trovata");
        }
        
        //Estraggo tutti i dati relativi alla macchinetta esistente
        query = "SELECT * FROM coffee_machines WHERE coffee_machines_id=" + coffeeMachineId;
        rs=stmt.executeQuery(query);
        
        rs.next();
        
        this.coffeeMachineId = coffeeMachineId;
        this.brand = rs.getString(3);
        this.model = rs.getString(2);
        this.credit = rs.getInt(4);
        
        this.loadIngredientsList();
        this.LoadProductsList();
    }
    
    //Funzione che carica la lista dei prodotti associati alla macchinetta attuale
    public void LoadProductsList() throws ClassNotFoundException, SQLException
    {
        this.productsList = new ArrayList();
        //Impostiamo la connessione al db Mysql
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
        
        //Estraggo tutte le associazioni macchinetta - prodotti
        String query = "SELECT * FROM coffee_machines_products WHERE coffee_machines_id=" + this.coffeeMachineId;
        
        ResultSet rs=stmt.executeQuery(query);
        
        while (rs.next())
        {
            this.productsList.add(new CoffeeMachineProducts(rs.getInt(1), rs.getInt(3)));
        }
    }

    public CoffeeMachine(String model, String brand) throws ClassNotFoundException, SQLException 
    {
        this.model = model;
        this.brand = brand;
        this.credit = 0.0f;
        
        this.coffeeMachineId = this.getCoffeeMachineIdFromDatabase();
        
        //TODO: Caricare la lista degli ingredienti della macchinetta del caffè
        this.loadIngredientsList();
    }
    
    //Carichiamo la lista degli ingredienti associati alla macchinetta attuale
    public void loadIngredientsList() throws ClassNotFoundException, SQLException
    {
        //Inizializziamo la lista degli ingredienti
        this.ingredientsList = new ArrayList<CoffeeMachineIngredients>();
        
        //Impostiamo la connessione al db Mysql
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
        
        //Leggere dalla tabella Coffee_Machines_Ingredients tutta la lista degli ingredienti associati alla 
        //macchinetta che invoca il metodo
        
        //Genero la query che estrae gli ingredienti associati alla macchinetta attuale
        String query = "SELECT coffee_machines_ingredients_id, ingredients_id, quantity FROM coffee_machines_ingredients WHERE coffee_machines_id=" + this.coffeeMachineId ;
        //Estraggo i dati della query, associati ad un oggetto di tipo ResultSet
        ResultSet rs=stmt.executeQuery(query);
        
        //Leggo i risultati, riga per riga, della tabella estratta
        while (rs.next())
        {
            //Aggiungo l'elemento estratto alla riga attuale del risultato, tra i dati della proprietà
            //ingredientsList della classe
            this.ingredientsList.add(new CoffeeMachineIngredients(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
        }
        
    }
    
    private int getCoffeeMachineIdFromDatabase() throws ClassNotFoundException, SQLException
    {
        //Genero lo UUID necessario per inserire la macchinetta nella base di dati
        String randomKey = Helper.getRandomString();
        
        //Importiamo il driver di mysql
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database
        //Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/coffee_manager","root","");  
        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
        //Genero la query da eseguire
        String query = "INSERT INTO coffee_machines (model,brand,credit,randomkey) VALUES ('" + this.model + "','" + this.brand + "',0,'" + randomKey + "')";
        //Eseguo la query di inserimento sul DB
        stmt.executeUpdate(query);
        
        //Genero la query che estrae l'id della macchinetta appena inserita
        query = "SELECT coffee_machines_id FROM coffee_machines WHERE randomkey='" + randomKey + "'";
        //Estraggo i dati della query, associati ad un oggetto di tipo ResultSet
        ResultSet rs=stmt.executeQuery(query);
        
        //Leggo la prima riga dei risultati dello statement
        rs.next();
        
        //Converto in intero il primo campo del resultset relativo alla query precedente
        int retvalue =rs.getInt(1);
        
        //Cancello dal database la randomkey che non mi serve più
        query = "UPDATE coffee_machines SET randomkey = NULL WHERE coffee_machines_id = " + retvalue;
        stmt.executeUpdate(query);
        
        return retvalue;
    }
    
    @Override
    public String toString()
    {
        return "Id:" + this.coffeeMachineId + " Model: " + this.model + " Brand: " + this.brand;
    }

    public int getCoffeeMachineId() {
        return coffeeMachineId;
    }

    public void setCoffeeMachineId(int coffeeMachinesId) {
        this.coffeeMachineId = coffeeMachinesId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }
    
    
}

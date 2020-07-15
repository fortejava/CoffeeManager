/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeemanager;

import exceptions.AllergenNotFoundException;
import exceptions.CoffeeMachineNotFoundException;
import exceptions.IllegalProductPriceException;
import exceptions.IngredientNotFoundException;
import exceptions.IngredientUnusedException;
import exceptions.InvalidStockStatusException;
import exceptions.ProductAlreadyPresentException;
import exceptions.ProductNotFoundException;
import java.util.ArrayList;
import java.sql.*;
import java.io.*;

/**
 *
 * @author Alessandro
 */
public class Console 
{
    private ArrayList<CoffeeMachine> coffeeMachinesList;
    private ArrayList<Ingredient> ingredientsList;
    private ArrayList<Product> productsList;
    private ArrayList<Allergen> allergensList;
    
    /*
    public Console() throws ClassNotFoundException, SQLException
    {
        this.LoadCoffeeMachinesList();
        this.LoadIngredientsList();
        this.LoadAllergensList();
        this.LoadProductsList();
    }
    */
    
    //Metodo che stampa a schermo la lista delle macchinette del caffè con tutte le loro info
    public void printCoffeeMachinesList() throws ClassNotFoundException, SQLException
    {
        CoffeeMachine[] list = this.LoadCoffeeMachinesData();
        
        for(CoffeeMachine x : list)
        {
            System.out.println("Id: " + x.getCoffeeMachineId() + " Modello: " + x.model + " Marca: " + x.brand + " Credito: " + x.credit);
        }
    }
    
    public void printCoffeeMachineProducts(int coffeeMachineId) throws ClassNotFoundException, SQLException, CoffeeMachineNotFoundException, ProductNotFoundException
    {
        //Istanziare la macchinetta con id = coffeemachineId
        
        CoffeeMachine x = new CoffeeMachine(coffeeMachineId);
        
        //Ciclare su tutti i CoffeeMachineProducts ed ottenere l'id di ogni prodotto
        
        //Ottenere i dati sul prodotto attuale
            //Stampare i dati relativi al prodotto attuale
        for (CoffeeMachineProducts tmp:x.productsList)
        {
            Product p = new Product(tmp.getProductId());
            System.out.println("Id prodotto: " + p.getProductId() + " Nome: " + p.getName() + " Prezzo: " + p.getPrice());
        } 
    }
    
    public void printCoffeeMachineIngredients(int coffeeMachineId) throws ClassNotFoundException, SQLException, CoffeeMachineNotFoundException, ProductNotFoundException, IngredientNotFoundException
    {
        //Istanziare la macchinetta con id = coffeemachineId
        
        CoffeeMachine x = new CoffeeMachine(coffeeMachineId);
        
        //Ciclare su tutti i CoffeeMachineProducts ed ottenere l'id di ogni prodotto
        
        //Ottenere i dati sul prodotto attuale
            //Stampare i dati relativi al prodotto attuale
        for (CoffeeMachineIngredients tmp:x.ingredientsList)
        {
            Ingredient p = new Ingredient(tmp.getIngredientsId());
            System.out.println("Id ingrediente: " + p.getIngredientId()+ " Nome: " + p.getName() + " Prezzo: " + p.getPrice());
        } 
    }
    
    public void kernel()
    {
        
    }
    
    //Carico i dati relativi a tutti gli ingredienti presenti nel database
    public void LoadIngredientsList() throws ClassNotFoundException, SQLException
    {
        this.ingredientsList = new ArrayList();
        //Estrarre tutti gli id delle macchinette inserite nel database
        //Impostiamo la connessione al db Mysql
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
       
        //Estraggo tutti gli id delle macchinette presenti nel db
        String query = "SELECT ingredients_id FROM ingredients";
        ResultSet rs=stmt.executeQuery(query);
        
        while (rs.next())
        {
            try
            {
                this.ingredientsList.add(new Ingredient(rs.getInt(1)));
            }
            catch (IngredientNotFoundException ex)
            {
                System.err.println("Errore nel caricamento della macchinetta con id: " + rs.getInt(1));
            }
        }
    }
    
    //Carico i dati relativi a tutte le macchinette del caffè presenti nel database
    public void LoadCoffeeMachinesList() throws ClassNotFoundException, SQLException
    {
        this.coffeeMachinesList = new ArrayList();
        //Estrarre tutti gli id delle macchinette inserite nel database
        //Impostiamo la connessione al db Mysql
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
       
        //Estraggo tutti gli id delle macchinette presenti nel db
        String query = "SELECT coffee_machines_id FROM coffee_machines";
        ResultSet rs=stmt.executeQuery(query);
        
        while (rs.next())
        {
            try
            {
                this.coffeeMachinesList.add(new CoffeeMachine(rs.getInt(1)));
            }
            catch (CoffeeMachineNotFoundException ex)
            {
                System.err.println("Errore nel caricamento della macchinetta con id: " + rs.getInt(1));
            }
        }
    }
    
    //Carico i dati relativi a tutte le macchinette del caffè presenti nel database
    public void LoadAllergensList() throws ClassNotFoundException, SQLException
    {
        this.allergensList = new ArrayList();
        //Estrarre tutti gli id delle macchinette inserite nel database
        //Impostiamo la connessione al db Mysql
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
       
        //Estraggo tutti gli id delle macchinette presenti nel db
        String query = "SELECT allergens_id FROM allergens";
        ResultSet rs=stmt.executeQuery(query);
        
        while (rs.next())
        {
            try
            {
                this.allergensList.add(new Allergen(rs.getInt(1)));
            }
            catch (AllergenNotFoundException ex)
            {
                System.err.println("Errore nel caricamento della macchinetta con id: " + rs.getInt(1));
            }
        }
    }
    
    public void LoadProductsList() throws ClassNotFoundException, SQLException
    {
        this.allergensList = new ArrayList();
        //Estrarre tutti gli id delle macchinette inserite nel database
        //Impostiamo la connessione al db Mysql
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
       
        //Estraggo tutti gli id delle macchinette presenti nel db
        String query = "SELECT products_id FROM products";
        ResultSet rs=stmt.executeQuery(query);
        
        while (rs.next())
        {
            try
            {
                this.productsList.add(new Product(rs.getInt(1)));
            }
            catch (ProductNotFoundException ex)
            {
                System.err.println("Errore nel caricamento della macchinetta con id: " + rs.getInt(1));
            }
        }
    }
    
    //Carico i dati relativi a tutte le macchinette del caffè presenti nel database
    public CoffeeMachine[] LoadCoffeeMachinesData() throws ClassNotFoundException, SQLException
    {
        ArrayList<CoffeeMachine> retvalue = new ArrayList<CoffeeMachine>();
        //Estrarre tutti gli id delle macchinette inserite nel database
        //Impostiamo la connessione al db Mysql
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
       
        //Estraggo tutti gli id delle macchinette presenti nel db
        String query = "SELECT * FROM coffee_machines";
        ResultSet rs=stmt.executeQuery(query);
        
        while (rs.next())
        {
            try
            {
                int tmp = rs.getInt(1);
                retvalue.add(new CoffeeMachine(tmp));
            }
            catch (CoffeeMachineNotFoundException ex)
            {
                System.err.println("Errore nel caricamento della macchinetta con id: " + rs.getInt(1));
            }
        }
        
        /*
        CoffeeMachine[] x = new CoffeeMachine[retvalue.size()];
        
        for (int i=0;i<retvalue.size();i++)
        {
            x[i] = (CoffeeMachine)retvalue.get(i);
        }
        */
        return retvalue.toArray(new CoffeeMachine[0]);
    }
    
    public ProductIngredients[] getProductIngredients(int productId) throws ClassNotFoundException, SQLException, ProductNotFoundException
    {
        //Istanziare il prodotto con Id passato in ingresso
        Product p = new Product(productId);
        
        return (ProductIngredients[])p.ingredientsList.toArray(new ProductIngredients[0]);
        
    }
    
    public CoffeeMachineIngredients[] getCoffeeMachineIngredients(int coffeeMachineId) throws ClassNotFoundException, SQLException, CoffeeMachineNotFoundException
    {
        CoffeeMachine c = new CoffeeMachine(coffeeMachineId);
        
        return (CoffeeMachineIngredients[])c.ingredientsList.toArray(new CoffeeMachineIngredients[0]);
    }
    
    public boolean checkAvailability(int coffeeMachineId, int productId) throws ClassNotFoundException, SQLException, CoffeeMachineNotFoundException, ProductNotFoundException, IngredientNotFoundException
    {
        boolean retvalue = true;
        
        //Estraggo la lista degli ingredienti (scorte) di una macchinetta
        CoffeeMachineIngredients[] cmi = this.getCoffeeMachineIngredients(coffeeMachineId);
        
        //Estraggo la lista degli ingredienti di un prodotto
        ProductIngredients[] pi = this.getProductIngredients(productId);
        
        for (ProductIngredients pi_tmp: pi)
        {
            boolean isIngredientInMachine=false;
            for (CoffeeMachineIngredients cmi_tmp: cmi)
            {
               //Verifico che i due id coincidano
               if (cmi_tmp.getIngredientsId() == pi_tmp.getIngredientId())
               {
                   isIngredientInMachine = true;
                   
                   //Se i due id sono uguali, devo verificare la scorta
                   if (cmi_tmp.getQuantity() == 0)
                   {
                       retvalue = false;
                       break;
                   }
               }
            }
            
            if (isIngredientInMachine == false)
            {
                throw new IngredientNotFoundException("Uno degli ingredienti associati al prodotto, non fa parte delle scorte della macchinetta" );
            }
            
            //Verifico se l'ingrediente è disponibile in almeno una quantità
            if (retvalue == false)
            {
                break;
            }
            
        }
        
        return retvalue;
    }
    
    public boolean isProductAvailable(int coffeeMachineId, int productId) throws ClassNotFoundException, SQLException, CoffeeMachineNotFoundException
    {
        boolean retvalue=false;
        
        CoffeeMachine c = new CoffeeMachine(coffeeMachineId);
        for (CoffeeMachineProducts cmp : c.productsList)
        {
            if (cmp.getProductId() == productId)
            {
                retvalue=true;
                break;
            }
        }
        
        return retvalue;
    }
    
    public Allergen[] getProductAllergens(int productId) throws ClassNotFoundException, SQLException, ProductNotFoundException, IngredientNotFoundException, AllergenNotFoundException
    {
        //Istanziare l'oggetto Product avente id == productId
        Product p = new Product(productId);
        
        //Creo un arraylist di ingredienti
        ArrayList<Ingredient> ingredientsList = new ArrayList();
        
        for (ProductIngredients pi: p.ingredientsList)
        {
            ingredientsList.add(new Ingredient(pi.getIngredientId()));
        }
        
        ArrayList<Allergen> allergensList = new ArrayList();
        
        for (Ingredient i : ingredientsList)
        {
            for (IngredientAllergens ia : i.allergensList)
            {
                boolean isAllergenPresent = false;
                for (Allergen tmp : allergensList)
                {
                    if (tmp.getAllergenId() == ia.allergenId)
                    {
                        isAllergenPresent = true;
                        break;
                    }
                }
                
                if (isAllergenPresent == false)
                {
                    allergensList.add(new Allergen(ia.allergenId));
                }
            }
        }
        
        return (Allergen[])allergensList.toArray(new Allergen[0]);
        
    }
    
    //Metodo che, data una macchinetta del caffè con id=coffeeMachineId, 
    //restituisce un array di allergeni ad essa associati
    public Allergen[] getCoffeeMachineAllergens(int coffeeMachineId) throws ClassNotFoundException, SQLException, CoffeeMachineNotFoundException, IngredientNotFoundException, AllergenNotFoundException
    {
        CoffeeMachine c = new CoffeeMachine(coffeeMachineId);
        ArrayList<Allergen> retvalue = new ArrayList();
        
        //Devo istanziare un oggetto di tipo Ingredient per ogni CoffeeMachineIngredient presente nella macchinetta
        for (CoffeeMachineIngredients cmi : c.ingredientsList)
        {
            //Istanzio l'ingrediente
            Ingredient i = new Ingredient(cmi.ingredientsId);
            
            for (IngredientAllergens ia : i.allergensList)
            {
                //Verifico se l'allergene con id = i.allergenId è già presente nella lista degli allergeni in uscita
                boolean isAllergenPresent = false;
                for (Allergen a: retvalue)
                {
                    if (a.allergenId == ia.allergenId)
                    {
                        //I due id coincidono: l'allergene è già stato inserito, o posso interrompere la ricerca
                        isAllergenPresent = true;
                        break;
                    }
                }
                
                if (!isAllergenPresent)
                {
                    retvalue.add(new Allergen(ia.allergenId));
                }
            }
        }
        
        return (Allergen[])retvalue.toArray(new Allergen[0]);
    }
    
    public void Sale(int coffeeMachineId, int productId) throws ClassNotFoundException, SQLException, CoffeeMachineNotFoundException, ProductNotFoundException, IngredientNotFoundException, AllergenNotFoundException
    {
        //Istanziamo la macchinetta del caffè
        CoffeeMachine c = new CoffeeMachine(coffeeMachineId);
        
        //Verifichiamo che il prodotto con id = productId sia offerto dalla macchinetta del caffè
        if (this.checkAvailability(coffeeMachineId, productId))
        {
            //Inserire la vendita nel database
            float productCost = this.calculateCost(productId);
            
            //Estraiamo il prezzo di vendita del prodotto
            Product p = new Product(productId);
            this.saveSale(coffeeMachineId, productId, p.price, productCost);
            
            //Diminuire le scorte di tutti gli ingredienti associati a quel prodotto
            for (ProductIngredients pi : p.ingredientsList)
            {
                for (CoffeeMachineIngredients cmi : c.ingredientsList)
                {
                    if (pi.ingredientId == cmi.ingredientsId)
                    {
                        this.editCoffeeMachineIngredientAvailability(coffeeMachineId, pi.ingredientId, cmi.quantity-1);
                        break;
                    }
                }
            }
        }
        else
        {
            //TODO: il prodotto esiste, è associato a quessta CoffeeMachine ma non può essere
            //venduto causa mancanza di scorte
        }
    }
    
    //Funzione che modifica la disponibilità di un ingrediente in una macchinetta del caffè
    protected void editCoffeeMachineIngredientAvailability(int coffeeMachineId, int ingredientId, int quantity) throws ClassNotFoundException, SQLException
    {
        //Impostiamo la connessione al db Mysql
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
        
        String query = "UPDATE coffee_machines_ingredients SET quantity=" + quantity + " WHERE coffee_machines_id=" + coffeeMachineId + " AND ingredients_id=" + ingredientId;
        
        stmt.executeUpdate(query);
        con.close();
    }
    
    //Funzione che salva i dati relativi ad una vendita nella tabella sales
    protected void saveSale(int coffeeMachineId, int productId, float productPrice, float productCost) throws ClassNotFoundException, SQLException, ProductNotFoundException, IngredientNotFoundException, AllergenNotFoundException
    {
        //Impostiamo la connessione al db Mysql
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
        
        String allergens = this.getJSONProductAllergens(productId);
        String ingredients = this.getJSONProductIngredients(productId);
        
        String query = "INSERT INTO sales (coffee_machines_id,products_id,price,production_fee,allergens_combo,ingredients_combo) VALUES (" + coffeeMachineId +
                "," + productId + "," + productPrice + "," + productCost + ",'" + allergens + "','" + ingredients + "')";
        stmt.executeUpdate(query);
        con.close();
    }
    
    //Creiamo la funzione calculateCost che calcoli il costo di produzione di un prodotto
    public float calculateCost(int productId) throws ClassNotFoundException, SQLException
    {
        float retvalue = 0.0f;
        //Impostiamo la connessione al db Mysql
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
       
        //Estraggo tutti gli id delle macchinette presenti nel db
        String query = "select SUM(price) AS product_price FROM ingredients AS i WHERE i.ingredients_id IN (SELECT ingredients_id FROM products_ingredients AS pi WHERE pi.products_id =" + productId + ")";
        
        ResultSet rs=stmt.executeQuery(query);
        
        rs.next();
        
        retvalue = rs.getFloat(1);
        
        return retvalue;
    }
    
    //Funzione che inserisce nel database un allergene, se non è già presente un
    //Allergene omonimo
    public boolean insertAllergen(String allergenName) throws ClassNotFoundException, SQLException
    {
        boolean retvalue = false;
        
        //Controlliamo se esiste un allergene omonimo
        if (this.isAllergenPresent(allergenName) == false)
        {
            this.addAllergen(allergenName);
            retvalue=true;
        }
        
        return retvalue;
    }
    
    //Funzione che inserisce un allergene nel database senza eseguire controlli
    protected void addAllergen(String allergenName) throws ClassNotFoundException, SQLException
    {
        //Impostiamo la connessione al db Mysql
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
        
        String query = "INSERT INTO allergens (name) VALUES ('" + allergenName + "')";
        stmt.executeUpdate(query);
        con.close();
    }
    
    //Funzione che prende in ingresso il nome di un allergene e restituisce:
    //-True se esiste già un allergene con quel nome
    //-False altrimenti
    public boolean isAllergenPresent(String allergenName) throws ClassNotFoundException, SQLException
    {
        boolean retvalue = false;
        
        //Impostiamo la connessione al db Mysql
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
        
        String query ="SELECT COUNT(*) AS numero FROM allergens WHERE Lower(name)='" + allergenName.toLowerCase() + "'";
        ResultSet rs=stmt.executeQuery(query);
        
        rs.next();
        
        if (rs.getInt(1) > 0)
        {
            retvalue=true;
        }
        
        con.close();
        
        return retvalue;
    }
    
    Allergen[] getAllergensByName(String allergenName) throws ClassNotFoundException, SQLException, AllergenNotFoundException
    {
        ArrayList<Allergen> retvalue = new ArrayList();
        
         //Impostiamo la connessione al db Mysql
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
        
        String query ="SELECT allergens_id FROM allergens WHERE Lower(name) LIKE '%" + allergenName.toLowerCase() + "%'";
        ResultSet rs=stmt.executeQuery(query);
        
        while(rs.next())
        {
           //getInt(1) converte in un numero intero, il valore contenuto nel
            //campo numero 1 della riga attuale del resultSet
            //Questo valore, viene usato dal costruttore di Allergen, per istanziare l'allergene
            //Che verrà aggiunto a retvalue
            retvalue.add(new Allergen(rs.getInt(1)));
        }
        
        return (Allergen[])retvalue.toArray(new Allergen[0]);
    }
    
    //Funzione che aggiunge un allergene ad un ingrediente, se entrambi sono
    //presenti nel database. Se invece l'allergene non fosse presente, verrebbe
    //sollevata una eccezione AllergenNotFoundException, mentre se l'ingrediente
    //non fosse presente, verrebbe sollevata una IngredientNotFoundException
    protected void addAllergenToIngredient(int allergenId, int ingredientId) throws ClassNotFoundException, SQLException, AllergenNotFoundException, IngredientNotFoundException
    {
        //Verificare se esiste l'allergene
        if (this.isAllergenPresent(allergenId) == false)
        {
            throw new AllergenNotFoundException("Allergene non presente nel database. Impossibile associare ingredienti");            
        }
        
        if (this.isIngredientPresent(ingredientId) == false)
        {
            throw new IngredientNotFoundException("Ingrediente non presente nel database. Impossibile associare allergenti");
        }
        
        //Impostiamo la connessione al db Mysql
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
        
        String query = "INSERT INTO ingredients_allergens (ingredients_id, allergens_id) VALUES ('" + ingredientId + "','" + allergenId + "')";
        stmt.executeUpdate(query);
        con.close();
    }
    
    public boolean isIngredientPresent(int ingredientId) throws ClassNotFoundException, SQLException
    {
        boolean retvalue = false;
        
         //Impostiamo la connessione al db Mysql
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
        
        String query ="SELECT COUNT(*) AS numero FROM ingredients WHERE ingredients_id='" + ingredientId + "'";
        ResultSet rs=stmt.executeQuery(query);
        
        rs.next();
        
        if (rs.getInt(1) > 0)
        {
            retvalue=true;
        }
        
        con.close();
        
        return retvalue;
    }
    
    public boolean isAllergenPresent(int allergenId) throws ClassNotFoundException, SQLException
    {
        boolean retvalue = false;
        
        //Impostiamo la connessione al db Mysql
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
        
        String query ="SELECT COUNT(*) AS numero FROM allergens WHERE allergens_id='" + allergenId + "'";
        ResultSet rs=stmt.executeQuery(query);
        
        rs.next();
        
        if (rs.getInt(1) > 0)
        {
            retvalue=true;
        }
        
        con.close();
        
        return retvalue;
    }
    
    public void deleteAllergen (int allergenId) throws AllergenNotFoundException, ClassNotFoundException, SQLException
    {
        if (this.isAllergenPresent(allergenId) == false)
        {
            throw new AllergenNotFoundException("Allergene non presente nel database");
        }
        
        //Impostiamo la connessione al db Mysql
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
        
        String query = "DELETE FROM allergens WHERE allergens_id=" + allergenId;
        stmt.executeUpdate(query);
        con.close();
    }
    
    protected void editAllergen (int allergenId, String name) throws ClassNotFoundException, SQLException, AllergenNotFoundException
    {
        if(this.isAllergenPresent(allergenId)==false)
        {
            throw new AllergenNotFoundException ("allergene non trovato nel DB, impossibile modificarlo");
        } 
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
                
        String query ="UPDATE allergens SET name= " + name + " WHERE allergens_id =" + allergenId + "'";
        stmt.executeUpdate(query);
        con.close();
    }
    
    //Funzione strockCheck: restituisce lo status dell'ingrediente all'interno del database
    public short stockCheck(int ingredientId) throws ClassNotFoundException, SQLException, IngredientNotFoundException
    {
        short retvalue = 0;
        
        //Verifichiamo se l'ingrediente esiste
        if (isIngredientPresent(ingredientId) == false)
        {
            throw new IngredientNotFoundException("ingrediente non presente in anagrafica");
        }
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
        
        String query = "SELECT instock FROM ingredients WHERE ingredients_id=" + ingredientId;
        
        ResultSet rs=stmt.executeQuery(query);
        
        rs.next();
        
        retvalue = rs.getShort(1);
        
        con.close();
        
        return retvalue;
    }
    
    //Funzione che modifica il nome del prodotto con il parametro name passato in ingresso.
    //Se l'ingrediente no è presente nel db, solleva eccezione IngredientNotFoundException
    protected void editIngredient(int ingredientId, String name) throws ClassNotFoundException, SQLException, IngredientNotFoundException
    {
        //Verifichiamo se l'ingrediente esiste
        if (this.isIngredientPresent(ingredientId) == false)
        {
            throw new IngredientNotFoundException("ingrediente non presente in anagrafica");
        }
        
          
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
                
        String query ="UPDATE ingredients SET name= " + name + " WHERE ingredients_id =" + ingredientId + "'";
        stmt.executeUpdate(query);
        con.close();
    }
    
    //Funzione che, dato un id ingrediente, restituisca un array di prodotti ad esso associati
    public Product[] getIngredientProducts( int ingredientId) throws ClassNotFoundException, IngredientNotFoundException, SQLException, ProductNotFoundException
    {
        ArrayList<Product> retvalue = new ArrayList();
        
        //Verifichiamo se l'ingrediente esiste
        if (this.isIngredientPresent(ingredientId) == false)
        {
            throw new IngredientNotFoundException("ingrediente non presente in anagrafica");
        }
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
        
        String query = "SELECT products_id FROM products_ingredients WHERE ingredients_id=" + ingredientId;
        
        ResultSet rs=stmt.executeQuery(query);
        
        while(rs.next())
        {
            retvalue.add(new Product(rs.getInt(1)));
        }
        
        con.close();
        
        return (Product[])retvalue.toArray(new Product[0]);
    }
    
    //Funzione che estrae tutte le macchinette del caffè associate ad un ingrediente
    public CoffeeMachine[] getIngredientCoffeeMachines(int ingredientId) throws ClassNotFoundException, SQLException, IngredientNotFoundException, IngredientUnusedException, CoffeeMachineNotFoundException
    {
        ArrayList<CoffeeMachine> retvalue = new ArrayList();
        
        //Verifichiamo se l'ingrediente esiste
        if (this.isIngredientPresent(ingredientId) == false)
        {
            throw new IngredientNotFoundException("ingrediente non presente in anagrafica");
        }
        
        //Estraggo tutte le macchinette
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
        
        String query = "SELECT COUNT(*) AS numero FROM coffee_machines_ingredients WHERE ingredients_id=" + ingredientId;
        
        ResultSet rs=stmt.executeQuery(query);
        
        rs.next();
        
        //Controllo se il conteggio ha dato risultato == 0, l'ingrediente è non utilizzato
        if (rs.getInt(1) == 0)
        {
            throw new IngredientUnusedException("Ingrediente orfano");
        }
        
        query = "SELECT coffee_machines_id FROM coffee_machines_ingredients WHERE ingredients_id=" + ingredientId;
        
        rs=stmt.executeQuery(query);
        
        while(rs.next())
        {
            retvalue.add(new CoffeeMachine(rs.getInt(1)));
        }
        
        con.close();
        
        return (CoffeeMachine[])retvalue.toArray(new CoffeeMachine[0]);
    }
    
    //Funzione che, dato in ingresso l'id di un ingrediente, lo rimuova dal database solamente se 
    //L'ingrediente non è instock ed è orfano, ovvero non associato a nessuna macchinetta
    protected void deleteIngredient(int ingredientId) throws ClassNotFoundException, IngredientNotFoundException, SQLException, CoffeeMachineNotFoundException
    {
        
        
        if (this.stockCheck(ingredientId) == 2 && this.isIngredientPresent(ingredientId) == true)
        {
        
            try
            {
                this.getIngredientCoffeeMachines(ingredientId);
            }
            catch(IngredientUnusedException ex)
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                //Tramite l'oggetto conn, stiamo creando la connessione al database        
                Connection con = Helper.getDatabaseConnection();

                //L'oggetto stmt esegue fisicamente la query sul db
                Statement stmt=con.createStatement();

                String query = "DELETE FROM ingredients WHERE ingredients_id=" + ingredientId;
                stmt.executeUpdate(query);
                con.close();
            }
        }
        else
        {
            throw new IngredientNotFoundException("Ingrediente non presente oppure non orfano");
        }
        
    }
    
    //Funzione che modifica il prezzo di un ingrediente, se esiste. Altrimenti,
    //Solleva IngredientNotFoundException
    protected void changeIngredientPrice(int ingredientId, float price) throws ClassNotFoundException, IngredientNotFoundException, SQLException
    {
        //Verifichiamo se l'ingrediente esiste
        if (this.isIngredientPresent(ingredientId) == false)
        {
            throw new IngredientNotFoundException("ingrediente non presente in anagrafica");
        }
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();

        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();

        String query = "UPDATE ingredients SET price=" + price + " WHERE ingredients_id=" + ingredientId;
        stmt.executeUpdate(query);
        con.close();
    }
    
    //Funzione che estrae un prodotto dal database se presente, altrimenti sollevi una ProductNotFoundException
    public Product getProductByName(String name) throws ClassNotFoundException, SQLException, ProductNotFoundException
    {
        //Estraggo tutte le macchinette
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
        
        String query = "SELECT COUNT(*) FROM products WHERE name='" + name + "'" ;
        
        ResultSet rs=stmt.executeQuery(query);
        
        rs.next();
        
        if (rs.getInt(1) == 0)
        {
            throw new ProductNotFoundException("Il prodotto non è presente in anagrafica");
        }
        
        query = "SELECT product_id FROM products WHERE name='" + name + "'" ;
        rs=stmt.executeQuery(query);
        
        rs.next();
        
        con.close();
        
        return new Product(rs.getInt(1));
        
    }
    
    protected void addProduct(String name, float price) throws ClassNotFoundException, SQLException, ProductAlreadyPresentException
    {
        Product p=null;
        try
        {
            this.getProductByName(name);
        }
        catch(ProductNotFoundException ex)
        {
            p = new Product(price,name);
        }
        
        if (p == null)
        {
            throw new ProductAlreadyPresentException("Prodotto omonimo presente in anagrafica");
        }
        
    }
    
    //Funzione che estreae tutti gli ingredienti orfani, ovvero non associati a nessuna
    //coffeemachine, presenti nella tabella ingredients
    public Ingredient[] getUnusedIngredients() throws ClassNotFoundException, SQLException, IngredientNotFoundException, CoffeeMachineNotFoundException
    {
        ArrayList<Ingredient> retvalue = new ArrayList();
        
        //Fase1: estrarre tutti gli ingredienti dal database
        //Estraggo tutte le macchinette
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
        
        String query = "SELECT ingredients_id FROM ingredients";
        
        ResultSet rs=stmt.executeQuery(query);
        
        while (rs.next())
        {
            int ingredientId = rs.getInt(1);
            try
            {
                this.getIngredientCoffeeMachines(ingredientId);
            }
            catch(IngredientUnusedException ex)
            {
                retvalue.add(new Ingredient(ingredientId));
            }
        }
        
        con.close();
        return (Ingredient[])retvalue.toArray(new Ingredient[0]);
    }
    
    //Funzione che imposta un ingrediente su InStock o OutOfStock
    protected void setIngredientStock(int ingredientId, short stockStatus) throws ClassNotFoundException, IngredientNotFoundException, SQLException, InvalidStockStatusException
    {
        //Verifichiamo se l'ingrediente esiste
        if (this.isIngredientPresent(ingredientId) == false)
        {
            throw new IngredientNotFoundException("ingrediente non presente in anagrafica");
        }
        
        //Verifichiamo che lo status sia un valore ammesso
        if (this.isStockCorrected(stockStatus) == false)
        {
            throw new InvalidStockStatusException("Status dello stock non ammissibile");
        }
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
        
        String query="UPDATE ingredients SET instock=" + stockStatus;
        stmt.executeUpdate(query);
        con.close();
        
    }
    
    //Definiamo una funzione che controlli se lo stock status è inserito nel database.
    public boolean isStockCorrected(int stockStatus) throws ClassNotFoundException, SQLException
    {
        boolean retvalue = true;
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();

        String query = "SELECT COUNT(*) as numero FROM stock_statuses WHERE stock_statuses_id=" + stockStatus;
        
        ResultSet rs=stmt.executeQuery(query);
        
        rs.next();
        
        if (rs.getInt(1) == 0)
        {
            retvalue=false;
        }
        
        con.close();
        return retvalue;
    }
    
    public Sale[] getSalesTotal(int coffeeMachineId, int productId, String startDate, String endDate) throws ClassNotFoundException, SQLException, CoffeeMachineNotFoundException, ProductNotFoundException
    {
        ArrayList<Sale> retvalue = new ArrayList<>();
        
        String query = "SELECT * AS totale FROM sales_report ";
        
        String[] querySegments = new String[3];
       
        for (int i=0;i<querySegments.length;i++)
        {
            querySegments[i] = "";
        }
        
        try
        {
            CoffeeMachine c = new CoffeeMachine(coffeeMachineId);
            
            querySegments[0] = " coffee_machines_id=" + coffeeMachineId + " ";
        }
        catch(CoffeeMachineNotFoundException ex)
        {
            if (coffeeMachineId != 0)
            {
                throw new CoffeeMachineNotFoundException("CoffeeMachineId assente o negativo");
            }
        }
        
        try
        {
            Product p = new Product(productId);
            querySegments[1] = " products_id=" + productId + " ";
        }
        catch (ProductNotFoundException ex)
        {
            if (productId != 0)
            {
                throw new ProductNotFoundException("Id prodotto assente o non valido");
            }
        }
        
        if (startDate.length() > 0)
        {
            querySegments[2] = " sales_date >=" + startDate + " ";
            
            if (endDate.length() >0)
            {
               querySegments[2] = "(" + querySegments[2] + "AND sales_date <= " + endDate + ")"; 
            } 
        }
        
        for (int i=0;i<querySegments.length;i++)
        {
            if (querySegments[i].length() > 0)
            {
                query += querySegments[i] + " AND ";
            }
            
            if (i == querySegments.length-1)
            {
                query += "1=1";
            }
        }
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
        
        ResultSet rs=stmt.executeQuery(query);
        
        while (rs.next())
        {
            retvalue.add(new Sale(rs.getString(1),rs.getString(3),rs.getString(8),rs.getString(2),rs.getInt(5),rs.getInt(6),rs.getInt(4),rs.getFloat(7),rs.getFloat(9)));
        }   
        
        con.close();
        
        return (Sale[])retvalue.toArray(new Sale[0]);
    }
    
    public void saveSalesData(Sale[] sales) throws IOException
    {
        PrintWriter outFile = new PrintWriter(new FileWriter("a:\\sales_data.csv"));
        
        for (int i=0;i<sales.length;i++)
        {
            //Correggo il bug della scrittura dell'ultima riga
            if (i<sales.length-1)
            {
                outFile.println(sales[i].toString(";"));
            }
            else
            {
                outFile.print(sales[i].toString(";"));
            }
        }
        
        //Chiudo il file
        outFile.close();
    }
    
    //Funzione che, dato un id di prodotto passato come parametro, restituisce la lista degli allergeni in formato JSON
    public String getJSONProductIngredients(int productId) throws ClassNotFoundException, SQLException, ProductNotFoundException, IngredientNotFoundException
    {
        
        ProductIngredients[] pi = this.getProductIngredients(productId);
        
        String retvalue = "{ \"ingredients\":[";
        
        for (int i =0;i<pi.length;i++)
        {
            String tmp = "\"";
            Ingredient ingrediente = new Ingredient(pi[i].getIngredientId());
            tmp += ingrediente.getName();
            tmp += "\"";
            
            if (i<pi.length-1)
            {
                tmp += ",";
            }
            
            retvalue += tmp;
        }
        
        retvalue += "]}";
        
        return retvalue;
    }
    
    public String getJSONProductAllergens(int productId) throws ClassNotFoundException, SQLException, ProductNotFoundException, AllergenNotFoundException, IngredientNotFoundException
    {
        
        Allergen[] list = this.getProductAllergens(productId);
        
        String retvalue = "{ \"allergens\":[";
        
        for (int i =0;i<list.length;i++)
        {
            String tmp = "\"";
            
            tmp += list[i].getName();
            tmp += "\"";
            
            if (i<list.length-1)
            {
                tmp += ",";
            }
            
            retvalue += tmp;
        }
        
        retvalue += "]}";
        
        return retvalue;
    }
    
    protected void editProduct(int productId, String name, float price) throws ClassNotFoundException, SQLException, ProductNotFoundException, IllegalProductPriceException, IllegalproductNameException
    {
        Product p = new Product(productId);
        
        if (price <= 0)
        {
            throw new IllegalProductPriceException();
        }
        
        if (name.length() == 0)
        {
            throw new IllegalproductNameException();
        }
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Tramite l'oggetto conn, stiamo creando la connessione al database        
        Connection con = Helper.getDatabaseConnection();
        
        //L'oggetto stmt esegue fisicamente la query sul db
        Statement stmt=con.createStatement();
        
        String query = "UPDATE products SET name='" + name + "', price=" + price + " WHERE product_id=" + productId;
        stmt.executeQuery(query);
        
        con.close();
        
    }
    
}

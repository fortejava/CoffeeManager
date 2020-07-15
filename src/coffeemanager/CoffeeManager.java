/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeemanager;

import exceptions.CoffeeMachineNotFoundException;
import exceptions.ProductNotFoundException;

/**
 *
 * @author Alessandro
 */
public class CoffeeManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        try
        {
            Console c = new Console();
            c.Sale(1, 1);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
}

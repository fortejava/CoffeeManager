/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeemanager;

/**
 *
 * @author Alessandro
 */
public class CoffeeMachineProducts 
{
    protected int coffeeMachineProductId;
    protected int productId;

    public CoffeeMachineProducts(int coffeeMachineProductId, int productId) {
        this.coffeeMachineProductId = coffeeMachineProductId;
        this.productId = productId;
    }
    
    

    public int getCoffeeMachineProductId() {
        return coffeeMachineProductId;
    }

    public void setCoffeeMachineProductId(int coffeeMachineProductId) {
        this.coffeeMachineProductId = coffeeMachineProductId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    
}

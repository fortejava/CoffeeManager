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
public class CoffeeMachineIngredients 
{
    protected int coffeeMachinesIngredientsId;
    protected int ingredientsId;
    protected int quantity;

    public CoffeeMachineIngredients(int coffeeMachinesIngredientsId, int ingredientsId, int quantity) {
        this.coffeeMachinesIngredientsId = coffeeMachinesIngredientsId;
        this.ingredientsId = ingredientsId;
        this.quantity = quantity;
    }

    public int getCoffeeMachinesIngredientsId() {
        return coffeeMachinesIngredientsId;
    }

    public void setCoffeeMachinesIngredientsId(int coffeeMachinesIngredientsId) {
        this.coffeeMachinesIngredientsId = coffeeMachinesIngredientsId;
    }

    public int getIngredientsId() {
        return ingredientsId;
    }

    public void setIngredientsId(int ingredientsId) {
        this.ingredientsId = ingredientsId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}

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
public class ProductIngredients 
{
    protected int productsIngredientsId;
    protected int ingredientId;

    public ProductIngredients(int productsIngredientsId, int ingredientId) {
        this.productsIngredientsId = productsIngredientsId;
        this.ingredientId = ingredientId;
    }

    public int getProductsIngredientsId() {
        return productsIngredientsId;
    }

    public void setProductsIngredientsId(int productsIngredientsId) {
        this.productsIngredientsId = productsIngredientsId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }
    
    
}

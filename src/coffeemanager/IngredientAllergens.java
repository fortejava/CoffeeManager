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
public class IngredientAllergens 
{
    protected int ingredientsAllergensId;
    protected int allergenId;

    public IngredientAllergens(int ingredientsAllergensId, int allergenId) {
        this.ingredientsAllergensId = ingredientsAllergensId;
        this.allergenId = allergenId;
    } 

    public void setIngredientsAllergensId(int ingredientsAllergensId) {
        this.ingredientsAllergensId = ingredientsAllergensId;
    }

    public void setAllergenId(int allergenId) {
        this.allergenId = allergenId;
    }
    
    
}

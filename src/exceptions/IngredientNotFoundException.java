/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Alessandro
 */
public class IngredientNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>IngredientNotFoundException</code>
     * without detail message.
     */
    public IngredientNotFoundException() {
    }

    /**
     * Constructs an instance of <code>IngredientNotFoundException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public IngredientNotFoundException(String msg) {
        super(msg);
    }
}

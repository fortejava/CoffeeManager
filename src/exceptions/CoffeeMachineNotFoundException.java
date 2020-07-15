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
public class CoffeeMachineNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>CoffeeMachineNotFoundException</code>
     * without detail message.
     */
    public CoffeeMachineNotFoundException() {
    }

    /**
     * Constructs an instance of <code>CoffeeMachineNotFoundException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public CoffeeMachineNotFoundException(String msg) {
        super(msg);
    }
}

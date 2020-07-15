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
public class ProductAlreadyPresentException extends Exception {

    /**
     * Creates a new instance of <code>ProductAlreadyPresentException</code>
     * without detail message.
     */
    public ProductAlreadyPresentException() {
    }

    /**
     * Constructs an instance of <code>ProductAlreadyPresentException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ProductAlreadyPresentException(String msg) {
        super(msg);
    }
}

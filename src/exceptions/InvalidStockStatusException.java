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
public class InvalidStockStatusException extends Exception {

    /**
     * Creates a new instance of <code>InvalisStockStatusException</code>
     * without detail message.
     */
    public InvalidStockStatusException() {
    }

    /**
     * Constructs an instance of <code>InvalisStockStatusException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidStockStatusException(String msg) {
        super(msg);
    }
}

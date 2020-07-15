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
public class AllergenNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>AllergenNotFoundException</code> without
     * detail message.
     */
    public AllergenNotFoundException() {
    }

    /**
     * Constructs an instance of <code>AllergenNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public AllergenNotFoundException(String msg) {
        super(msg);
    }
}

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
public class Sale 
{
    private String brand, name, sales_date, model;
    private int coffee_machine_id, product_id, sales_id;
    private float price, production_fee;

    public Sale(String brand, String name, String sales_date, String model, int coffee_machine_id, int product_id, int sales_id, float price, float production_fee) {
        this.brand = brand;
        this.name = name;
        this.sales_date = sales_date;
        this.model = model;
        this.coffee_machine_id = coffee_machine_id;
        this.product_id = product_id;
        this.sales_id = sales_id;
        this.price = price;
        this.production_fee = production_fee;
    }
    
    public String toString(String separator)
    {
        return this.brand + separator + this.model + separator + sales_id +
                separator + sales_date + separator + this.name + separator + 
                this.price + separator + this.production_fee + separator + this.coffee_machine_id +
                separator + this.product_id;
    }
    
}

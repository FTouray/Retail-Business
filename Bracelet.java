import java.util.*;
/**
 * Write a description of class Bracelet here.
 *
 * Fatoumatta Touray | C21354101
 */
public class Bracelet extends Jewellery
{
    /**Task 3 - define Bracelet*/    
    private String metal;
    
    /**Task 3.1 - Create standard operation*/
    
    /*Constructors*/
    public Bracelet()
    {
        super();
        this.metal = "";
    }
    
    public Bracelet(String type, String colour, double price, int stock, double sales, String metal)
    {
        super(type, colour, price, stock, sales);
        this.metal= metal;
    }
    
    /*Getters*/
    public String getMetal()
    {
        return this.metal;
    }
    
    /*Setters*/
    public void setMetal(String metal)
    {
        this.metal = metal;
    }    
    
     public boolean sale()
    {
        boolean sale = true;
        
        if (this.metal.equalsIgnoreCase("platinum") || this.metal.equalsIgnoreCase("rhodium") 
        || this.metal.equalsIgnoreCase("gold"))
        {
            sale = false;
        } 
       
        return sale;
    }
    
    /*To String*/
    public String toString()
    {    
        return "\nThis item is a bracelet \n" + super.toString() + 
        "\nThe metal used to manufacture the bracelet is: " + this.metal;
    }
}

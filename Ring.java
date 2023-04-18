import java.util.*;
/**
 * Write a description of class Ring here.
 *
 * Fatoumatta Touray | C21354101
 */
public class Ring extends Jewellery
{
    /**Task 2 - define Ring*/
    private String gemstone;
    private double carat;
    
    /**Task 2.1 - Create standard operation*/
    
    /*Constructors*/
    public Ring()
    {
        super();
        this.gemstone = "";
        this.carat = 0;
    }
    
    public Ring(String type, String colour, double price, int stock, double sales, String gem, double carat)
    {
        super(type, colour, price, stock, sales);
        this.gemstone = gem;
        this.carat = carat;
    }
    
    /*Getters*/
    public String getGemstone()
    {
        return this.gemstone;
    }

    public double getCarat()
    {
        return this.carat;
    }

    /*Setters*/
    public void setGemstone(String gem)
    {
        this.gemstone = gem;
    }

    public void setCarat(double carat)
    {
        this.carat = carat;
    }
    
     public boolean sale()
    {
        boolean sale = true;
        
        if (this.gemstone.equalsIgnoreCase("diamond") || this.gemstone.equalsIgnoreCase("jade") 
        || this.gemstone.equalsIgnoreCase("emerald") || this.gemstone.equalsIgnoreCase("opal"))
        {
            sale = false;
        } 
        
        
        return sale;
    }
    
    /*To String*/
    public String toString()
    {
        return "\nThis item is a ring \n" + super.toString() + 
        "\nGemstone on ring: " + this.gemstone +
        "\nThe carat in the ring: " + this.carat;
    }
}

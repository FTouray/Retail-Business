import java.util.*;
import java.io.Serializable;
/**
 * Superclass that contains the type of product, the colour, the price and the
 stock.
 * Fatoumatta Touray | C21354101
 */
class Jewellery implements Serializable
{
    /**Task 1 - define Jewelley*/
    private String itemType;
    private String itemColour;
    private double price;
    private int stock;
    private double generatedSales;
    
    /**Task 1.1 - Create standard operation*/
    
    /*Constructors*/
    public Jewellery()
    {
        this.itemType = "";
        this.itemColour = "";
        this.price = 0;
        this.stock = 0;
        this.generatedSales = 0;
    }
    
    public Jewellery(String type, String colour, double price, int stock, double sales)//constructor
    {
        this.itemType = type;
        this.itemColour = colour;
        this.price = price;
        this.stock = stock;
        this.generatedSales = sales; 
    }
    
    /*Getters*/
    public String getItemType()
    {
        return this.itemType;
    }
    
    public String getItemColour()
    {
        return this.itemColour;
    }
    
    public double getPrice()
    {
        return this.price;
    }
    
    public int getStock()
    {
        return this.stock;
    }
    
    public double getGeneratedSales()
    {
        return this.generatedSales;
    }
    
    /*Setters*/
    public void setItemType(String item)
    {
        this.itemType = item;
    }
    
    public void setItemColour(String colour)
    {
        this.itemColour = colour;
    }
    
    public void setPrice(double price)
    {
        this.price = price;
    }
    
    public void setStock(int stock)
    {
        this.stock = stock;
    }

    public void setGeneratedSales(double sales)
    {
        this.generatedSales = sales;
    }
    
    /**Task 1.2 - Return true/false value of stock level*/
    public boolean stockLevel()
    {
        boolean stockLevel = false;
        
        if (this.stock > 0)
        {
            stockLevel = true;
        } 
        else
        {
            stockLevel = false;
        }
        
        return stockLevel;
    }
    
    public boolean sale()
    {
        
        return false;
    }
    
    /*To String*/   
    public String toString()
    {
        String result = "";
        
        result = "Item details displayed below \n" +
        "Item type: " + this.itemType + "\n" +
        "Colour of item: " + this.itemColour + "\n" +
        "Price of item: " + this.price;
        
        if (stockLevel() == true)
        {
            result = result + "\nAmount in stock: " + this.stock;
        }
        else
        {
            result = result + "\nOut of stock";
        }
        return result;
    }
}

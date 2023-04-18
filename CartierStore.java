import java.util.*;
import java.util.ArrayList;
import java.io.*;
/**
 * 
 *
 * Fatoumatta Touray | C21354101
 * 
 */
public class CartierStore
{

    ArrayList<Jewellery>category; //Declaring global ArrayList of Jewellery

    //Constructor to call all the methods
    public CartierStore()
    {
        /**Task 4 - Creating a blank ArrayList*/
        category = new ArrayList<Jewellery>(); //Creating a blank ArrayList
        System.out.print("\f"); //clear the screen 

        /*At the start of the program, check to see if the data file exists and copy jewellery items
        from data file into the ArrayList */

        if (readFromFile())
        {
            System.out.println("Jewellery Items have been found and are now being inputted into the system.\n");
            enterReturn();
        }
        else
        {
            System.out.println("There are no Jewellery Items stored. Please head to the staff menu to create new items.\n");
            enterReturn();
        }

        mainMenu();

        /* Before the program ends, save the jewellery objects into the data file */
        saveToFile();
    }

    public void saveToFile()
    {
        ObjectOutputStream fileOut;
        try{
            fileOut = new ObjectOutputStream(new FileOutputStream("jewellery.dat"));
            for (Jewellery j : category)
            {
                fileOut.writeObject(j);
            }
            fileOut.close();
            System.out.println("All jewellery items have been saved");
        }
        catch (IOException e)
        {
            System.out.println("IO Error : " + e.getMessage());
        }
    }//end saveToFile

    public boolean readFromFile()
    {
        int index=0;
        ObjectInputStream fileIn;
        Jewellery j;

        try{
            fileIn = new ObjectInputStream(new FileInputStream("jewellery.dat"));
            System.out.println("Opened file successfully");
            j = (Jewellery) fileIn.readObject();
            index = 1;
            while (j != null)
            {
                category.add(j);
                j = (Jewellery) fileIn.readObject();
                index++;
            }
            fileIn.close();
            return true;
        }
        catch (IOException e)
        {
            if (index > 0) 
            {
                return true; 
            }
            else 
            { System.out.println("Data file does not exist\n");
                return false;
            }
        }

        catch (ClassNotFoundException e)
        {
            System.out.println("Class Error : " + e.getMessage());
            return false;
        }
    }//end saveToFile

    public void enterReturn()
    {
        //reduce the amount of time this code is written
        Scanner scan = new Scanner(System.in);
        System.out.println("\nPress return to continue"); //so the s.o.p can be displayed before the menu displays
        scan.nextLine();
    }

    public void mainMenu() //decided to use switch case to tidy it up
    {
        Scanner scan = new Scanner(System.in);
        String menu = " "; //Declared as String so program doesn't crash if wrong data type is inputted
        System.out.print("\f");

        System.out.println("\t\tWELCOME TO THE CARTIER JEWELLERY WEBSITE");
        System.out.println("We sell different types of rings ranging from wedding rings to promise rings");
        System.out.println("As well as rings, we also sell bracelets such as charm bracelets and tennis bracelets");

        do 
        {
            /**Task 5 - Displaying main menu option*/
            System.out.println("\nPlease input the menu option you wish to carry out");

            System.out.println("\n1. Customer Menu");
            System.out.println("2. Staff Menu");
            System.out.println("3. Exit System");
            menu = scan.nextLine(); /**Task 5.1 - Input menu option*/

            switch (menu)
            {
                case "1": //if 1 is inputted the customer menu will display and you must hit enter to continue
                    enterReturn();
                    customerMenu(); //calling customer menu method
                    break;

                case "2": //if 2 is inputted the staff menu will display and you must hit enter to continue
                    enterReturn();
                    staffMenu(); //calling staff menu method
                    break;

                case "3":
                    System.out.println("\nExiting Program"); //if 3 is inputted the program will close
                    enterReturn(); //data should be stored in data file
                    break;

                default: /**Task 5.1.1 - Validate menu option*/
                    System.out.println("\nMenu option inputted is invalid"); //Error message if wrong menu option is inputted

            }
        }while (menu.charAt(0) != '3'); /**Task 5.1.2 - Trap if menu option invalid*/
    }

    public void customerMenu()
    {

        Scanner scan = new Scanner(System.in);
        String menu = " "; //Declared as String so program doesn't crash if wrong data type is inputted

        do 
        {
            System.out.print("\f"); 

            System.out.println("\t\tCUSTOMER MENU");
            System.out.println("\t\t==============");

            /**Task 6 - Displaying customer menu option*/
            System.out.println("\nWelcome to the customer menu");
            System.out.println("\nPlease input the menu option you wish to carry out");

            System.out.println("\n1. Display Jewellery Items That We Sell");
            System.out.println("2. Buy Jewellery Items");
            System.out.println("3. Exit Customer Menu and Return To Main Menu");
            menu = scan.nextLine(); /**Task 6.1 - Input customer menu option*/

            switch (menu) /**Task 6.1.1 - Validate menu option*/
            {
                case "1": //if 1 is inputted press enter to continue to see the items that are sold
                    enterReturn();
                    displayItems();
                    break;

                case "2": //if 2 is inputted press enter to continue to buy items
                    enterReturn();
                    buyItems();
                    break;

                case "3": //return to menu
                    enterReturn();
                    break;

                default:
                    System.out.println("\nMenu option inputted is invalid");

            }
        }while (menu.charAt(0) != '3'); /**Task 6.4 - Exit customer menu*/
    }

    public void emptyArray()
    {
        boolean empty = category.isEmpty(); //check if ArrayList is empty
        if (empty == true)
        {
            System.out.println("There is currently no items in stock."); //display message if ArrayList is empty
            System.out.println("Apologies for any inconvience this may cause");
            enterReturn();
            //System.out.print("\f"); 
        }
    }

    public void displayItems()/**Task 6.2 - Display items*/
    {
        System.out.print("\f");
        System.out.println("\t\tDISPLAYING ALL JEWELLERY ITEMS FOR SALE....\n");
        /**Task 6.2.1 - check if ArrayList is empty*/
        emptyArray(); //if ArrayList is empty display message
        for (Jewellery j : category) /**Task 6.2.2 - display items */
        {
            System.out.println(j.toString());            
        }
        enterReturn();
    }

    public void buyItems()/**Task 6.3 - Buy items*/
    {
        emptyArray(); /**Task 6.3.1 - Check if ArrayList is empty*/
        Scanner scan = new Scanner(System.in);
        String jewellery;
        System.out.print("\f"); 

        do
        {
            /**Task 6.3.2 - Ask user which item they wish to buy*/
            System.out.println("\nWhat jewellery item do you wish to purchase? (Ring/Bracelet)");
            System.out.println("Enter 'back' to return to the menu");
            jewellery = scan.nextLine();

            if (jewellery.equalsIgnoreCase("Ring"))
            {
                enterReturn();
                purchaseRing();
            }
            else
            if (jewellery.equalsIgnoreCase("Bracelet"))
            {
                enterReturn();
                purchaseBracelet();
            }
            else
            if (jewellery.equalsIgnoreCase("back"))
            {
                enterReturn();
            }
            else
            {
                System.out.println("\nMenu option inputted is invalid");
            }          
        }while (!jewellery.equalsIgnoreCase("back"));        
    }

    public void purchaseRing() /**Task 6.3 - buy item*/
    {
        Scanner scan = new Scanner(System.in);
        String type, gem, colour;
        double carat;
        double totalCost;

        int quantity;
        boolean found = false;
        boolean stockLevel = true;
        Ring r;

        /**Task 6.3.2.1 - Input details of item*/
        System.out.print("\nEnter the type of ring you wish to purchase: ");
        type = scan.nextLine();
        System.out.print("Enter the gemstone of the ring you wish to purchase: ");
        gem = scan.nextLine();
        System.out.print("Enter the colour of the gemstone of the ring you want to purchase: ");
        colour = scan.nextLine();
        System.out.print("How many carats would you like ring: ");
        carat = scan.nextDouble(); scan.nextLine();
        for (Jewellery j : category)
        {
            if (j instanceof Ring)
            {
                r = (Ring)j;
                /**Task 6.3.2.2 - Verify details*/
                if (type.equalsIgnoreCase(r.getItemType()) && gem.equalsIgnoreCase(r.getGemstone()) && colour.equalsIgnoreCase(r.getItemColour()) && carat == (r.getCarat()))
                {
                    found = true;
                    if (j.stockLevel())
                    {
                        /**Task 6.3.2.3 - If found ask user quantity they wish to purchase*/
                        System.out.println("The item you wish to purchase has been found");
                        System.out.println("This ring costs €" + r.getPrice());
                        System.out.println("\nHow much would like to be purchased?"); 
                        quantity = scan.nextInt(); scan.nextLine();

                        if ((quantity <= r.getStock() && quantity != 0)) 
                        {
                            /**Task 6.3.2.3.3 - Display appropriate message if item can be purchased and total*/
                            totalCost = (r.getPrice()*quantity);
                            System.out.println(quantity + " of this item will cost €" + totalCost + "."); 
                            System.out.println("\nItem has successfully been purchased and order total is €" + totalCost + ".");
                            r.setStock(r.getStock() - quantity);
                            r.setGeneratedSales(r.getGeneratedSales() + totalCost);

                        }
                        else if ((quantity > r.getStock()) || (quantity == 0))
                        {
                            /**Task 6.3.2.3.1 - Display error if quantity is more than stock*/
                            System.out.println("\nError not enough stock available./Number is invalid.");
                            System.out.println("There is only " + r.getStock() + " stock left available.");
                        }
                    }
                    else if (j.stockLevel() == false)
                    {
                        /**Task 6.3.2.3.2 - Display error if item is out of stock*/
                        System.out.println("\nThis item is out of stock");
                        System.out.println("Sorry for any inconvience this may cause.");                        
                    }
                }                
            }
        }

        if (!found)
        {
            /**Task 6.3.2.4 - Display message if item is not found*/
            System.out.println("\nThis item you wish to purchase is not sold in this shop.");
        }
    }

    public void purchaseBracelet() /**Task 6.3 - buy item*/
    {
        Scanner scan = new Scanner(System.in);
        String jewellery, type, colour, metal;
        int quantity;
        double totalCost;

        boolean found = false;
        boolean stockLevel = true;
        Bracelet b;

        /**Task 6.3.2.1 - Input details of item*/
        System.out.print("\nEnter the type of bracelet you wish to purchase: ");
        type = scan.nextLine();
        System.out.print("Enter the metal of the bracelet you wish to purchase: ");
        metal = scan.nextLine();
        System.out.print("Enter the colour of the metal of the bracelet you want to purchase: ");
        colour = scan.nextLine();

        for (Jewellery j : category)
        {
            if (j instanceof Bracelet)
            {
                b = (Bracelet)j;
                /**Task 6.3.2.2 - Verify details*/
                if (type.equalsIgnoreCase(b.getItemType()) && metal.equalsIgnoreCase(b.getMetal()) && colour.equalsIgnoreCase(b.getItemColour()))
                {
                    /**Task 6.3.2.3 - If found ask user quantity they wish to purchase*/
                    found = true;
                    if (j.stockLevel())
                    {
                        System.out.println("The item you wish to purchase has been found");
                        System.out.println("This ring costs €" + b.getPrice());
                        System.out.println("\nHow much would like to be purchased?");
                        quantity = scan.nextInt(); scan.nextLine();

                        if ((quantity <= b.getStock() && quantity != 0))
                        {
                            /**Task 6.3.2.3.3 - Display appropriate message if item can be purchased and total*/
                            totalCost = (b.getPrice()*quantity);
                            System.out.println(quantity + " of this item will cost €" + totalCost + "."); 
                            System.out.println("\nItem has successfully been purchased and order total is €" + totalCost + ".");
                            b.setStock(b.getStock() - quantity); 
                            b.setGeneratedSales(b.getGeneratedSales() + totalCost);
                        }
                        else if ((quantity > b.getStock()) || (quantity == 0))
                        {
                            /**Task 6.3.2.3.1 - Display error if quantity is more than stock*/
                            System.out.println("\nError not enough stock available./Number is invalid.");
                            System.out.println("There is only " + b.getStock() + " stock left available.");
                        }
                    }
                    else if (j.stockLevel() == false)
                    {
                        /**Task 6.3.2.3.2 - Display error if item is out of stock*/
                        System.out.println("\nThis item is out of stock");
                        System.out.println("Sorry for any inconvience this may cause.");                        
                    }
                }                
            }
        }

        if (!found)
        {
            /**Task 6.3.2.4 - Display message if item is not found*/
            System.out.println("\nThis item you wish to purchase is not sold in this shop.");
        }
    }

    public void staffMenu()
    {
        System.out.print("\f"); 
        Scanner scan = new Scanner(System.in);
        String menu = " "; //Declared as String so program doesn't crash if wrong data type is inputted

        do 
        {
            /**Task 7.1 - Displaying staff menu option*/
            System.out.print("\f"); 
            System.out.println("\t\tSTAFF MENU");
            System.out.println("\t\t==============");
            System.out.println("\nWelcome to the staff menu");
            System.out.println("Please input the menu option you wish to carry out");

            System.out.println("\n1. Add New Type of Ring/Bracelet to Stock List");
            System.out.println("2. Display Details of the Best-Selling Jewellery Item");
            System.out.println("3. Update Stock Level for an Existing Ring or Bracelet with a New Delivery");
            System.out.println("4. Discount Rings/Bracelets");
            System.out.println("5. Exit Staff Menu and Return To Main Menu");
            menu = scan.nextLine();

            switch (menu) /**Task 7.1.1 - Validate menu option*/
            {
                case "1":
                    enterReturn();
                    addStock();
                    break;

                case "2":
                    enterReturn();
                    bestSelling();
                    break;

                case "3":
                    enterReturn();
                    updateStock();
                    break;

                case "4":
                    enterReturn();
                    promotionalSale();
                    break;

                case "5":
                    enterReturn();
                    break;

                default:
                    System.out.println("\nMenu option inputted is invalid");

            }
        }while (menu.charAt(0) != '5'); /**Task 7.6 - Return to main menu*/

    }

    public void addStock() /**Task 7.2 - Add new item to stock list*/
    {
        Scanner scan = new Scanner(System.in);
        String jewellery;

        System.out.print("\f");

        System.out.print("\t\tADD STOCK PAGE");

        do
        {
            System.out.println("\nWhat item are you entering? (Ring or Bracelet)");
            System.out.println("Enter 'back' to return to the menu");
            jewellery = scan.nextLine();
            /**Task 7.2.1 - Input item you wish to add new stock under*/

            if (jewellery.equalsIgnoreCase("Ring"))
            {
                enterReturn();
                ring();
            }
            else
            if (jewellery.equalsIgnoreCase("Bracelet"))
            {
                enterReturn();
                bracelet();
            }
            else
            if (jewellery.equalsIgnoreCase("back"))
            {
                enterReturn();
            }
            else
            {
                System.out.println("\nMenu option inputted is invalid");
            }          
        }while (!jewellery.equalsIgnoreCase("back"));
    }

    public void ring()
    {
        Scanner scan = new Scanner(System.in);
        String type, colour, gem;
        int stock;
        double price, carat;
        Ring r;
        System.out.println("\nAdding ring to stock...");

        /**Task 7.2.1.1 - Input item details*/
        System.out.print("\nEnter the type of ring: ");
        type = scan.nextLine();
        System.out.print("Enter the gemstone of the ring: ");
        gem = scan.nextLine();
        System.out.print("Enter the colour of the gemstone: ");
        colour = scan.nextLine();
        System.out.print("How many carats is the ring: ");
        carat = scan.nextDouble(); scan.nextLine();
        System.out.print("Enter the cost of the ring: €");
        price = scan.nextDouble(); scan.nextLine();
        System.out.print("Enter the amount of stock the ring has: ");
        stock = scan.nextInt(); scan.nextLine();

        /**Task 7.2.1.2 - Validate details*/
        duplicateRing(type, colour, gem, carat, price);

        /**Task 7.2.1.3 - Add item to ArrayList*/
        r = new Ring(type, colour, price, stock, 0, gem, carat);
        category.add(r);
    }

    public void duplicateRing(String type, String colour, String gem, double carat, double price)
    {
        /**Task 7.2.1.2 - Validate details*/
        Ring r;

        boolean found = false;

        do
        {
            found = false; 

            for (Jewellery j : category)
            {
                if (j instanceof Ring)
                {
                    r = (Ring)j;
                    if (type.equalsIgnoreCase(r.getItemType()) && colour.equalsIgnoreCase(r.getItemColour()) && gem.equalsIgnoreCase(r.getGemstone()) 
                    && carat == r.getCarat() && price == r.getPrice()) 
                    {    
                        found = true;
                    }
                }
            }

            if (found == true)
            {
                /**Task 7.2.1.2.1 - Display message if item already exists*/
                System.out.println("\nItem already entered. ");
                System.out.println("Please enter a different type!");
            }

        }while (found == true);
    }

    public void bracelet()
    {
        Scanner scan = new Scanner(System.in);
        String type, colour, metal;
        int stock;
        double price;
        Bracelet b;
        System.out.println("\nAdding bracelet to stock...");

        /**Task 7.2.1.1 - Input item details*/
        System.out.print("\nEnter the type of bracelet: ");
        type = scan.nextLine();
        System.out.print("Enter the metal the bracelet is made out of: ");
        metal = scan.nextLine();
        System.out.print("Enter the colour of the metal of the bracelet: ");
        colour = scan.nextLine();
        System.out.print("Enter the cost of the bracelet: €");
        price = scan.nextDouble(); scan.nextLine();
        System.out.print("Enter the amount of stock the ring has: ");
        stock = scan.nextInt(); scan.nextLine();

        /**Task 7.2.1.2 - Validate details*/
        duplicateBracelet(type, colour, metal, price); 

        /**Task 7.2.1.3 - Add item to ArrayList*/
        b = new Bracelet(type, colour, price, stock, 0, metal);
        category.add(b);

    }

    public void duplicateBracelet(String type, String colour, String metal, double price)
    {
        /**Task 7.2.1.2 - Validate details*/
        Scanner scan = new Scanner(System.in);
        Bracelet b;

        boolean found = false;

        do
        {
            found = false; 

            for (Jewellery j : category)
            {
                if (j instanceof Bracelet)
                {
                    b = (Bracelet)j;
                    if (type.equalsIgnoreCase(b.getItemType()) && colour.equalsIgnoreCase(b.getItemColour()) && metal.equalsIgnoreCase(b.getMetal()) 
                    && price == b.getPrice()) 
                    {    
                        found = true;
                    }
                }
            }

            if (found == true)
            {
                /**Task 7.2.1.2.1 - Display message if item already exists*/
                System.out.println("\nItem already entered. ");
                System.out.println("Please enter a different type!");
                type = scan.nextLine();
            }

        }while (found == true);
    }

    public void bestSelling() /**Task 7.3 - Calculate best-selling item*/
    {
        System.out.print("\f"); 
        Jewellery bestSelling = category.get(0);
        System.out.print("Best Selling Item"); 
        emptyArray(); /**Task 7.3.1 - Display message if there are no items*/
        for (Jewellery j : category)
        {
            if (bestSelling.getGeneratedSales() < j.getGeneratedSales())
            {
                bestSelling = j;       
            }
        }

        /**Task 7.2.2 - Display item that made the most money*/
        System.out.println("The item that has generated the most money in terms of sales is " + bestSelling.getItemType() +
            " with a total of €" +  bestSelling.getGeneratedSales());
    }

    public void updateStock() /**Task 7.4 - Update stock of new delivery*/
    {
        Scanner scan = new Scanner(System.in);
        String delivery;
        String item;
        System.out.print("\f"); 

        do
        {
            /**Task 7.4.1 - Ask user whether they want to update stock for an item*/
            System.out.println("\nDo you wish to update stock for a ring or bracelet? (yes/no)");
            delivery = scan.nextLine();

            if (delivery.equalsIgnoreCase("Yes"))
            {
                System.out.println("Is the delivery for a ring or bracelet?");
                item = scan.nextLine();
                /**Task 7.4.1 - Ask user which category is the item under*/
                if (item.equalsIgnoreCase("Ring"))
                {
                    enterReturn();
                    deliveryRing();
                }
                else
                if (item.equalsIgnoreCase("Bracelet"))
                {
                    enterReturn();
                    deliveryBracelet();
                }
                else 
                {
                    System.out.println("Error - invalid input");
                }
            }
            else
            if (delivery.equalsIgnoreCase("No"))
            {
                /**Task 7.4.1.2 - Return to staff menu*/
                System.out.println("\nReturning to menu");
                enterReturn();
            }
            else
            {
                System.out.println("Invalid input");
            }
        }while (!delivery.equalsIgnoreCase("No"));
    }

    public void deliveryRing()
    {
        Scanner scan = new Scanner(System.in);
        String item, colour, gemstone;
        double price, carat;
        int deliveredStock; 
        Ring r;
        boolean found = false;

        /**Task 7.4.1.1.1 - Ask user for details of the item*/
        System.out.print("What type of ring is the delivery for?");
        item = scan.nextLine();
        System.out.print("What is the price of the ring the delivery is for?");
        price = scan.nextDouble(); scan.nextLine();
        System.out.print("What is the gemstone of the ring the delivery is for?");
        gemstone = scan.nextLine();
        System.out.print("What is the colour of the gemstone the delivery is for?");
        colour = scan.nextLine();
        System.out.print("How many carat is the ring the delivery is for?");
        carat = scan.nextDouble(); scan.nextLine();

        for (Jewellery j : category)
        {
            if (j instanceof Ring)
            {
                r = (Ring)j;
                if (item.equalsIgnoreCase(r.getItemType()) && colour.equalsIgnoreCase(r.getItemColour()) && gemstone.equalsIgnoreCase(r.getGemstone()) 
                && carat == r.getCarat() && price == r.getPrice()) 
                {    
                    found = true;
                    /**Task 7.4.1.1.2 - If found ask user how much the want to update stock by*/
                    System.out.println("\nItem has been found");
                    System.out.println("How much stock has been delivered?");
                    deliveredStock = scan.nextInt(); scan.nextLine();

                    /**Task 7.4.1.1.2.1 - Update stock accordingly*/
                    r.setStock(r.getStock() + deliveredStock); 
                    System.out.println("\nDelivery has been accepted.");
                    System.out.println("There is now " + r.getStock() + " stock of " + r.getItemType());
                }
            }
        }

        if (!found)
        {
            /**Task 7.4.1.1.3 - Display message is item is not found*/
            System.out.println("\nThis item is not sold in this shop.");
            System.out.println("Delivery can not be accepted.");
        }
    }

    public void deliveryBracelet()
    {
        Scanner scan = new Scanner(System.in);
        String item, colour, metal;
        double price;
        int deliveredStock; 
        Bracelet b;
        boolean found = false;

        /**Task 7.4.1.1.1 - Ask user for details of the item*/
        System.out.print("What type of bracelet is the delivery for?");
        item = scan.nextLine();
        System.out.print("What is the price of the bracelet the delivery is for?");
        price = scan.nextDouble(); scan.nextLine();
        System.out.print("What is the metal of the bracelet the delivery is for?");
        metal = scan.nextLine();
        System.out.print("What is the colour of the metal the delivery is for?");
        colour = scan.nextLine();

        for (Jewellery j : category)
        {
            if (j instanceof Bracelet)
            {
                b = (Bracelet)j;
                if (item.equalsIgnoreCase(b.getItemType()) && colour.equalsIgnoreCase(b.getItemColour()) && metal.equalsIgnoreCase(b.getMetal()) 
                && price == b.getPrice()) 
                {    
                    found = true;
                    /**Task 7.4.1.1.2 - If found ask user how much the want to update stock by*/
                    System.out.println("\nItem has been found");
                    System.out.println("How much stock has been delivered?");
                    deliveredStock = scan.nextInt(); scan.nextLine();

                    /**Task 7.4.1.1.2.1 - Update stock accordingly*/
                    b.setStock(b.getStock() + deliveredStock); 
                    System.out.println("\nDelivery has been accepted.");
                    System.out.println("There is now " + b.getStock() + " stock of " + b.getItemType());
                }
            }
        }

        if (!found)
        {
            /**Task 7.4.1.1.3 - Display message is item is not found*/
            System.out.println("\nThis item is not sold in this shop.");
            System.out.println("Delivery can not be accepted.");
        }
    }

    public void promotionalSale() /**Task 7.5 - discount the price of an item*/
    {
        Scanner scan = new Scanner(System.in);
        String setOnSale;
        String item;
        System.out.print("\f"); 

        do
        {
            System.out.println("\nDo you wish to do a promotional sale for a ring or bracelet? (yes/no)");
            setOnSale = scan.nextLine();
            /**Task 7.5.1 - Ask usee if they want to set an item on sale*/
            if (setOnSale.equalsIgnoreCase("Yes"))
            {
                System.out.println("Enter whether the promotional sale is for a ring or bracelet?");
                item = scan.nextLine();
                /**Task 7.5.1.1 - Ask which category is the item under*/
                if (item.equalsIgnoreCase("Ring"))
                {
                    enterReturn();
                    discountRing();
                }
                else
                if (item.equalsIgnoreCase("Bracelet"))
                {
                    enterReturn();
                    discountBracelet();
                }
                else 
                {
                    System.out.println("Error - invalid input");
                }
            }
            else
            if (setOnSale.equalsIgnoreCase("No"))
            {
                /**Task 7.5.1.2 - Retrun to staff menu*/
                System.out.println("\nReturning to menu");
                enterReturn();
            }
            else
            {
                System.out.println("Invalid input");
            }
        }while (!setOnSale.equalsIgnoreCase("No"));

    }

    public void discountRing()
    {
        Scanner scan = new Scanner(System.in);
        String item, colour, gemstone;
        double price, carat; 
        double percentage;
        double discount;
        Ring r;
        boolean found = false;
        boolean discountApplied = false;

        /**Task 7.5.1.1.1 - Ask user details of the item*/
        System.out.print("What type of ring do you wish to discount?");
        item = scan.nextLine();
        System.out.print("What is the price of the ring?");
        price = scan.nextDouble(); scan.nextLine();
        System.out.print("What is the gemstone of the ring?");
        gemstone = scan.nextLine();
        System.out.print("What is the colour of the gemstone?");
        colour = scan.nextLine();
        System.out.print("How many carat is the ring?");
        carat = scan.nextDouble(); scan.nextLine();

        for (Jewellery j : category)
        {
            if (j instanceof Ring)
            {
                r = (Ring)j;
                if (item.equalsIgnoreCase(r.getItemType()) && colour.equalsIgnoreCase(r.getItemColour()) && gemstone.equalsIgnoreCase(r.getGemstone()) 
                && carat == r.getCarat() && price == r.getPrice()) 
                {    
                    found = true;
                    discountApplied = false;
                    /**Task 7.5.1.1.2 - Ask user what percentage they wish to take off*/
                    if (j.sale())
                    {
                        discountApplied = true;
                        System.out.println("Item has been found");
                        System.out.println("What percentage off would you like the " + r.getItemType() + " to be?");
                        percentage = scan.nextInt(); scan.nextLine();

                        discount = (r.getPrice()*percentage)/100;

                        System.out.println("\nThe original price was " + r.getPrice());
                        /**Task 7.5.1.1.2.1 - Update the price*/
                        r.setPrice((r.getPrice()-discount)); 
                        System.out.println("A " + percentage + "% discount has been applied to " + r.getItemType());
                        System.out.println("It now cost €" + r.getPrice());
                    }
                    
                }
            }
        }

        if (!found)
        {
            System.out.println("\nThis item is not sold in this shop.");
        }
        
        if (!discountApplied)
        {
            System.out.println("\nDiscount can't be applied due to the materials used to manufacture.");
        }
    }

    public void discountBracelet()
    {
        Scanner scan = new Scanner(System.in);
        String item, colour, metal;
        double price;
        double percentage;
        double discount;
        Bracelet b;
        boolean found = false;
        boolean discountApplied = false;

        /**Task 7.5.1.1.1 - Ask user details of the item*/
        System.out.print("What type of bracelet is the discount being applied to?");
        item = scan.nextLine();
        System.out.print("What is the price of the bracelet?");
        price = scan.nextDouble(); scan.nextLine();
        System.out.print("What is the metal of the bracelet?");
        metal = scan.nextLine();
        System.out.print("What is the colour of the metal?");
        colour = scan.nextLine();

        for (Jewellery j : category)
        {
            if (j instanceof Bracelet)
            {
                b = (Bracelet)j;
                if (item.equalsIgnoreCase(b.getItemType()) && colour.equalsIgnoreCase(b.getItemColour()) && metal.equalsIgnoreCase(b.getMetal()) 
                && price == b.getPrice()) 
                {    
                    found = true;
                    discountApplied = false;
                    /**Task 7.5.1.1.2 - Ask user what percentage they wish to take off*/
                    if (j.sale())
                    {
                        discountApplied = true; 
                        
                        System.out.println("Item has been found");
                        System.out.println("What percentage off would you like the " + b.getItemType() + " to be?");
                        percentage = scan.nextInt(); scan.nextLine();

                        discount = (b.getPrice()*percentage)/100;

                        System.out.println("\nThe original price was " + b.getPrice());
                        /**Task 7.5.1.1.2.1 - Update the price*/
                        b.setPrice((b.getPrice()-discount)); 
                        System.out.println("A " + discount + "% discount has been applied to " + b.getItemType());
                        System.out.println("It now cost €" + b.getPrice());
                    }
                    
                }
            }
        }

        if (!found)
        {
            /**Task 7.5.1.1.3 - Display error message if not found*/
            System.out.println("\nThis item is not sold in this shop.");
        }
        
        if (!discountApplied)
        {
            System.out.println("\nDiscount can't be applied due to this item.");
        }
    }

    public static void main(String[] args)
    {
        new CartierStore();
    }
}

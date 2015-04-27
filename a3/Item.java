
/**
 * Class for an Item for a room in the PubCrawl. An item has a description and a price. 
 * 
 * Exercise 6.20
 * 
 * @author ann wallace
 * @version 20150411
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String name;
    private String description;
    private double price;

    /**
     * Constructor for objects of class Item
     * @param item - description of the item, price - how much the item cost
     */
    public Item(String name, String description, double price)
    {
        // initialise instance variables
        this.name = name;
        this.description = description;
        this.price = price;
    }
    
    /**
     * Get the name of an item
     * @return String - the name of an item
     */
    public String getName ()
    {
        return name;
    }

    /**
     * Get the description of an item
     * @return  string a description of the item
     */
    public String getDescription ()
    {
        return description;
    }
    
     /**
     * Get the price of an item
     * @return double - the price of an item
     */
    public double getPrice ()
    {
        return price;
    }
}

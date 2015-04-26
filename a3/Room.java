import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  ann wallace (was Michael Kölling and David J. Barnes)
 * @version 2015.04.17
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private ArrayList<Item> items;


    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new ArrayList<Item>();
      
    }
    
    /**
     * Create an empty room
     */
    public Room() {
    }

    /**
     * Define the exits of this room.  
     * @param direction - The direction of the exit.
     * @param neighbor - the room in the given direction
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    
    /**
     * Create an item in a room
     * @param item - description and price
     */
    public void addItem(Item newItem) {
        items.add(newItem);
    }
    
    /**
     * Return all the items in a room
     * @return a list of items
     */
    public ArrayList<Item> getItems() {
        return items;
    }
    
    /**
     * Return a description of the item in the room
     * @return a string with the desription and the price of the item
     */ 
    public String getItemDescription() {
        String msg = new String("Items are: \n");
        int i = 1;
        for(Item item : items) {
            msg += i + ". " + item.getDescription() + " that cost $" + item.getPrice() + "\n";
            i++;
        }
        return msg;
    }
    
    /**
     * getExit - returns the different exits assocaited with a room
     * @return Room
     */
    public Room getExit(String direction) {
        return exits.get(direction);
    }
    
    /**
     * Return the description of the room that was defined in the constructor
     */
    public String getDescription() {
        return this.description;
    }
    
    /**
     * Return a description of the room's exits.
     * Example: "Exits: north west south".
     * @return a description of the available exits
     */
    public String getExitString() {
      String msg = new String("Exits: ");
      Set<String> keys = exits.keySet();
      for(String exit : keys) {
          msg += " " + exit;
      }
      return msg;
    }

   
    
    /**
     * return a long description of this room, of the form:
     *  You are in the kitchen.
     *  Exits: north south
     *  Items : XXXXX
     * @return a description of the room, including exits
     */
    public String getLongDescription() {
        return "you are " + description + ".\n" + getExitString() + 
        "\n" + getItemDescription();
    }

}

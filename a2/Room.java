import java.util.HashMap;
import java.util.Set;

/**
 * Class Room - a room in an adventure game.
 *
 * This was World of Zuul, now it is PubCrawl. Your goal is to drink beer at all the 
 *  pubs and get home with money still in your pocket. Beware not every place you go into is a pub. 
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  ann wallace (was Michael Kölling and David J. Barnes)
 * @version 20150405
 */
public class Room 
{
    public String description;
    // exercise 8.8
    private HashMap<String, Room> exits;
  

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        // exercise 6.8
        exits = new HashMap<String, Room>();
    }


    /**
     * exercsie 6.6
     * getExit - returns the room with the given direction
     * @param direction the direction to exit the room - vaild (north, east, south, west)
     * @return the room in the given direction 
     */
    //public Room getExit(String direction) {
    //    if(direction.equals("north")) {
    //        return northExit;
    //    }
    //    if(direction.equals("east")) {
    //        return eastExit;
    //    }
    //    if(direction.equals("south")) {
    //        return southExit;
    //    }
    //    if(direction.equals("west")) {
    //        return westExit;
    //    }
    //    return null;
    //}
    
    /**
     * exercise 6.8
     * getExit - returns the different exits assocaited with a room
     * @return Room that is reached in the direction 
     */
    public Room getExit(String direction) {
        return exits.get(direction);
    }
    
     /**
     * exercise 6.8
     * Define the exits of this room.  
     * @param direction - The direction of the exit.
     * @param neighbor - the room in the given direction
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    
     /**
     * exercise 6.11
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
     * exercise 6.11
     * return a long description of this room, of the form:
     *  You are in the kitchen.
     *  Exits: north south
     * @return a description of the room, including exits
     */
    public String getLongDescription() {
        return "you are " + description + ".\n" + getExitString();
    }
    
    /**
     * exercise 6.7
     * Return a description of the room's exits.
     * Example: "Exits: north west south".
     * @return a description of the available exits
     */
    //public String getExitString() {
    //    String msg = new String("Exits: ");
    //    if(this.northExit != null) {
    //        msg += "north ";
    //    }
    //    if(this.eastExit != null) {
    //        msg += "east ";
    //    }
    //    if(this.southExit != null) {
    //        msg += "south ";
    //    }
    //    if(this.westExit != null) {
    //        msg += "west ";
    //    }
    //    
    //    return msg;
    //}

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

}

import java.util.HashMap;
import java.util.Map;
/**
 * Player in the game of PubCrawl
 * A player is in a room, drinks beer and has a wallet
 * 
 * @ann wallace 
 * @20150422
 */
public class Player
{
    // instance variables - replace the example below with your own
    private String name;
    private Room currentRoom;
    private Wallet wallet;
    private HashMap<String, Item> inventory;
    private OLCC olcc;

    /**
     * Constructor for objects of class Player
     */
    public Player(String name)
    {
        // initialise instance variables
        this.name = name;
        // start out with $100 in your wallet
        wallet = new Wallet(100);
        inventory = new HashMap<String, Item>();
        currentRoom = null;
        olcc = new OLCC();

    }

    /**
     * Return the name of the play
     * 
     * @return  name 
     */
    public String getName()
    {
        // put your code here
        return name;
    }
    
    /**
     * set the current room location
     * @param room - the current room
     */
    public void setRoom(Room room) {
        currentRoom = room;
    }
    
    /**
     * get the current room location
     * @return room - the current room
     */
    public Room getRoom() {
        return currentRoom;
    }
    
    /**
     * get total abount of money in the wallet
     * @return string - money in wallet
     */
    public String getWalletTotal() {
        return "$" + wallet.getMoney();
    }
    
    /**
     * add an item to your inventory
     */
    private void addToInventory(Item item) {
        inventory.put(item.getName(), item);
    }
    
    /**
     * return all items in your inventory
     * @return String of all the items in the Inventory 
     */
    public String getInventoryString() {
        String msg = new String("Inventory: \n");
        for (Map.Entry<String, Item> me : inventory.entrySet()) {
            msg += " item: " + me.getKey()  + " description: " + me.getValue().getDescription() + "\n";
        }
        return msg;
    }
            
    
    /**
     * buy item
     * removes the item from the current room
     * adds the item to your inventory
     * deducts the price from your wallet
     * @param Item that one wants to buy
     */
    public void buy(Item item) {
       // remove money from wallet
       try { 
            wallet.removeMoney(item.getPrice());
            // remove item from room
            currentRoom.removeItem(item);
            // add item to inventory
            addToInventory(item);
        } catch (IllegalArgumentException e) {
            System.err.println("You don't have enough money to purchase " + item.getDescription());
        }


    }
    
    /**
     * drink a beer and remove it from the inventory
     * @param a beer one wants to drink
     * @return description of the item drank
     * @throws IllegalArgumentException - if something other than a beer is passed
     */
    public String drink(String itemName) {
        Item item = inventory.get(itemName);
        //check to make sure you are drinking a beer
        if (!olcc.isABeer(item)) {
           // throw an exception if not a beer
            throw new IllegalArgumentException("Cannot drink " + itemName);
        } 
        // drink beer and remove it from the inventory
        inventory.remove(item.getName());
        return item.getDescription();
    }

}

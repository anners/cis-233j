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
        inventory.put(currentRoom.getDescription(), item);
    }
    
    /**
     * return all items in your inventory
     */
    public void getInventoryString() {
        String msg = new String("Inventory: ");
        for (Map.Entry<String, Item> me : inventory.entrySet()) {
            System.out.println("Key: "+me.getKey()+ " & Value: " + me.getValue().getDescription());
        }
    }
            
    
    /**
     * buy item
     */
    public void buy(Item item) {
        // remove item from room
        // add item to inventory
        addToInventory(item);
        // remove money from wallet
    }
    
    /**
     * drink
     */
    public void drink() {
        // drink beer and remove it from the inventory
    }

}

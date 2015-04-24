
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
    private Room room;
    private Wallet wallet;

    /**
     * Constructor for objects of class Player
     */
    public Player(String name)
    {
        // initialise instance variables
        this.name = name;
        // start out with $100 in your wallet
        wallet = new Wallet(100);
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
        this.room = room;
    }
    
    /**
     * get total abount of money in the wallet
     * @return string - money in wallet
     */
    public String getWalletTotal() {
        return "$" + wallet.getMoney();
    }
    
    /**
     * buy item
     */
    public void buy(Item item) {
        // add item to inventory
        // remove money from wallet
    }
    
    /**
     * drink
     */
    public void drink() {
        // drink beer and remove it from the inventory
    }
    
    /**
     * Return the room the player is currently in
     * @return room
     */
    public Room getRoom() {
        return room;
    }
}

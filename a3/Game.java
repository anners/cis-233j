import java.util.Stack;
/**
*  This was World of Zuul, now it is PubCrawl. Your goal is to drink beer at all the 
 *  pubs and get home with money still in your pocket. Beware not every place you go into is a pub.
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Ann Wallace (was Michael Kölling and David J. Barnes)
 * @version 20150401
 */

public class Game 
{
    private Parser parser;
    private Stack<Room> previousRoom;
    private Player player1;
   
    
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        player1 = new Player("Player 1");
        createRooms();
        parser = new Parser();
        previousRoom = new Stack<Room>();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room work, pp, gdPub, gym, bsPub, hbPub, aPub, home;
        Item laptop, gum, dmIPA, clifBar, cruxIPA, boneyardIPA, rrStout, bed, towel;
        
      
        // create the rooms
        work = new Room("your boring office");
        pp = new Room("convenient store");
        gdPub = new Room("green dragon pub");
        gym = new Room("the smelly gym");
        bsPub = new Room("belmont station pub");
        hbPub = new Room("horse brass pub");
        aPub = new Room("apex pub");
        home = new Room("your house");
        
        // create items
        laptop = new Item("laptop", "a macbook pro", 1000.00);
        gum = new Item("gum", "a pack of gum", 1.00);
        dmIPA = new Item("beer", "a double mountain IPA", 5.00);
        clifBar = new Item("clifbar","a Peanut Butter Clif Bar", 2.00);
        cruxIPA = new Item("beer","a Crux Double IPA", 8.00);
        boneyardIPA = new Item("beer", "a Boneyard RPM", 4.00);
        rrStout = new Item("beer","a Russian River Stout", 6.50);
        bed = new Item("bed", "a nice comfy bed", 0.00);
        towel = new Item("towel", "a towel for the shower", 5.00);
        
        
        //add items to rooms
        work.addItem(laptop);
        pp.addItem(gum);
        pp.addItem(clifBar);
        gdPub.addItem(dmIPA);
        gdPub.addItem(cruxIPA);
        gdPub.addItem(rrStout);
        bsPub.addItem(dmIPA);
        bsPub.addItem(cruxIPA);
        bsPub.addItem(boneyardIPA);
        hbPub.addItem(rrStout);
        hbPub.addItem(cruxIPA);
        hbPub.addItem(boneyardIPA);
        aPub.addItem(rrStout);
        aPub.addItem(cruxIPA);
        gym.addItem(towel);
        home.addItem(bed);
        
        // initialise room exits (Room north, Room east, Room south, Room west) 
        work.setExit("east", gdPub);
        work.setExit("south", hbPub);
        work.setExit("west", pp);
        pp.setExit("east", work);
        pp.setExit("south", bsPub);
        gdPub.setExit("south", gym);
        gdPub.setExit("west", work);
        gym.setExit("north", gdPub);
        gym.setExit("south", aPub);
        bsPub.setExit("north", pp);
        bsPub.setExit("east", hbPub);
        hbPub.setExit("north", work);
        hbPub.setExit("east", aPub);
        hbPub.setExit("south", home);
        hbPub.setExit("west", bsPub);
        aPub.setExit("north", gym);
        aPub.setExit("west", hbPub);
        home.setExit("north", hbPub);
        
        player1.setRoom(work);  // start game at work
        
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Hello " + player1.getName());
        System.out.println("Welcome to Pub Crawl!");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
        printWalletBalance();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * 6.14 added look command
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("look")) {
            look();
        }
        else if (commandWord.equals("drink")) {
            drink(command);
        }
        else if (commandWord.equals("inventory")) {
            printInventory();
        }
        else if (commandWord.equals("buy")) {
            buy(command);
        }
        else if (commandWord.equals("balance")) {
            printWalletBalance();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("back")) {
            goBackRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }
    
    /**
     * Print out the current room description 
     * @return nothing 
     */
    private void look () {
        System.out.println(player1.getRoom().getLongDescription());
    }

    /**
     * buy an item from the current room and add it to the player's inventory
     * @param - command second work is the number of the item to buy
     */
    private void buy (Command command) {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what item to buy...
            System.out.println("Buy what?");
            System.out.println("Please enter an item number");
            return;
        }
        
        int itemNumber;
        try { 
            itemNumber = Integer.parseInt(command.getSecondWord());
            try {
                Item item = player1.getRoom().getItems().get(itemNumber-1);
                player1.buy(item);
            } catch (IndexOutOfBoundsException e) {
                System.err.println(itemNumber + " is not valid");
            }
        } catch (NumberFormatException e) {
            System.err.println("Could not parse item number" + command.getSecondWord());
        }
    }
    
    /**
     * stub method for drinking beer 
     * for now it just prints out you aren't thristy 
     * TODO - drink beer
     */
    private void drink (Command command) {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what item to buy...
            System.out.println("Drink what?");
            System.out.println("Please enter an item name");
            System.out.println("Type inventory to see what you are carrying");
            return;
        } else {
            try {
                String itemName = command.getSecondWord();
                String description = player1.drink(itemName);
                System.out.println(player1.getName()+ " just drank " + description);
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
    }
    
    /**
     * prints out information about items in the room
     * exercise 6.20
     * @return nothing
     */
    private void printItemInfo() {
        System.out.println(player1.getRoom().getItemDescription());
    }
    
    /**
     * prints out the invetory of the player
     * @return nothing
     */
    private void printInventory() {
        System.out.println(player1.getInventoryString());
    }


    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     * updated for exercise 6.16 and 6.18
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around looking for bars to drink beer in");
        System.out.println("until you finally make your way home.");
        System.out.println();
        System.out.println("Your goal is to buy a beer from each bar and drink it");
        System.out.println("and to still have money in your wallet when you get home.");
        System.out.println("You cannot have more than 1 beer in your inventory at a time.");
        System.out.println("You cannot leave a bar with a beer in your inventory.");
        System.out.println("You can thank the OLCC for this.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
    }
    
    /**
     * Print out the balance in the wallet
     * exercise 6.15
     */
    private void printWalletBalance() {
        System.out.println("You have " + player1.getWalletTotal() +" in your wallet.");
    }

    /**
     * Go back to the previous room, if there wasn't a previous room print an error
     */
    private void goBackRoom(Command command) {
        if(command.hasSecondWord()) {
            System.out.println("What???? If you want to go back, simply type back");
            return;
        }else if (previousRoom.empty()) {
            System.out.println("There is no where to go back to.");
            return;
        } else {
            player1.setRoom(previousRoom.pop());
            printLocationInfo();
            printWalletBalance();
        }
    }
            
    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player1.getRoom().getExit(direction);
        
        if (nextRoom == null) {
            System.out.println("There is no door! You must be drunk!");
        }
        else {
            previousRoom.push(player1.getRoom());
            player1.setRoom(nextRoom);
            printLocationInfo();
            printWalletBalance();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * printLocationInfo - prints the current room and the directions of the rooms that are available
     * @return nothing
     */
     private void printLocationInfo(){
         System.out.println(player1.getRoom().getLongDescription());
    }
    
    
            

}

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
    private Room currentRoom;
    private Stack<Room> previousRoom;
    private Player player1;
    
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        player1 = new Player("Player 1");
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
        work = new Room("inside your boring office");
        pp = new Room("in a convenient store");
        gdPub = new Room("in the green dragon pub");
        gym = new Room("in the smelly gym");
        bsPub = new Room("in the belmont station pub");
        hbPub = new Room("in the horse brass pub");
        aPub = new Room("in the apex pub");
        home = new Room("in your house");
        
        // create items
        laptop = new Item("a macbook pro", 1000.00);
        gum = new Item("a pack of gum", 1.00);
        dmIPA = new Item("a double mountain IPA", 5.00);
        clifBar = new Item("a Peanut Butter Clif Bar", 2.00);
        cruxIPA = new Item("a Crux Double IPA", 8.00);
        boneyardIPA = new Item("a Boneyard RPM", 4.00);
        rrStout = new Item("a Russian River Stout", 6.50);
        bed = new Item("a nice comfy bed", 0.00);
        towel = new Item("a towel for the shower", 5.00);
        
        
        //add items to rooms
        work.addItem("laptop", laptop);
        pp.addItem("gum", gum);
        pp.addItem("trail bar", clifBar);
        gdPub.addItem("beer 1", dmIPA);
        gdPub.addItem("beer 2", cruxIPA);
        gdPub.addItem("beer 3", rrStout);
        bsPub.addItem("beer 1", dmIPA);
        bsPub.addItem("beer 2", cruxIPA);
        bsPub.addItem("beer 3", boneyardIPA);
        hbPub.addItem("beer 1", rrStout);
        hbPub.addItem("beer 2", cruxIPA);
        hbPub.addItem("beer 3", boneyardIPA);
        aPub.addItem("beer 1", rrStout);
        aPub.addItem("beer 2", cruxIPA);
        gym.addItem("towel", towel);
        home.addItem("bed", bed);
        
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
        
        currentRoom = work;  // start game at work
        
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
        printWalletBalance();
        System.out.println();
        printLocationInfo();
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
            drink();
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
     * exercise 6.14 - this is a duplication of printLocationInfo() BAD DESIGN 
     * @return nothing 
     */
    private void look () {
        System.out.println(currentRoom.getLongDescription());
    }
    
    /**
     * stub method for drinking beer 
     * for now it just prints out you aren't thristy 
     * TODO - drink beer
     */
    private void drink () {
        System.out.println("You aren't thristy, try again later");
    }
    
    /**
     * prints out information about items in the room
     * exercise 6.20
     * @return nothing
     */
    private void printItemInfo() {
        System.out.println(currentRoom.getItemDescription());
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     * updated for exercise 6.16 and 6.18
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at work.");
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
            currentRoom = previousRoom.pop();
            printLocationInfo();
            printItemInfo();
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
        Room nextRoom = currentRoom.getExit(direction);
        
        if (nextRoom == null) {
            System.out.println("There is no door! You must be drunk!");
        }
        else {
            previousRoom.push(currentRoom);
            currentRoom = nextRoom;
            printLocationInfo();
            printItemInfo();
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
         System.out.println(currentRoom.getLongDescription());
    }

}

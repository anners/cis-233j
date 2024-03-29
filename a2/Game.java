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
 * @version 20150405
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room work, pp, gdPub, gym, bsPub, hbPub, aPub, home;
      
        // create the rooms
        work = new Room("inside your boring office");
        pp = new Room("in a convenient store");
        gdPub = new Room("in the green dragon pub");
        gym = new Room("in the smelly gym");
        bsPub = new Room("in the belmont station pub");
        hbPub = new Room("in the horse brass pub");
        aPub = new Room("in the apex pub");
        home = new Room("in your house");
        
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
        
        currentRoom = work;  // start game work
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
        System.out.println("Welcome to Pub Crawl!");

        System.out.println("Type 'help' if you need help.");
        System.out.println();
        
        printLocationInfo();
     
    }
    
    /**
     * exercise 6.5
     * Print out the room description and the directions of the exits
     */
   // private void printLocationInfo() {
   //     System.out.println("You are " + currentRoom.getDescription());
   //     System.out.print("Exits: ");
   //     if(currentRoom.northExit != null) {
   //         System.out.print("north ");
   //     }
   //     if(currentRoom.eastExit != null) {
   //         System.out.print("east ");
   //     }
   //      if(currentRoom.southExit != null) {
   //         System.out.print("south ");
   //      }
   //      if(currentRoom.westExit != null) {
   //          System.out.print("west ");
   //      }
   //      System.out.println();
   //  }
   
    /**
     * exercise 6.7
     * Print out the room description and the directions of the exits
     */
    //private void printLocationInfo() {
    //    System.out.println("You are " + currentRoom.getDescription());
    //    System.out.println(currentRoom.getExitString());
    //}
    
    /**
     * Given a command, process (that is: execute) the command.
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
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at work.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help");
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
        // exercise 6.5
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door! You must be drunk!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
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
     * exercise 6.11
     * printLocationInfo - prints the current room and the directions of the rooms that are available
     * @return nothing
     */
     private void printLocationInfo(){
         System.out.println(currentRoom.getLongDescription());
     }
}

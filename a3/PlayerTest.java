

import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import org.junit.rules.ExpectedException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class PlayerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PlayerTest
{
    private Player player;
    private Room room;
    private Item item;
    /**
     * Default constructor for test class PlayerTest
     */
    public PlayerTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        player = new Player("User");
        room = new Room("a test room");
        item = new Item("item", "a test item", 5.00);
        room.addItem(item);
        player.setRoom(room);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    /**
     * test the name returns correctly
     */
    @Test
    public void getName() {
        assertEquals("User", player.getName());
    }
    
    /**
     * buy and item and make sure $5 is removed from wallet
     */
    @Test
    public void buyAnItemAndCheckWalletBalance() {
        player.buy(item);
        assertEquals("$95.0", player.getWalletTotal());
    }
    
    /**
     * check the nventory for an item
     *
     */
    @Test
    public void checkInventoryForIceCream() {
        Item icecream = new Item("icecream", "salt and straw special flavor", 9.00);
        room.addItem(icecream);
        player.buy(icecream);
        assertThat(player.getInventoryString(), containsString("icecream"));
    }
        
    /**
     * check that you can drink beer
     */   
    @Test     
    public void drinkBeer() {
        Item beer = new Item("beer", "a good Porter", 5.00);
        room.addItem(beer);
        player.buy(beer);
        assertEquals("a good Porter", player.drink(beer.getName()));
    }


    /**
     * check that drink method throws an exception when something other than
     * beer is passed
     */
    @Test (expected = IllegalArgumentException.class)
    public void throwsExceptionWhenBeerIsNotPassed() {
        player.buy(item);
        player.drink(item.getName());
    }
        
        

}

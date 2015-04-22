

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class WalletTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class WalletTest
{
    private Wallet wallet;
    /**
     * Default constructor for test class WalletTest
     */
    public WalletTest()
    {
         
    }

    @Test 
    public void removeFourDollarsFromWallet() {
       
        int expected = 6;
        wallet.removeMoney(4);
        int actual = wallet.getMoney();
        assertEquals(expected, actual);
    }
    
    @Test 
    public void addTenDollarsToWallet() {
       
        int expected = 20;
        wallet.addMoney(10);
        int actual = wallet.getMoney();
        assertEquals(expected, actual);
    }
        
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        wallet = new Wallet(10);
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
}

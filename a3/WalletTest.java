

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class WalletTest.
 *
 * @author  ann wallace
 * @version 20150421
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
        double actual = wallet.getMoney();
        assertEquals(expected, actual, 0);
    }
    
    @Test 
    public void addTenDollarsToWallet() {
       
        double expected = 20;
        wallet.addMoney(10);
        double actual = wallet.getMoney();
        assertEquals(expected, actual, 0);
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

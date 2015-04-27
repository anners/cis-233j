
/**
 * Class for all the annoying things the OLCC does
 * 
 * @ann wallace
 * @20150426
 */
public class OLCC
{
    // instance variables - replace the example below with your own


    /**
     * Constructor for objects of class OLCC
     */
    public OLCC()
    {

    }

   /**
     * isABeer - check to see if an item is a beer or not
     * @return boolean - ture if an item is a beer false if not
     */
    public boolean isABeer(Item item) {
        boolean beerTest;
        if (item.getName().equals("beer")) {
            beerTest = true;
        } else {
            beerTest = false;
        }
        return beerTest;
    }
}

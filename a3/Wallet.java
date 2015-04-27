
/**
 * This is a simple Wallet class. This class stores money in a wallet, you are able to add and remove
 * money from the wallet.
 * 
 * @author ann wallace
 * @version 20150405
 */
public class Wallet
{
    
    private double money;

    /**
     * Constructor for objects of class Wallet
     * @param money how much money is in the Wallet
     */
    public Wallet(double money) {
        this.money = money;
    }
    
    /**
     * return the amount of money in the wallet. 
     * 
     * @return money - the current amount of money in the wallet
     */
    public double getMoney() {
        return money;
    }

    /**
     * Add cash to the wallet. 
     * 
     * @param  cash  the amount of money you want to add to your wallet
     * @return  void
     */
    public void addMoney(double cash) {
        money += cash;
    }
    
    /**
     * remove cash from the wallet. 
     * 
     * @param  cash  the amount of money you want to remove from your wallet
     * @return  void
     * @throws XX if cash is > than money in the wallet
     */
    public void removeMoney(double cash) {
        
        if (cash > money) {
            throw new IllegalArgumentException ("You cannot remove more money than you have in your wallet.");
        } else {
        money -= cash;
        }
    }
}

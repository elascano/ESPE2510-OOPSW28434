package ec.edu.espe.hwcomposite.model;
/**
 *
 * @author Kevin Chalan, OBJECT MASTER, OOP
 */
public class President extends Supervisor {
    private static President president = new President();
   private President(String aName) {
        this(); 
        name = aName;
    }

    private President() {
        super();
        title = "President";
    }

    @Override
    public void stateName() {
       super.stateName();
    }

   public static President getPresident(String aName) {
        president.name = aName;
        return President.president;
    }
}
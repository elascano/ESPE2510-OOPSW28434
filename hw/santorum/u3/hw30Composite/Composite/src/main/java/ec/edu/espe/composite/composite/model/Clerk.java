
package ec.edu.espe.composite.composite.model;

/**
 *
 * @author Thais Santórum
 */
class Clerk extends Employee {
    public Clerk( String aName ) {
        this();
        name = aName;
    }
    public void stateName() {
        super.stateName();
    }
    public Clerk() {
        title = "Clerk";
    }
}

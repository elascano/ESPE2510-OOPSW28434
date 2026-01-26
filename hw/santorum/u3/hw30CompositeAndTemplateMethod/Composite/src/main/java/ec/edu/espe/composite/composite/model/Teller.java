
package ec.edu.espe.composite.composite.model;

/**
 *
 * @author Thais Santórum
 */
class Teller extends Employee {
    public Teller( String aName ) {
        this();
        name = aName;
    }
    public void stateName() {
        super.stateName();
    }
    public Teller() {
        title = "Teller";
    }
}

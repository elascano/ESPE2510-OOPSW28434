
package ec.edu.espe.ws34abstractfactory.model;

/**
 *
 * @author Joseph Medina
 */
abstract class GUIFactory {

    public static GUIFactory getFactory() {
        int sys = ConfigReader.readFromConfigFile("OS_TYPE");
        
        if (sys == 0)
            return (new WinFactory());
        else
            return (new LinuxFactory());
    }

    public abstract Button createButton();
    public abstract Menu createMenu();


}

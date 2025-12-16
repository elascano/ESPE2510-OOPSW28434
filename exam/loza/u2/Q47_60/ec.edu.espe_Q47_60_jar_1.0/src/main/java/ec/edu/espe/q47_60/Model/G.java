package ec.edu.espe.q47_60.Model;

/**
 *
 * @author Steven Loza
 */
public class G implements H {
    private J j;

    public G(J j) {
        this.j = j;
    }
@Override
    public void execute() {
        System.out.println("G executing via H interface");
        j.action();
    }
}
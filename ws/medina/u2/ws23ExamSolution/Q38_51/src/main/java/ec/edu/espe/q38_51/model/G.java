package ec.edu.espe.q38_51.model;

/**
 *
 * @author Joseph Medina, ESPE
 */
public class G implements H{

    @Override
    public void m(J j) {
        System.out.println("method m f class G is using an object j --> " + j);
    }

    @Override
    public J m() {
        return new J();
    }
    
    
}

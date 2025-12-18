package ec.edu.espe.q38_51.model;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
public class G implements H {

    @Override
    public void m(J j) {
        System.out.println("method m of class G is using an object j ---> " + j);
    }

    @Override
    public J m() {
        return new J();
    }

}

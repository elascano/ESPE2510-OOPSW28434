package ec.edu.espe.q38_51.model;

/**
 *
 * @author LABS-ESPE
 */
public class G implements H {  //1

    @Override
    public void m(J j) {                            //1
        System.out.println("method m of class G is using an object J-->"+j);
    }

    @Override
    public J m() {       //1
       return new J();
    }
    
}

package ec.edu.espe.q38_61.model;

/**
 *
 * @author Josue Carvajal,The Art of Programming, @ESPE
 */
public class G implements H{        //1

    @Override
    public void m(J j) {            //1
        System.out.println("Method m of class G is using an object J--->" + j); 
    }

    @Override
    public J m() {
        return new J();
    }
    
}

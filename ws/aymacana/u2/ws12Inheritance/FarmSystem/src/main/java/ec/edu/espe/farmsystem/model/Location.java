package ec.edu.espe.farmsystem.model;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
public class Location {

    int xCoordenate;
    int yCoordenate;

    @Override
    public String toString() {
        return "Location{" + "xCoordenate=" + xCoordenate + ", yCoordenate=" + yCoordenate + '}';
    }

    public Location(int xCoordenate, int yCoordenate) {
        this.xCoordenate = xCoordenate;
        this.yCoordenate = yCoordenate;
    }

    public int getxCoordenate() {
        return xCoordenate;
    }

    public void setxCoordenate(int xCoordenate) {
        this.xCoordenate = xCoordenate;
    }

    public int getyCoordenate() {
        return yCoordenate;
    }

    public void setyCoordenate(int yCoordenate) {
        this.yCoordenate = yCoordenate;
    }

}

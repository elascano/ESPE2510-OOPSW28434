package ec.edu.espe.computer.model;

import java.util.List;
import org.bson.Document;

/**
 *
 * @author Pablo Collaguazo
 */
public interface ILibrary {
    void insert(Document data);
    List<Document> getAll();
}

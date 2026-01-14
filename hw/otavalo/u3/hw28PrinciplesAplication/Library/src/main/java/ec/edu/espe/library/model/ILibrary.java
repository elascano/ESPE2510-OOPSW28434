package ec.edu.espe.library.model;

import java.util.List;
import org.bson.Document;

/**
 *
 * @author Arelys Otavalo, the POOwer Rangers of Programming
 */
public interface ILibrary {
    void insert(Document data);
    List<Document> getAll();
}

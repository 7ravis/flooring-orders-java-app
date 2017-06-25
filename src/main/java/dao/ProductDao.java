package dao;

import dto.Product;
import java.util.HashMap;

/**
 *
 * @author Travis Rogers
 */

public interface ProductDao {
    public void open() throws DataPersistenceException;
    public void close() throws DataPersistenceException;
    public HashMap<String,Product> getProducts();
    public Product getProductByType(String type);
}

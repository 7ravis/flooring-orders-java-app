package dao;

import dto.Product;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Travis Rogers
 */

public class ProductDaoImpl implements ProductDao{
    private String productFile;
    private HashMap<String, Product> products;
    private final String DELIM = ",";
    
    public ProductDaoImpl(String productFile) {
        this.productFile = productFile;
    }
    
    private void loadFile() throws DataPersistenceException {
        Scanner sc;
        try {
            sc = new Scanner(new BufferedReader(new FileReader("products/Products.txt")));
            sc.nextLine(); //GETTING RID OF FILE HEADER
            while (sc.hasNextLine()) {
                String[] productData = sc.nextLine().split(DELIM);
                Product product = new Product(productData[0], new BigDecimal(productData[1]), new BigDecimal(productData[2]));
                products.put(productData[0], product);
            }
        } catch (IOException e) {
            throw new DataPersistenceException("ERROR: could not load product data.");
        }
    }
    
    @Override
    public void open() throws DataPersistenceException {
        products = new HashMap<>();
        loadFile();
    }
    @Override
    public void close() throws DataPersistenceException {}
    @Override
    public HashMap<String, Product> getProducts() {
        return products;
    }
    @Override
    public Product getProductByType(String type) {
        if (products.containsKey(type)) {
            return products.get(type);
        }
        return null;
    }
}

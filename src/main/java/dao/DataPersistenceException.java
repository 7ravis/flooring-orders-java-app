package dao;

/**
 *
 * @author Travis Rogers
 */

public class DataPersistenceException extends Exception {
    public DataPersistenceException(String message) {
        super(message);
    }
    public DataPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}


package dao;

import dto.State;
import java.util.HashMap;

/**
 *
 * @author Travis Rogers
 */

public interface StateDao {
    public void open() throws DataPersistenceException;
    public void close() throws DataPersistenceException;
    public HashMap<String,State> getStates();
    public State getStateByName(String stateAbbr);
}

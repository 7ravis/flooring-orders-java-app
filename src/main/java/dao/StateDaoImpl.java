package dao;

import dto.State;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Travis Rogers
 */

public class StateDaoImpl implements StateDao {
    private String stateFile;
    private HashMap<String, State> states;
    private final String DELIM = ",";
    
    public StateDaoImpl(String stateFile) {
        this.stateFile = stateFile;
    }
    
    private void loadFile() throws DataPersistenceException {
        Scanner sc;
        try {
            sc = new Scanner(new BufferedReader(new FileReader("states/States.txt")));
            sc.nextLine(); //GETTING RID OF FILE HEADER
            while (sc.hasNextLine()) {
                String[] stateData = sc.nextLine().split(DELIM);
                State state = new State(stateData[0],new BigDecimal(stateData[1]).divide(new BigDecimal("100"), 4, RoundingMode.HALF_UP));
                states.put(stateData[0], state);
            }
        } catch (IOException e) {
            throw new DataPersistenceException("ERROR: state and tax-rate data could not be loaded.");
        }
    }
    
    @Override
    public void open() throws DataPersistenceException {
        states = new HashMap<>();
        loadFile();
    }
    @Override
    public void close() throws DataPersistenceException {}
    
    @Override
    public HashMap<String, State> getStates() {
        return states;
    }
    @Override
    public State getStateByName(String stateAbbr) {
        if (states.containsKey(stateAbbr)) {
            return states.get(stateAbbr);
        }
        return null;
    }
}

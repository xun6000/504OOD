package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.obj.User;

import java.util.HashMap;
import java.util.Map;
/**
 * Command to generate the use id map
 */
public class CollectNamesCmd implements IUserCmd {
    /**
     * a map of userid to username
     */
    private Map<Integer,String> names;

    /**
     * generate a new map
     */
    public CollectNamesCmd(){
        names = new HashMap<>();
    }

    /**
     * put a new pair to the map
     * @param context
     */
    @Override
    public void execute(User context) {
        names.put(context.getId(), context.getName());
    }

    /**
     * get the name from the map
     * @return names
     */
    public Map<Integer, String> getNames() {
        return names;
    }
}

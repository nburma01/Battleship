package battleship.model.ship;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ShipFactory {

    private static Map<String, String> map = new HashMap<>();

    private ShipFactory() {
    }

    /**
     * Register the class of the Ship for the given name.
     * 
     * @param name
     * @param clazz
     */
    public static void registerShip(String name, String clazz) {
        if (!map.containsKey(name)) {
            map.put(name, clazz);
        }
    }

    /**
     * @param name
     * @return a new Ship instance of the given name type.
     */
    public static Ship getShip(String name) {
        try {
            String className = map.get(name);
            Class c = Class.forName(className);
            Constructor cons = c.getConstructor(new Class[] {});
            return (Ship)cons.newInstance(new Object[] {});
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException |
                IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}

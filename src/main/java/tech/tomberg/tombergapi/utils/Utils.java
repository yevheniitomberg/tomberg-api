package tech.tomberg.tombergapi.utils;

import java.util.HashMap;

public class Utils {
    public static HashMap<String, Object> getDefaultDataMap(String message, boolean error) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("error", error);
        return map;
    }
}

package org.jenkinsci.plugins.yamlaxis;

import java.util.List;
import java.util.Map;

public abstract class YamlLoader {
    public List<String> loadStrings(String key){
        Map<String, String> content = getContent();
        Object values = content.get(key);

        if(values == null){
            return null;
        }
        values.collect {
            it.toString()
        }
    }

    /**
     *
     * @param key
     * @return if key is not found, return null
     */
    public List<Map<String, String>> loadMaps(String key){
        Map content = getContent();
        def values = content.get(key);
        if(values == null){
            return null;
        }
        values.collect {
            it.collectEntries { k, v -> [k, v.toString()] }
        }
    }

    public abstract Map getContent();
}

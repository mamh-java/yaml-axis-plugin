package org.jenkinsci.plugins.yamlaxis;

import java.util.List;
import java.util.Map;

public abstract class YamlLoader {
    public List<String> loadStrings(String key){
        Map<String, List> content = getContent();
        List list = content.get(key);
        return list;
    }

    /**
     *
     * @param key
     * @return if key is not found, return null
     */
    public List<Map> loadMaps(String key){
        Map content = getContent();
        List<Map> list = (List<Map>) content.get(key);
        return list;
//        Map content = getContent();
//        def values = content.get(key);
//        if(values == null){
//            return null;
//        }
//        values.collect {
//            it.collectEntries { k, v -> [k, v.toString()] }
//        }
    }

    public abstract Map getContent();
}

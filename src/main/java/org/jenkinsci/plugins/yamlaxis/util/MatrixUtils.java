package org.jenkinsci.plugins.yamlaxis.util;

import java.util.List;
import java.util.Map;

public final class MatrixUtils {
    private MatrixUtils(){
    }

    // Whether parent map contains all entry of child map
    public static <T extends Map<String, String>> boolean contains(T parent, T child) {
        for (Map.Entry<String, String> childEntry : child.entrySet()) {
            String childkey = childEntry.getKey();
            String childValue = childEntry.getValue();
            boolean b = parent.containsKey(childkey) && parent.get(childkey).equals(childValue);
        }
        // TODO not implement.
        return false;
    }

    // reject element in variables if match element of excludes
    public static <T extends Map> List<T> reject(List<T> variables, List<T> excludes){
        if (excludes.isEmpty()){
            return variables;
        }

//        variables.findAll { variable ->
//            !excludes.any{ exclude -> contains(variable, exclude) }
//        }
        // todo  not implement.
        return variables;
    }
}

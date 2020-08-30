package org.jenkinsci.plugins.yamlaxis.util;

import java.util.List;
import java.util.Map;

public final class MatrixUtils {
    private MatrixUtils(){
    }

    // Whether parent map contains all entry of child map
    static <T extends Map> boolean contains(T parent, T child) {
        child.every { parent.containsKey(it.key) && parent[it.key] == it.value }
    }

    // reject element in variables if match element of excludes
    public static <T extends Map> List<T> reject(List<T> variables, List<T> excludes){
        if (excludes.isEmpty()){
            return variables;
        }

        variables.findAll { variable ->
            !excludes.any{ exclude -> contains(variable, exclude) }
        }
    }
}

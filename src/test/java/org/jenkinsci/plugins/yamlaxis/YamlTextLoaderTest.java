package org.jenkinsci.plugins.yamlaxis;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class YamlTextLoaderTest {

    @Test
    public void testgetContent1(){
        String yamlText = "\n" +
                "STRING_VALUE:\n" +
                "  - a\n" +
                "  - b\n" +
                "  - c\n" +
                "INT_VALUE:\n" +
                "  - 1\n" +
                "  - 2\n" +
                "  - 3\n" +
                "BOOL_VALUE:\n" +
                "  - true\n" +
                "  - false\n";

        YamlTextLoader loader = new YamlTextLoader(yamlText);
        Map content = loader.getContent();

        System.out.println(content.get("STRING_VALUE"));  // ["a", "b", "c"]
        System.out.println(content.get("INT_VALUE"));  // ["1", "2", "3"]
        System.out.println(content.get("BOOL_VALUE")); // ["true", "false"]
        System.out.println(content.get("UNKNOWN")); // null

//        "STRING_VALUE" || ["a", "b", "c"]
//        "INT_VALUE"    || ["1", "2", "3"]
//        "BOOL_VALUE"   || ["true", "false"]
//        "UNKNOWN"      || []
//        "STRING_VALUE" || ["a", "b", "c"]
    }

    @Test
    public void testgetContent2(){
        String yamlText = "\n" +
                "exclude:\n" +
                "  - a: 1\n" +
                "    b: 2\n" +
                "  - c: 3\n";


        YamlTextLoader loader = new YamlTextLoader(yamlText);
        Map content = loader.getContent();
        System.out.println(content.get("UNKNOWN")); // null
        System.out.println(content.get("exclude")); // [{a=1, b=2}, {c=3}]



//        "exclude"   || [[a: "1", b: "2"], [c: "3"]]
//        "not_found" || null
    }

    @Test
    public void testloadStrings(){
        String yamlText =
                "STRING_VALUE:\n" +
                "  - a\n" +
                "  - b\n" +
                "  - c\n" +
                "INT_VALUE:\n" +
                "  - 1\n" +
                "  - 2\n" +
                "  - 3\n" +
                "BOOL_VALUE:\n" +
                "  - true\n" +
                "  - false\n";


        YamlTextLoader loader = new YamlTextLoader(yamlText);
        System.out.println(loader.loadStrings("STRING_VALUE"));
        System.out.println(loader.getContent().get("STRING_VALUE"));

        System.out.println(loader.loadStrings("INT_VALUE"));
        System.out.println(loader.getContent().get("INT_VALUE"));

        System.out.println(loader.loadStrings("BOOL_VALUE"));
        System.out.println(loader.getContent().get("BOOL_VALUE"));
    }

    @Test
    public void testloadMaps(){
        String yamlText = "\n" +
                "exclude:\n" +
                "  - a: 1\n" +
                "    b: 2\n" +
                "  - c: 3\n";


        YamlTextLoader loader = new YamlTextLoader(yamlText);
        System.out.println(loader.getContent().get("UNKNOWN")); // null
        System.out.println(loader.getContent().get("exclude")); // [{a=1, b=2}, {c=3}]

        System.out.println(loader.loadMaps("exclude"));
    }

}

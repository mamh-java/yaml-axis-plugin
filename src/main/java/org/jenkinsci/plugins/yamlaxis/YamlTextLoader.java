package org.jenkinsci.plugins.yamlaxis;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import java.util.Map;

public class YamlTextLoader extends YamlLoader {
    static final String RADIO_VALUE = "text";
    private String yamlText;

    public YamlTextLoader(String yamlText) {
        this.yamlText = yamlText;
    }

    @Override
    public Map getContent() {
        Yaml yaml = new Yaml(new SafeConstructor(new LoaderOptions()));
        return (Map) yaml.load(yamlText);
    }
}

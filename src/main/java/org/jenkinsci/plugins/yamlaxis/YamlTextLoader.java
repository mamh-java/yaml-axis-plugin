package org.jenkinsci.plugins.yamlaxis;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import java.util.Map;

class YamlTextLoader extends YamlLoader {
    static final String RADIO_VALUE = "text";

    private String yamlText;

    public YamlTextLoader(String yamlText) {
        this.yamlText = yamlText;
    }

    public String getYamlText() {
        return yamlText;
    }


    @Override
    public Map getContent() {
        Yaml yaml = new Yaml(new SafeConstructor());
        return yaml.load(yamlText);
    }
}

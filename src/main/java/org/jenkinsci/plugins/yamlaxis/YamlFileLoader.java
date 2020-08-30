package org.jenkinsci.plugins.yamlaxis;

import hudson.FilePath;
import hudson.Util;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class YamlFileLoader extends YamlLoader {
    static final String RADIO_VALUE = "file";

    private String yamlFile;
    private FilePath workspace;

    public YamlFileLoader(String yamlFile, FilePath workspace) {
        this.yamlFile = yamlFile;
        this.workspace = workspace;

    }

    public String getYamlFile() {
        return yamlFile;
    }

    public void setYamlFile(String yamlFile) {
        this.yamlFile = yamlFile;
    }

    public FilePath getWorkspace() {
        return workspace;
    }

    public void setWorkspace(FilePath workspace) {
        this.workspace = workspace;
    }

    @Override
    public Map getContent() {
        if(Util.fixEmpty(yamlFile) == null) {
            return null;
        }


        InputStream input = null;
        try{
            Yaml yaml = new Yaml(new SafeConstructor());
            input = createFilePath().read();
            yaml.load(input);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(input!=null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private FilePath createFilePath() {
        if (!Util.isRelativePath(yamlFile) || workspace == null) {
            return new FilePath(new File(yamlFile));
        }

        return new FilePath(workspace, yamlFile);
    }
}

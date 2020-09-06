package org.jenkinsci.plugins.yamlaxis;

import hudson.Extension;
import hudson.FilePath;
import hudson.matrix.Combination;
import hudson.matrix.MatrixBuild;
import hudson.matrix.MatrixExecutionStrategyDescriptor;
import hudson.util.FormValidation;
import net.sf.json.JSONObject;
import org.jenkinsci.plugins.yamlaxis.util.BuildUtils;
import org.jenkinsci.plugins.yamlaxis.util.DescriptorUtils;
import org.jenkinsci.plugins.yamlaxis.util.MatrixUtils;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

class YamlMatrixExecutionStrategy extends BaseMES {
    String yamlType = YamlFileLoader.RADIO_VALUE;
    String yamlFile;
    String yamlText;
    String excludeKey;

    private volatile List<Combination> excludes = null;

    @DataBoundConstructor
    public YamlMatrixExecutionStrategy(String yamlType, String yamlText, String yamlFile, String excludeKey){
        this.yamlType = yamlType;
        this.yamlText = yamlText;
        this.yamlFile = yamlFile;
        this.excludeKey = excludeKey;
    }

    YamlMatrixExecutionStrategy(List<Combination> excludes){
        this.excludes = excludes;
    }

    @Override
    public Map decideOrder(MatrixBuild.MatrixBuildExecution execution, List<Combination> comb) {
//        List<Combination> excludeCombinations = loadExcludes(execution)
//        List<Combination> combinations = MatrixUtils.reject(comb, excludeCombinations)
//
//        BuildUtils.log(execution, "excludes=${excludeCombinations}")
//        ["YamlMatrixExecutionStrategy": combinations]
        return null;
    }

    boolean isYamlTypeFile(){
        return yamlType.equals(YamlFileLoader.RADIO_VALUE);
    }

    boolean isYamlTypeText(){
        return yamlType.equals(YamlTextLoader.RADIO_VALUE);
    }

    private List<Combination> loadExcludes(MatrixBuild.MatrixBuildExecution execution){
        if(excludes != null){
            return excludes;
        }

//        try{
//            List<Map<String, String>> values = getYamlLoader(execution).loadMaps(excludeKey);
//            if(values == null){
//                BuildUtils.log(execution, "[WARN] NotFound excludeKey ${excludeKey}");
//                return [];
//            }
//            values.collect { new Combination(it) }
//
//        } catch (IOException e) {
//            BuildUtils.log(execution, "[WARN] Can not read yamlFile: ${yamlFile}", e)
//            [];
//        }
        return null;
    }

    private YamlLoader getYamlLoader(MatrixBuild.MatrixBuildExecution execution){
        switch(yamlType){
        case YamlFileLoader.RADIO_VALUE:
            FilePath workspace = execution.getBuild().getModuleRoot();
            return new YamlFileLoader(yamlFile,  workspace);
        case YamlTextLoader.RADIO_VALUE:
            return new YamlTextLoader(yamlText);
        default:
            throw new IllegalArgumentException("${yamlType} is unknown");
        }
    }

    @Extension
    public static class DescriptorImpl extends MatrixExecutionStrategyDescriptor {
        final String displayName = "Yaml matrix execution strategy";

        @Override
        public  YamlMatrixExecutionStrategy newInstance(StaplerRequest req, JSONObject formData) {
            String yamlType = formData.getString("yamlType");
            String yamlFile = formData.getString("yamlFile");
            String yamlText = formData.getString("yamlText");
            String excludeKey = formData.getString("excludeKey");
            return new YamlMatrixExecutionStrategy(yamlType, yamlText, yamlFile, excludeKey);
        }

        public FormValidation doCheckYamlFile(@QueryParameter String value) {
            return DescriptorUtils.checkFieldNotEmpty(value, "yamlFile");
        }

        public FormValidation doCheckYamlText(@QueryParameter String value) {
           return DescriptorUtils.checkFieldNotEmpty(value, "yamlText");
        }
    }
}

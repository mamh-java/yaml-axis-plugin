package org.jenkinsci.plugins.yamlaxis;

import hudson.Extension;
import hudson.FilePath;
import hudson.matrix.Axis;
import hudson.matrix.AxisDescriptor;
import hudson.matrix.MatrixBuild;
import hudson.util.FormValidation;
import net.sf.json.JSONObject;
import org.jenkinsci.plugins.yamlaxis.util.DescriptorUtils;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;

import java.util.List;

class YamlAxis extends Axis {
    private List<String> computedValues = null;
    private String valueString;

    @DataBoundConstructor
    public YamlAxis(String name, String valueString, List<String> computedValues) {
        super(name, valueString);
        this.computedValues = computedValues;
    }

    @Override
    public List<String> getValues() {
        if(computedValues != null){
            return computedValues;
        }

        // NOTE: Plugin can not get workspace location in this method
        //YamlLoader loader = new YamlFileLoader(getYamlFile());

        //return computedValues = loader.loadStrings(name);
        return null;
    }

    @Override
    public List<String> rebuild(MatrixBuild.MatrixBuildExecution context) {
        FilePath workspace = context.getBuild().getModuleRoot();
        //YamlLoader loader = new YamlFileLoader(yamlFile, workspace);

        //computedValues = loader.loadStrings(name);
        return null;
    }

    public String getYamlFile(){
        return valueString;

    }

    /**
     * Descriptor for this plugin.
     */
    @Extension
    public static class DescriptorImpl extends AxisDescriptor {
        final String displayName = "Yaml Axis";

        /**
         * Overridden to create a new instance of our Axis extension from UI
         * values.
         * @see hudson.model.Descriptor#newInstance(org.kohsuke.stapler.StaplerRequest,
         * net.sf.json.JSONObject )
         */
        @Override
        public Axis newInstance(StaplerRequest req, JSONObject formData) {
            String name = formData.getString("name");
            String yamlFile = formData.getString("valueString");
            return new YamlAxis(name, yamlFile, null);
        }

        public FormValidation doCheckValueString(@QueryParameter String value) {
            return DescriptorUtils.checkFieldNotEmpty(value, "valueStrng");
        }
    }
}

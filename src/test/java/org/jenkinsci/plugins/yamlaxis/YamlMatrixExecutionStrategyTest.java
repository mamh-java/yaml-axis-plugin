package org.jenkinsci.plugins.yamlaxis;


import hudson.matrix.MatrixProject;
import org.junit.Rule;
import org.jvnet.hudson.test.GroovyJenkinsRule;

public class YamlMatrixExecutionStrategyTest   {
    @Rule
    private final GroovyJenkinsRule rule = new GroovyJenkinsRule();

    public MatrixProject configure() {
//        matrixProject = rule.createMatrixProject();
//
//        def axis = new TextAxis('axis1', ['a', 'b', 'c'])
//        def axis2 = new TextAxis('axis2', ['x', 'y', 'z'])
//        def axl = new AxisList()
//
//        axl << axis
//        axl << axis2
//
//        matrixProject.setAxes(axl)
//
//        matrixProject
        return null;
    }

//    def "run"() {
//        setup:
//        def matrixProject = configure()
//        List<Combination> excludeCombinations = excludes.collect { new Combination(it) }
//        matrixProject.executionStrategy = new YamlMatrixExecutionStrategy(excludeCombinations)
//        def build = matrixProject.scheduleBuild2(0).get()
//
//        expect:
//        build.logFile.text.contains('SUCCESS')
//        build.runs.every { it.logFile.text.contains('SUCCESS') }
//        build.runs.size() == runsCount
//
//        where:
//        excludes                   || runsCount
//        []                         || 9
//        [[axis1: "c", axis2: "z"]] || 8
//        [[axis1: "c"]]             || 6
//    }
}

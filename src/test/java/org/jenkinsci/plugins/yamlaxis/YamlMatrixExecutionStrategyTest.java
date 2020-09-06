package org.jenkinsci.plugins.yamlaxis;


import hudson.console.AnnotatedLargeText;
import hudson.matrix.*;
import hudson.model.queue.QueueTaskFuture;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class YamlMatrixExecutionStrategyTest  {
    @Rule
    public JenkinsRule jenkinsRule = new JenkinsRule();


    public MatrixProject configure() throws IOException {
        MatrixProject project = jenkinsRule.createProject(MatrixProject.class, "MatrixProject");

        AxisList axes = new AxisList();
        axes.add(new TextAxis("axis1", "a", "b", "c"));
        axes.add(new TextAxis("axis2", "x", "y", "z"));

        project.setAxes(axes);

        return project;
    }

    @Test
    public void test() throws IOException, ExecutionException, InterruptedException {
        MatrixProject matrixProject = configure();
        List<Combination> excludeCombinations = Arrays.asList(new Combination(new HashMap<>()));
        System.out.println(excludeCombinations);
        matrixProject.setExecutionStrategy(new YamlMatrixExecutionStrategy(excludeCombinations));

        MatrixBuild build = matrixProject.scheduleBuild2(0).get();

        AnnotatedLargeText logText = build.getLogText();
        System.out.println(logText);

        for (MatrixRun run : build.getRuns()) {
            AnnotatedLargeText logText1 = run.getLogText();
            System.out.println(logText1.toString());
        }



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

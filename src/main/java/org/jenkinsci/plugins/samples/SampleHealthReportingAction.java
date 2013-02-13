package org.jenkinsci.plugins.samples;

import java.util.Random;

import org.jenkinsci.plugins.samples.Messages;

import hudson.model.AbstractBuild;
import hudson.model.HealthReport;
import hudson.model.HealthReportingAction;

public class SampleHealthReportingAction implements HealthReportingAction {

    private final AbstractBuild<?,?> build;

    public SampleHealthReportingAction(AbstractBuild<?,?> build) {
        this.build = build;
    }

    public AbstractBuild<?, ?> getBuild() {
        return build;
    }

    public String getIconFileName() {
        return "graph.gif";
    }

    public String getDisplayName() {
        return "SampleHealthReportingAction";
    }

    public String getUrlName() {
        return "";
    }

    public HealthReport getBuildHealth() {
        int score = 100;

        Random random = new Random();
        score = random.nextInt(100);

        return new HealthReport(score, Messages._HealthReporting_Description("aaaa"));
    }
}

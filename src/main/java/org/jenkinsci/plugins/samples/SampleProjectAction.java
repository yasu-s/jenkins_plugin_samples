package org.jenkinsci.plugins.samples;

import java.io.IOException;

import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.Action;

public class SampleProjectAction implements Action {

    private final AbstractProject<?, ?> project;

    public SampleProjectAction(AbstractProject<?,?> project) {
        this.project = project;
    }

    public AbstractProject<?, ?> getProject() {
        return project;
    }

    public String getIconFileName() {
        return "graph.gif";
    }

    public String getDisplayName() {
        return Messages.SampleProjectAction_DisplayName();
    }

    public String getUrlName() {
        return "sample";
    }

    public String getData() {
        return "Sample Project Action!!";
    }
}

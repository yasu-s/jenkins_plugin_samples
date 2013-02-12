package org.jenkinsci.plugins.samples;

import org.jenkinsci.plugins.samples.Messages;

import org.kohsuke.stapler.StaplerProxy;

import hudson.model.AbstractBuild;
import hudson.model.Action;

public class SampleBuildAction implements Action, StaplerProxy {

    private final AbstractBuild<?,?> build;

    public SampleBuildAction(AbstractBuild<?,?> build) {
        this.build = build;
    }

    public AbstractBuild<?, ?> getBuild() {
        return build;
    }

    public String getIconFileName() {
        return "notepad.png";
    }

    public String getDisplayName() {
        return Messages.SampleBuildAction_DisplayName();
    }

    public String getUrlName() {
        return "sample1";
    }

    public Object getTarget() {
        return new SampleReport(build, "index");
    }
}

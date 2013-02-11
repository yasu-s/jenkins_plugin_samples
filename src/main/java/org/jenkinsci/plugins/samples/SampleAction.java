package org.jenkinsci.plugins.samples;

import hudson.model.AbstractBuild;
import hudson.model.Action;

public class SampleAction implements Action {

    private final AbstractBuild<?,?> build;

    public SampleAction(AbstractBuild<?,?> build) {
        this.build = build;
    }

    public AbstractBuild<?, ?> getBuild() {
        return build;
    }

    public String getIconFileName() {
        return "document.gif";
    }

    public String getDisplayName() {
        return Messages.SampleAction_DisplayName();
    }

    public String getUrlName() {
        return "sample";
    }

    public String getData() {
        return "Sample!!";
    }
}

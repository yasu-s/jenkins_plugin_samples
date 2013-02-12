package org.jenkinsci.plugins.samples;

import java.io.Serializable;

import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

import hudson.model.AbstractBuild;

public class SampleReport implements Serializable  {

    private final AbstractBuild<?,?> build;
    private final String token;

    public SampleReport(AbstractBuild<?,?> build, String token) {
        this.build = build;
        this.token = token;
    }

    public AbstractBuild<?, ?> getBuild() {
        return build;
    }

    public String getToken() {
        return token;
    }

    public Object getDynamic(String token, StaplerRequest req, StaplerResponse rsp) {
        return new SampleReport(build, token);
    }
}

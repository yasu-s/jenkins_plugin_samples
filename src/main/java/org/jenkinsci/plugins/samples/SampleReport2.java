package org.jenkinsci.plugins.samples;

import java.io.Serializable;

import hudson.model.ModelObject;
import hudson.model.AbstractBuild;

public class SampleReport2 implements Serializable, ModelObject  {

    private final AbstractBuild<?,?> build;
    private final String token;

    public SampleReport2(AbstractBuild<?,?> build, String token) {
        this.build = build;
        this.token = token;
    }

    public AbstractBuild<?, ?> getBuild() {
        return build;
    }

    public String getToken() {
        return token;
    }

    public String getDisplayName() {
        return "ModelObject[" + token + "]";
    }
}

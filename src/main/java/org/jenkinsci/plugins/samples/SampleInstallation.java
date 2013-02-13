package org.jenkinsci.plugins.samples;

import java.io.IOException;

import org.jenkinsci.plugins.samples.Messages;
import org.kohsuke.stapler.DataBoundConstructor;

import hudson.EnvVars;
import hudson.Extension;
import hudson.model.EnvironmentSpecific;
import hudson.model.Node;
import hudson.model.TaskListener;
import hudson.slaves.NodeSpecific;
import hudson.tools.ToolDescriptor;
import hudson.tools.ToolInstallation;

public class SampleInstallation extends ToolInstallation implements NodeSpecific<SampleInstallation>, EnvironmentSpecific<SampleInstallation> {

    /** */
    private transient String pathToSample;

    @DataBoundConstructor
    public SampleInstallation(String name, String home) {
        super(name, home, null);
    }

    public SampleInstallation forNode(Node node, TaskListener log) throws IOException, InterruptedException {
        return new SampleInstallation(getName(), translateFor(node, log));
    }

    public SampleInstallation forEnvironment(EnvVars environment) {
        return new SampleInstallation(getName(), environment.expand(getHome()));
    }

    protected Object readResolve() {
        if (this.pathToSample != null) {
            return new SampleInstallation(this.getName(), this.pathToSample);
        }
        return this;
    }

    @Extension
    public static class DescriptorImpl extends ToolDescriptor<SampleInstallation> {

        public String getDisplayName() {
            return Messages.SampleInstallation_DisplayName();
        }
    }
}

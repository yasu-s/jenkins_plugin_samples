package org.jenkinsci.plugins.samples;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.jenkinsci.plugins.samples.Messages;
import org.jenkinsci.plugins.samples.util.Constants;
import org.kohsuke.stapler.DataBoundConstructor;

import hudson.EnvVars;
import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.model.Result;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Publisher;
import hudson.tasks.Recorder;

/**
 * @author Yasuyuki Saito
 */
public class SampleRecorder extends Recorder {

    private final String status;

    @DataBoundConstructor
    public SampleRecorder(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener) throws InterruptedException, IOException {

        PrintStream logger = listener.getLogger();

        if (Constants.STATUS_SUCCESS.equals(status)) {
            logger.println("SampleRecorder: SUCCESS");
            return true;
        } else if (Constants.STATUS_UNSTABLE.equals(status)) {
            logger.println("SampleRecorder: UNSTABLE");
            build.setResult(Result.UNSTABLE);
            return true;
        } else if (Constants.STATUS_FAILURE.equals(status)) {
            logger.println("SampleRecorder: FAILURE");
            return false;
        }

        return true;
    }

    public BuildStepMonitor getRequiredMonitorService() {
        return BuildStepMonitor.NONE;
    }

    @Override
    public BuildStepDescriptor<Publisher> getDescriptor() {
         return DESCRIPTOR;
    }

    /**
     * Descriptor should be singleton.
     */
    @Extension
    public static final DescriptorImpl DESCRIPTOR = new DescriptorImpl();

    public static class DescriptorImpl extends BuildStepDescriptor<Publisher> {

        public DescriptorImpl() {
            super(SampleRecorder.class);
        }

        @Override
        public String getDisplayName() {
            return Messages.SampleRecorder_DisplayName();
        }

        @Override
        public boolean isApplicable(Class<? extends AbstractProject> jobType) {
            return true;
        }
    }
}
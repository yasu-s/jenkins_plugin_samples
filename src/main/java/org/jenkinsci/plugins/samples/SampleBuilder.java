package org.jenkinsci.plugins.samples;

import java.io.PrintStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.jenkinsci.plugins.samples.util.Constants;
import org.kohsuke.stapler.DataBoundConstructor;

import hudson.CopyOnWrite;
import hudson.EnvVars;
import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.Util;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import hudson.model.Result;
import hudson.model.Computer;
import hudson.model.Descriptor;
import hudson.tasks.Builder;
import hudson.tools.ToolInstallation;
import hudson.util.ArgumentListBuilder;

/**
 * @author Yasuyuki Saito
 */
public class SampleBuilder extends Builder {

    private final String status;

    /**
     * Constructor
     * @param status
     */
    @DataBoundConstructor
    public SampleBuilder(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }


    @Override
    public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener) throws InterruptedException, IOException {
        PrintStream logger = listener.getLogger();

        if (Constants.STATUS_SUCCESS.equals(status)) {
            logger.println("SampleBuilder: SUCCESS");
            return true;
        } else if (Constants.STATUS_UNSTABLE.equals(status)) {
            logger.println("SampleBuilder: UNSTABLE");
            build.setResult(Result.UNSTABLE);
            return true;
        } else if (Constants.STATUS_FAILURE.equals(status)) {
            logger.println("SampleBuilder: FAILURE");
            return false;
        }

        return false;
    }


    @Override
    public Descriptor<Builder> getDescriptor() {
         return DESCRIPTOR;
    }

    /**
     * Descriptor should be singleton.
     */
    @Extension
    public static final DescriptorImpl DESCRIPTOR = new DescriptorImpl();

    /**
     * @author Yasuyuki Saito
     */
    public static final class DescriptorImpl extends Descriptor<Builder> {

        DescriptorImpl() {
            super(SampleBuilder.class);
            load();
        }

        public String getDisplayName() {
            return Messages.SampleBuilder_DisplayName();
        }
    }
}

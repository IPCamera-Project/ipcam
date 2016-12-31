package kh.com.kshrd.core.plugin;

import java.security.Policy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sophatvathana on 20/12/16.
 */
public class PluginPath {
    private static final String DEFAULT_PLUGIN_DIRECTORY = "";

    protected String pluginDirectories;

    public PluginPath() {
//        Policy.setPolicy(new PermissionLoader());
//        System.setSecurityManager(new SecurityManager());

        pluginDirectories = DEFAULT_PLUGIN_DIRECTORY;
    }

    public PluginPath(String uri){
        this.pluginDirectories = uri;
    }

    public String getPluginDirectories() {
        return pluginDirectories;
    }

    public void setPluginDirectories(String pluginDirectories) {
        this.pluginDirectories = pluginDirectories;
    }
}

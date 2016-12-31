package kh.com.kshrd.core.plugin;

import com.github.zafarkhaja.semver.Version;

/**
 * Created by sophatvathana on 20/12/16.
 */
public interface PluginManager {

    void loadPlugin(String pluginId, PluginLoadClass pluginLoadClass);

    PluginDescription getProperties(PluginLoadClass pluginLoadClass);

    void setSystemVersion(Version version);

    Version getSystemVersion();
}

package kh.com.kshrd.core.plugin;

import java.io.File;

/**
 * Created by sophatvathana on 20/12/16.
 */
public class PluginSettingUp {
    private File jarFile;
    private ClassLoader classLoader;
    private PluginStateEvent pluginStateEvent;

    public File getJarFile() {
        return jarFile;
    }

    public void setJarFile(File jarFile) {
        this.jarFile = jarFile;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public PluginStateEvent getPluginStateEvent() {
        return pluginStateEvent;
    }

    public void setPluginStateEvent(PluginStateEvent pluginStateEvent) {
        this.pluginStateEvent = pluginStateEvent;
    }

    @Override
    public String toString() {
        return "PluginSettingUp{" +
                "jarFile=" + jarFile +
                ", classLoader=" + classLoader +
                ", pluginStateEvent=" + pluginStateEvent +
                '}';
    }
}

package kh.com.kshrd.core.plugin;

import com.github.zafarkhaja.semver.Version;
import kh.com.kshrd.core.exceptions.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by sophatvathana on 20/12/16.
 */
public class DefaultLoader implements PluginManager {
    private static final Logger log = LoggerFactory.getLogger(DefaultLoader.class);
    public static final String DEFAULT_PLUGINS_DIRECTORY = "plugins";
    protected File pluginLocation;
    private static DefaultLoader defaultLoader;

    public static DefaultLoader getInstance(File pluginLocation){
        if (defaultLoader == null)
            defaultLoader = new DefaultLoader(pluginLocation);
        return defaultLoader;
    }

    public static DefaultLoader getInstance(){
        if (defaultLoader == null)
            defaultLoader = new DefaultLoader();
        return defaultLoader;
    }

    private DefaultLoader(){
        pluginLocation = createDirectory();
        initializer();
    }

    private DefaultLoader(File pluginLocation){
        this.pluginLocation = pluginLocation;
    }

    private void initializer() {
    }

    private File createDirectory() {
        String pluginsDir = System.getProperty("kshrd.plugindir");
        if (pluginsDir == null) {
           pluginsDir = DEFAULT_PLUGINS_DIRECTORY;
        }

        return new File(pluginsDir);
    }

    @Override
    public void loadPlugin(String name, PluginLoadClass pluginLoadClass) {
        log.debug("Lookup plugins in '{}'", pluginLocation.getAbsolutePath());
        if (!pluginLocation.exists() || !pluginLocation.isDirectory()) {
            log.error("No '{}' directory", pluginLocation.getAbsolutePath());
            return;
        }
       // Check for exactly .jar
        PluginLoader pluginLoader = new PluginLoader(pluginLocation.getAbsoluteFile(),new PluginPath(),
                pluginLoadClass);
        try {
            pluginLoader.loadJar(name);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

    @Override
    public PluginDescription getProperties(PluginLoadClass pluginLoadClass) {
        try {
            return new PropertiesDetector().find(pluginLoadClass);
        } catch (SystemException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setSystemVersion(Version version) {

    }

    @Override
    public Version getSystemVersion() {
        return null;
    }

}

package kh.com.kshrd.core.plugin;

import com.github.zafarkhaja.semver.Version;
import kh.com.kshrd.core.exceptions.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by sophatvathana on 20/12/16.
 */
public class DefaultLoader implements PluginManager {
    private static final Logger log = LoggerFactory.getLogger(DefaultLoader.class);
    public static final String DEFAULT_PLUGINS_DIRECTORY = "plugins";
    protected File pluginLocation;
    private String plugindir;
    private static DefaultLoader defaultLoader;

    public void setPluginDir(String dir){
        this.plugindir = dir;
    }

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
        try {
            initializer();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DefaultLoader(File pluginLocation){
        this.pluginLocation = pluginLocation;
        try {
            initializer();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializer() throws ClassNotFoundException, IOException {
        JarFile jarFile = new JarFile(new File(this.pluginLocation.getAbsolutePath()+"lib/"));
        Enumeration<JarEntry> e = jarFile.entries();

        URL[] urls = { new URL("jar:file:" + new File(this.pluginLocation.getAbsolutePath()+"lib/")+"!/") };
        URLClassLoader cl = URLClassLoader.newInstance(urls);

        while (e.hasMoreElements()) {
            JarEntry je = e.nextElement();
            if(je.isDirectory() || !je.getName().endsWith(".class")){
                continue;
            }
            // -6 because of .class
            String className = je.getName().substring(0,je.getName().length()-6);
            className = className.replace('/', '.');
            Class c = cl.loadClass(className);
        }
    }

    private File createDirectory() {
        //String pluginsDir = System.getProperty("kshrd.plugindir");
        if (this.plugindir == null) {
           this.plugindir = DEFAULT_PLUGINS_DIRECTORY;
        }
        return new File(this.plugindir);
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

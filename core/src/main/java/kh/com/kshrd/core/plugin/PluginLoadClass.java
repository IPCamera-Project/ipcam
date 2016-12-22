package kh.com.kshrd.core.plugin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;


/**
 * Created by sophatvathana on 20/12/16.
 */
public class  PluginLoadClass extends URLClassLoader{

    private static final Logger log = LoggerFactory.getLogger(PluginLoadClass.class);

    private static final String PLUGIN_PACKAGE_PREFIX = "kh.com.kshrd.";

    public PluginLoadClass() {
        super(new URL[0]);
    }

    @Override
    public void addURL(URL url) {
        System.out.println(url);
        super.addURL(url);
    }

    /**
     * This implementation of loadClass uses a child first delegation model rather than the standard parent first.
     * If the requested class cannot be found in this class loader, the parent class loader will be consulted
     * via the standard PluginLoadClass.loadClass(String) mechanism.
     */
    @Override
    public Class<?> loadClass(String className) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(className)) {
            log.trace("Received request to load class '{}'", className);
            // if the class it's a part of the plugin engine use parent class loader
            if (className.startsWith(PLUGIN_PACKAGE_PREFIX)) {
                log.trace("Delegate the loading of class '{}' to parent", className);
                try {
                    return getClass().getClassLoader().loadClass(className);
                } catch (ClassNotFoundException e) {
                    // try next step
                    // TODO if I uncomment below lines (the correct approach) I received ClassNotFoundException for demo (ro.fortsoft.pf4j.demo)
                    //                log.error(e.getMessage(), e);
                    //                throw e;
                }
            }

            // second check whether it's already been loaded
            Class<?> clazz = findLoadedClass(className);
            if (clazz != null) {
                log.trace("Found loaded class '{}'", className);
                return clazz;
            }

            // nope, try to load locally
            try {
                clazz = findClass(className);
                log.trace("Found class '{}' in plugin classpath", className);
                return clazz;
            } catch (ClassNotFoundException e) {
                // try next step
            }

            log.trace("Couldn't find class '{}' in plugin classpath. Delegating to parent", className);

            // use the standard URLClassLoader (which follows normal parent delegation)
            return super.loadClass(className);
        }
    }

    /**
     * Load the named resource from this plugin. This implementation checks the plugin's classpath first
     * then delegates to the parent.
     *
     * @param name the name of the resource.
     * @return the URL to the resource, <code>null</code> if the resource was not found.
     */
    @Override
    public URL getResource(String name) {
        log.trace("Trying to find resource '{}' in plugin classpath", name);
        URL url = findResource(name);
        if (url != null) {
            log.trace("Found resource '{}' in plugin classpath", name);
            return url;
        }

        log.trace("Couldn't find resource '{}' in plugin classpath. Delegating to parent");

        return super.getResource(name);
    }

    @Override
    public URL findResource(String name) {
        return super.findResource(name);
    }

    /**
     * Release all resources acquired by this class loader.
     * The current implementation is incomplete.
     * For now, this instance can no longer be used to load
     * new classes or resources that are defined by this loader.
     */
    public void dispose() {
        try {
            close();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

}

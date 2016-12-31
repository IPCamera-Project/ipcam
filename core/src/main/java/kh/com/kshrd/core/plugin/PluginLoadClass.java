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

    @Override
    public Class<?> loadClass(String className) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(className)) {
            log.trace("Received request to load class '{}'", className);
            if (className.startsWith(PLUGIN_PACKAGE_PREFIX)) {
                log.trace("Delegate the loading of class '{}' to parent", className);
                try {
                    return getClass().getClassLoader().loadClass(className);
                } catch (ClassNotFoundException e) {
                    // try next step
                    //                log.error(e.getMessage(), e);
                    //                throw e;
                }
            }

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

    public void dispose() {
        try {
            close();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

}

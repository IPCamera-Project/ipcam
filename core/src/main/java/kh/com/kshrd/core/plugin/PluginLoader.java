package kh.com.kshrd.core.plugin;

import kh.com.kshrd.core.utils.DirectoryFile;
import kh.com.kshrd.core.utils.JarFileAccept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.net.MalformedURLException;
import java.security.Policy;
import java.util.Vector;

/**
 * Created by sophatvathana on 20/12/16.
 */
public class PluginLoader {
    private static final Logger log = LoggerFactory.getLogger(PluginLoader.class);
    private File pluginRepository;

    private PluginPath pluginPath;
    private PluginLoadClass pluginLoadClass;

    public PluginLoader(){
        pluginPath = new PluginPath();
    }
    public PluginLoader(File repo, PluginPath pluginPath, PluginLoadClass pluginLoadClass){
        this.pluginRepository = repo;
        this.pluginPath = pluginPath;
        this.pluginLoadClass = pluginLoadClass;
    }

    public boolean loadJars() {
        String PluginDirectories = pluginPath.getPluginDirectories();
        File file = new File(pluginRepository, PluginDirectories).getAbsoluteFile();
        // collect all jars from current lib directory in jars variable
        Vector<File> jars = new Vector<>();
            getJars(jars, file);
            for (File jar : jars) {
                try {
                    pluginLoadClass.addURL(jar.toURI().toURL());
                    log.debug("Added '{}' to the class loader path", jar);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    log.error(e.getMessage(), e);
                    return false;
                }
            }

        return true;
    }

    public boolean loadJar(String name) throws Throwable{
        FileFilter jarFilter = new JarFileAccept();
        String PluginDirectories = pluginPath.getPluginDirectories();
        File file = new File(pluginRepository, PluginDirectories).getAbsoluteFile();
        log.debug("This '{}' is and absolute. ",file);
        File jar = new File(file,name);
        try {
            log.debug("This '{}' is and absolute. ",jar.toURI());
            pluginLoadClass.addURL(jar.toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void getJar(File path, File file){
        if (file.exists() && file.isDirectory() && file.isAbsolute()){

        }
    }

    private void getJars(Vector<File> bucket, File file) {
        FileFilter jarFilter = new JarFileAccept();
        FileFilter directoryFilter = new DirectoryFile();

        if (file.exists() && file.isDirectory() && file.isAbsolute()) {
            File[] jars = file.listFiles(jarFilter);
            for (int i = 0; (jars != null) && (i < jars.length); ++i) {
                bucket.addElement(jars[i]);
            }

            File[] directories = file.listFiles(directoryFilter);
            for (int i = 0; (directories != null) && (i < directories.length); ++i) {
                File directory = directories[i];
                getJars(bucket, directory);
            }
        }
    }

    public static void main(String[] arg){
        try {
            System.out.println(new PluginLoader().loadJar("hikvision-1.0-SNAPSHOT.jar"));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

}

package kh.com.kshrd.core.plugin;

import com.github.zafarkhaja.semver.Version;
import kh.com.kshrd.core.exceptions.SystemException;
import kh.com.kshrd.core.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.Properties;

/**
 * Created by sophatvathana on 20/12/16.
 */
public class PropertiesDetector implements IPropertiesDetector {
    private static final Logger log = LoggerFactory.getLogger(PropertiesDetector.class);
    private final static String DEFAULT_PROPERTIES_FILE_NAME = "plugin.properties";
    private String propertiesFileName ;

    public PropertiesDetector(){
        this.propertiesFileName = DEFAULT_PROPERTIES_FILE_NAME;
    }

    public PropertiesDetector(String propertiesFileName){
        this.propertiesFileName = propertiesFileName;
    }


    @Override
    public PluginDescription find(File pluginRepository) throws SystemException {
        Properties properties = readProperties(pluginRepository);

        PluginDescription pluginDescription = createPluginDescription(properties);
        validate(pluginDescription);

        return pluginDescription;
    }

    @Override
    public PluginDescription find(URL pluginRepository) throws SystemException {
        Properties properties = readProperties(pluginRepository);

        PluginDescription pluginDescription = createPluginDescription(properties);
        validate(pluginDescription);

        return pluginDescription;
    }

    @Override
    public PluginDescription find(PluginLoadClass pluginLoadClass) throws SystemException {
        Properties properties = readProperties(pluginLoadClass.getResource(this.propertiesFileName));

        PluginDescription pluginDescription = createPluginDescription(properties);
        validate(pluginDescription);

        return pluginDescription;
    }

    private Properties readProperties(File pluginRepository) throws SystemException {
        File propertiesFile = new File(pluginRepository, propertiesFileName);
        log.debug("Lookup plugin detail in '{}'", propertiesFile);

        if (!propertiesFile.exists()) {
            throw new SystemException("Cannot find '" + propertiesFile + "' file");
        }

        InputStream input = null;
        try {
            input = new FileInputStream(propertiesFile);
        } catch (FileNotFoundException e) {
            // not happening
        }

        Properties properties = new Properties();
        try {
            properties.load(input);
        } catch (IOException e) {
            throw new SystemException(e.getMessage(), e);
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                throw new SystemException(e.getMessage(), e);
            }
        }

        return properties;
    }

    private Properties readProperties(URL pluginRepository) throws SystemException {
        log.debug("Lookup plugin detail in '{}'", pluginRepository);

        if (pluginRepository == null) {
            throw new SystemException("Cannot find '" + pluginRepository + "' file");
        }

        InputStream input = null;
        try {
            input = pluginRepository.openStream();

        }catch (IOException e) {
            e.printStackTrace();
        }

        Properties properties = new Properties();
        try {
            properties.load(input);
        } catch (IOException e) {
            throw new SystemException(e.getMessage(), e);
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                throw new SystemException(e.getMessage(), e);
            }
        }

        return properties;
    }

    private PluginDescription createPluginDescription(Properties properties) {
        PluginDescription pluginDescription = new PluginDescription();

        // TODO validate !!!
        String id = properties.getProperty("plugin.id");
        pluginDescription.setId(id);

        String vendor = properties.getProperty("plugin.vendor");
        pluginDescription.setVendor(vendor);

        String className = properties.getProperty("plugin.className");
        pluginDescription.setClassName(className);

        String version = properties.getProperty("plugin.version");
        if (StringUtils.isNotEmpty(version)) {
            pluginDescription.setVersion(Version.valueOf(version));
        }

        String author = properties.getProperty("plugin.author");
        pluginDescription.setAuthor(author);

        String description = properties.getProperty("plugin.description");
        pluginDescription.setDescription(description);

        String release = properties.getProperty("plugin.release");
        pluginDescription.setRelease(release);

        String name = properties.getProperty("plugin.name");
        pluginDescription.setName(name);

        return pluginDescription;
    }

    protected void validate(PluginDescription pluginDescription) throws SystemException {
//        if (StringUtils.isEmpty(pluginDescription.getId())) {
//            throw new SystemException("plugin.id cannot empty");
//        }
        if (StringUtils.isEmpty(pluginDescription.getClassName())) {
            throw new SystemException("plugin.className cannot empty");
        }
        if (pluginDescription.getVersion() == null) {
            throw new SystemException("plugin.version cannot empty");
        }
    }

    public static void main(String[] arg){
        try {
            PluginDescription p = new PropertiesDetector().find(new File("/Users/sophatvathana/Desktop/ipcam/hikvision/"));
            System.out.println(p.getAuthor());
            System.out.println(p.getClassName());
            System.out.println(p.getVersion());
            System.out.println(p.getVendor());
            System.out.println(p.getId());
        } catch (SystemException e) {
            e.printStackTrace();
        }
    }
}

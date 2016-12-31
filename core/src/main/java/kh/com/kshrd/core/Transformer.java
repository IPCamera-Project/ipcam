package kh.com.kshrd.core;

import kh.com.kshrd.core.exceptions.SystemException;
import kh.com.kshrd.core.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;


/**
 * Created by sophatvathana on 21/12/16.
 */
public class Transformer implements TransformerFactory {

    private static final Logger log = LoggerFactory.getLogger(Transformer.class);

    private PluginDescription pluginDescription;
    private PluginLoadClass pluginLoadClass;

    public Transformer(PluginLoadClass pluginLoadClass){
        this.pluginLoadClass = pluginLoadClass;
    }
    @Override
    public PluginStateEvent newInstance() {
        //pluginDescription = new PluginDescription();
        //PluginLoadClass pluginLoadClass = new PluginLoadClass(pluginDescription);
        URL url = pluginLoadClass.getResource("plugin.properties");
        try {
            pluginDescription = new PropertiesDetector().find(url);
            log.debug("Vendor name is {}", pluginDescription.getVendor());
        } catch (SystemException e) {
            e.printStackTrace();
        }

        log.debug("Find resource {} ",pluginLoadClass.getResource("plugin.properties"));
        try {
            PluginStateEvent sp = (PluginStateEvent) pluginLoadClass.loadClass(pluginDescription.getClassName()).newInstance();
            return sp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

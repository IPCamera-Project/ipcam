package kh.com.kshrd.core.plugin;

import kh.com.kshrd.core.exceptions.SystemException;

import java.io.File;
import java.net.URL;

/**
 * Created by sophatvathana on 20/12/16.
 */
public interface IPropertiesDetector {
    PluginDescription find(File pluginRepository) throws Exception;
    PluginDescription find(URL pluginRepository) throws Exception;
    PluginDescription find(PluginLoadClass pluginLoadClass) throws SystemException;
}

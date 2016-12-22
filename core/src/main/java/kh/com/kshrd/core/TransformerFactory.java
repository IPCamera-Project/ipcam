package kh.com.kshrd.core;

import kh.com.kshrd.core.plugin.PluginLoadClass;
import kh.com.kshrd.core.plugin.PluginStateEvent;

/**
 * Created by sophatvathana on 21/12/16.
 */
public interface TransformerFactory  {
    PluginStateEvent newInstance();
}

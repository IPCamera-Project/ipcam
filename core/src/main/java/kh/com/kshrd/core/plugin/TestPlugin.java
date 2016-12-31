package kh.com.kshrd.core.plugin;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.Policy;

/**
 * Created by sophatvathana on 20/12/16.
 */
public class TestPlugin {

    public TestPlugin() throws Throwable {
        Policy.setPolicy(new PermissionLoader());
        System.setSecurityManager(new SecurityManager());
        String path = new File(System.getProperty("user.dir")).getAbsolutePath()+"/plugins/";
        File authorizedJarFile = new File(path,"hikvision-1.0-SNAPSHOT.jar");
        ClassLoader authorizedLoader = URLClassLoader.newInstance(new URL[] { authorizedJarFile.toURL() });
        PluginStateEvent authorizedPluginStateEvent = (PluginStateEvent) authorizedLoader.loadClass("kh.com.kshrd.hikvision.ServiceProvider").newInstance();
        authorizedPluginStateEvent.run();
        authorizedPluginStateEvent.setConnection("http://192.168.0.1",80, 256, "Admin","12345");
        System.out.println(authorizedPluginStateEvent.helloWorld());
    }

    public static void main(String[] arg) throws Throwable {
        new TestPlugin();
        System.out.println(new File(System.getProperty("user.dir")).getAbsolutePath());
    }
}

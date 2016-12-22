package kh.com.kshrd.core;

import kh.com.kshrd.core.exceptions.SystemException;
import kh.com.kshrd.core.plugin.*;

import java.lang.annotation.Target;

/**
 * Created by sophatvathana on 21/12/16.
 */
public class Client{
//    private static Transformer transformer;
//    private static PluginDescription pluginDescription;
//    private static PluginLoadClass pluginLoadClass;
//    private static PluginStateEvent pluginStateEvent;
    public static void main(String [] arg){
        Hikvision h = new Hikvision();
        h.run();
//
// un();

    }
    static class Hikvision extends Thread{
        public void run() {
            PluginLoadClass pluginLoadClass = new PluginLoadClass();
            DefaultLoader.getInstance().loadPlugin("hikvision-1.0-SNAPSHOT.jar", pluginLoadClass);
            Transformer transformer = new Transformer(pluginLoadClass);
            PluginStateEvent pluginStateEvent = transformer.newInstance();
            System.out.println("asdsadasdasdas");
            pluginStateEvent.setConnection("192.168.0.27", 80, "admin", "12345");
            System.out.println(pluginStateEvent.helloWorld());
            for (int i = 0; i < 40; i++) {
                try {
                    pluginStateEvent.left();
                    sleep(3000);
                    pluginStateEvent.stop();
                    pluginStateEvent.right();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //pluginStateEvent.left();
            //pluginStateEvent.right();
            pluginStateEvent.stop();
            PluginDescription pluginDescription = DefaultLoader.getInstance().getProperties(pluginLoadClass);
            System.out.println(pluginDescription.getVendor());
            System.out.println(pluginStateEvent.getRtsp());
        }
    }
    static class Vivotek extends Thread{
        public void run() {
            PluginLoadClass pluginLoadClass = new PluginLoadClass();
            DefaultLoader.getInstance().loadPlugin("vivotek-1.0-SNAPSHOT.jar", pluginLoadClass);
            Transformer transformer = new Transformer(pluginLoadClass);
            PluginStateEvent pluginStateEvent = transformer.newInstance();
            System.out.println("asdsadasdasdas");
            pluginStateEvent.setConnection("192.168.0.45", 80, "root", "pass");
            System.out.println(pluginStateEvent.helloWorld());
            for (int i = 0; i < 40; i++) {
                try {
                    pluginStateEvent.left();
                    pluginStateEvent.up();
                    sleep(300);
                    if (i<20)
                        continue;
                    //sleep(3000);
                    //pluginStateEvent.stop();
                    pluginStateEvent.right();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //pluginStateEvent.left();
            //pluginStateEvent.right();
            pluginStateEvent.stop();
            PluginDescription pluginDescription = DefaultLoader.getInstance().getProperties(pluginLoadClass);
            System.out.println(pluginDescription.getVendor());
            System.out.println(pluginStateEvent.getRtsp());
        }

    }
}

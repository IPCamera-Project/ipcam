package kh.com.kshrd.core;

import kh.com.kshrd.core.plugin.PluginStateEvent;

/**
 * Created by sophatvathana on 20/12/16.
 */
public abstract class Command implements PluginStateEvent {
    public void run(){}
    public String helloWorld(){
        return "Hello wold";
    }

    public boolean left(){
        return false;
    }
    public boolean right(){
        return false;
    }
    public boolean up(){
        return false;
    }
    public boolean down(){
        return false;
    }
    public boolean zoomIn(){
        return false;
    }
    public boolean zoomOut(){
        return false;
    }
    public boolean stop(){
        return false;
    }

    public String getRtsp(){
        return null;
    }

    public abstract void setConnection(String host, int port, String user,String pass);
}

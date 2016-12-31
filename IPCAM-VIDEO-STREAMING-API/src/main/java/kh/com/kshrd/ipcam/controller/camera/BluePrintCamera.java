package kh.com.kshrd.ipcam.controller.camera;

import kh.com.kshrd.core.plugin.PluginStateEvent;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * Created by sophatvathana on 23/12/16.
 */
public class BluePrintCamera implements PluginStateEvent{
    private String host, user, pass;
    private int    port;

    public BluePrintCamera() {
    }

    @Override
    public void setConnection(String host, int port, int rstpPort, String user, String pass) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.pass = pass;
    }

    @Override
    public void run() {

    }

    @Override
    public String helloWorld() {
        return null;
    }

    @Override
    public boolean left() {
        return false;
    }

    @Override
    public boolean right() {
        return false;
    }

    @Override
    public boolean up() {
        return false;
    }

    @Override
    public boolean down() {
        return false;
    }

    @Override
    public boolean zoomIn() {
        return false;
    }

    @Override
    public boolean zoomOut() {
        return false;
    }

    @Override
    public boolean stop() {
        return false;
    }

    @Override
    public String getRtsp() {
        return null;
    }

}

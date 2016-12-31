package kh.com.kshrd.ipcam.service.CameraType.Impl;

import kh.com.kshrd.ipcam.service.CameraType.CmdInterface;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by rina on 12/18/16.
 */
public class Vivotek implements CmdInterface {


    private String host;
    private int port;
    private String user;
    private String pass;
    CloseableHttpClient httpclient;


    public Vivotek(){}

    public Vivotek(String host, int port, String user, String pass) {
        this.host = host;
        this.port = port;
    }

    private HttpGet makeRequest(String move) {
        return new HttpGet(String.format("http://%s%s/cgi-bin/camctrl/camctrl.cgi?move=" + move, host, port == 80? "": String.format(":%d", port)));
    }


    @Override
    public boolean left(String left) {
            return executeCommand("left","left");
    }

    @Override
    public boolean right(String right) {
            return executeCommand("right","right");
    }

    @Override
    public boolean up(String up) {
            return executeCommand("up","up");
    }

    @Override
    public boolean down(String down) {
            return executeCommand("down","down");
    }

    @Override
    public boolean zoomIn(String zoomIn) {
            return executeCommand("zoom in","tele");
    }

    @Override
    public boolean zoomOut(String zoomOut) {
            return executeCommand("wide","wide");
    }

    @Override
    public boolean executeCommand(String msg, String command) {
        boolean rslt = false;
        HttpGet req = makeRequest(command);
        System.out.println(req);
        org.apache.http.entity.StringEntity ent = new org.apache.http.entity.StringEntity(msg, "UTF-8");
        try {

            CloseableHttpResponse response2 = httpclient.execute(req);

            try {
                HttpEntity entity2 = response2.getEntity();
                InputStreamReader isr = new InputStreamReader(entity2.getContent());
                BufferedReader br = new BufferedReader(isr);
                String l = br.readLine();
                while (l != null) {
                    System.out.println(l);
                    l = br.readLine();
                }
                rslt = (response2.getStatusLine().getStatusCode() >=200 &&
                        response2.getStatusLine().getStatusCode()< 300);
            } finally {
                response2.close();
            }
        } catch (Exception ex) {

        }

        return true;
    }
    @Override
    public  boolean stop(){
        return executeCommand("down","stop");
    }

    @Override
    public void setConnection(String host, int port, String user, String pass) {
        this.host = host;
        this.user = user;
        this.pass = pass;
        this.port = port;

        if (user != null && user.length() > 0) {
            CredentialsProvider credsProvider = new BasicCredentialsProvider();
            credsProvider.setCredentials(
                    new AuthScope(host, port),
                    new UsernamePasswordCredentials(user, pass));
            httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
        }
        else
            httpclient = HttpClients.createDefault();

    }

}

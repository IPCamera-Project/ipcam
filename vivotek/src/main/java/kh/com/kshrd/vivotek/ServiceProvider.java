package kh.com.kshrd.vivotek;

import kh.com.kshrd.core.Command;
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
 * Created by sophatvathana on 22/12/16.
 */
public class ServiceProvider extends Command {

    private String host;
    private int port;
    private String user;
    private String pass;
    CloseableHttpClient httpclient;


    private HttpGet makeRequest(String move, Boolean isZoom) {
        return new HttpGet(String.format("http://%s%s/cgi-bin/camctrl/camctrl.cgi?%s=" + move, host, port == 80? "": String.format(":%d", port), isZoom?"zoom":"move"));
    }


    @Override
    public boolean left() {
        return executeCommand("left","left", false);
    }

    @Override
    public boolean right() {
        return executeCommand("right","right", false);
    }

    @Override
    public boolean up() {
        return executeCommand("up","up", false);
    }

    @Override
    public boolean down() {
        return executeCommand("down","down", false);
    }

    @Override
    public boolean zoomIn() {
        return executeCommand("zoom in","tele", true);
    }

    @Override
    public boolean zoomOut() {
        return executeCommand("wide","wide", true);
    }

    private boolean executeCommand(String msg, String command, boolean isZoom) {
        boolean rslt = true;
        HttpGet req = makeRequest(command, isZoom);
        System.out.println(req);
        org.apache.http.entity.StringEntity ent = new org.apache.http.entity.StringEntity(msg, "UTF-8");
        if(rslt)
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
                System.out.println(rslt);
            } finally {
                response2.close();
            }
        } catch (Exception ex) {

        }

        return true;
    }
    @Override
    public  boolean stop(){
        return executeCommand("down","stop", false);
    }

    @Override
    public void setConnection(String host, int port, int rstpPort, String user, String pass) {
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

    @Override
    public String getRtsp() {
        return "/live.sdp";
    }
}

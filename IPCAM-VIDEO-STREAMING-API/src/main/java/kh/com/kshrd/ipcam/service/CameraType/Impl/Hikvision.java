package kh.com.kshrd.ipcam.service.CameraType.Impl;

import kh.com.kshrd.ipcam.service.CameraType.CmdInterface;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by rina on 12/18/16.
 */
public class Hikvision implements CmdInterface {

    private String host;
    private int port;
    private String user;
    private String pass;
    CloseableHttpClient httpclient;

    public Hikvision(){}

    public Hikvision(String host, int port, String user, String pass) {
        this.host = host;
        this.port = port;
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

    @Override
    public boolean executeCommand(String command,String msg) {
        boolean rslt = false;
        HttpPut req = makeRequest();
        System.out.print("Inside +++++++ "+req);
        org.apache.http.entity.StringEntity ent;
        ent = new org.apache.http.entity.StringEntity(msg, "UTF-8");
        req.setEntity(ent);
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
            ex.printStackTrace();
        }
        return rslt;
    }

    private HttpPut makeRequest() {
        return new HttpPut(
                String.format("http://%s%s/ISAPI/PTZCtrl/channels/1/continuous", host, port == 80? "": String.format(":%d", port)));
    }

    private String makeString(int p, int t, int z) {
        return String.format("<PTZData version=\"2.0\" xmlns=\"http://www.isapi.org/ver20/XMLSchema\"><pan>%d</pan><tilt>%d</tilt><zoom>%d</zoom></PTZData>", p, t, z);
    }

    @Override
    public boolean left(String left) {
        Integer value ;
        try{
            stop();
            value = Integer.parseInt(left);
        }
        catch (NumberFormatException e){
            return false;
        }
        return executeCommand("left",
                                makeString(-value,0,0));
    }

    @Override
    public boolean right(String right) {
        Integer value ;
        try{
            stop();
            value = Integer.parseInt(right);
        }
        catch (NumberFormatException e){
            return false;
        }

        return executeCommand("right",makeString(value,0,0));
    }

    @Override
    public boolean up(String up) {
        Integer value ;
        try{
            stop();
            value = Integer.parseInt(up);
        }
        catch (NumberFormatException e){
            return false;
        }

        return executeCommand("up",makeString(0,value,0));
    }

    @Override
    public boolean down(String down) {
        Integer value ;
        try {
            stop();
            value  = Integer.parseInt(down);
        }
        catch (NumberFormatException e){
            return  false;
        }
        return executeCommand("down",makeString(0,-value,0));
    }

    @Override
    public boolean zoomIn(String zoomIn) {
        Integer value ;
        try {
            stop();
            value  = Integer.parseInt(zoomIn);
        }
        catch (NumberFormatException e){
            return  false;
        }
        return executeCommand("zoomIn",makeString(0,0,value));
    }

    @Override
    public boolean zoomOut(String zoomOut) {
        Integer value ;
        try {
            stop();
            value  = Integer.parseInt(zoomOut);
        }
        catch (NumberFormatException e){
            return  false;
        }
        return executeCommand("zoomOut",makeString(0,0,-value));
    }

    @Override
    public  boolean stop(){
        return executeCommand("stop",makeString(0,0,0));
    }





}

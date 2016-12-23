package kh.com.kshrd.ipcam.entity.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import kh.com.kshrd.ipcam.entity.camera.Model;
import kh.com.kshrd.ipcam.entity.user.User;

/**
 * Created by rina on 12/23/16.
 */
public class IPCameraInputer {
    @JsonProperty("SERIAL_NUMBER")
    private String serial_number;

    @JsonProperty("NAME")
    private  String name;

    @JsonProperty("IP_ADDRESS")
    private String ip_address;

    @JsonProperty("WEB_PORT")
    private int web_port;

    @JsonProperty("RTSP_PORT")
    private int rtsp_port;

    @JsonProperty("USERNAME")
    private String username;

    @JsonProperty("PASSWORD")
    private String password;

    @JsonProperty("STREAM_URL")
    private String stream_url;


    @JsonProperty("MODEL")
    private Model model;

    @JsonProperty("USER")
    private User user;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerial_number() {return serial_number;}

    public void setSerial_number(String serial_number) {this.serial_number = serial_number;}

    public String getIp_address() {return ip_address;}

    public void setIp_address(String ip_address) {this.ip_address = ip_address;}

    public int getWeb_port() {return web_port;}

    public void setWeb_port(int web_port) {this.web_port = web_port;}

    public int getRtsp_port() {return rtsp_port;}

    public void setRtsp_port(int rtsp_port) {this.rtsp_port = rtsp_port;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getStream_url() {return stream_url;}

    public void setStream_url(String stream_url) {this.stream_url = stream_url;}

    public Model getModel() {return model;}

    public void setModel(Model model) {this.model = model;}

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}
}
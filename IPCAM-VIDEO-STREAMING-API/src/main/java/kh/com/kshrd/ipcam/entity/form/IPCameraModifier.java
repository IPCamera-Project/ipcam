package kh.com.kshrd.ipcam.entity.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import kh.com.kshrd.ipcam.entity.camera.Model;
import kh.com.kshrd.ipcam.entity.user.User;

/**
 * Created by rina on 12/23/16.
 */
public class IPCameraModifier {

    @JsonProperty("CAMERA_ID")
    private  int camera_id;

    @JsonProperty("NAME")
    private  String name;

    @JsonProperty("SERIAL_NUMBER")
    private String serial_number;

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

    @JsonProperty("MODEL_ID")
    private int model_id;

    @JsonProperty("USER_ID")
    private int user_id;


    public int getCamera_id() {
        return camera_id;
    }

    public void setCamera_id(int camera_id) {
        this.camera_id = camera_id;
    }

    public int getModel_id() {
        return model_id;
    }

    public void setModel_id(int model_id) {
        this.model_id = model_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

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

}

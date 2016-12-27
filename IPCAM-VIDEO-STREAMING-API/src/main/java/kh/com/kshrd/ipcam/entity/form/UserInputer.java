package kh.com.kshrd.ipcam.entity.form;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by rina on 12/22/16.
 */
public class UserInputer {

    @JsonProperty("USERNAME")
    protected String username;

    @JsonProperty("EMAIL")
    private String email;

    @JsonProperty("PASSWORD")
    protected String password;

    @JsonProperty("IMAGE")
    protected String image;

    @JsonProperty("ROLE_ID")
    protected  int role_id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }
}
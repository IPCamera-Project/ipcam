package kh.com.kshrd.ipcam.entity.form;

/**
 * Created by sophatvathana on 22/12/16.
 */

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by rina on 12/22/16.
 */
public class UserModifier {
    @JsonProperty("USER_ID")
    private  int user_id;

    @JsonProperty("USERNAME")
    protected String username;

    @JsonProperty("PASSWORD")
    protected String password;

    @JsonProperty("EMAIL")
    private String email;

    @JsonProperty("IMAGE")
    protected String image;

    @JsonProperty("STATUS")
    protected boolean status;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}
}
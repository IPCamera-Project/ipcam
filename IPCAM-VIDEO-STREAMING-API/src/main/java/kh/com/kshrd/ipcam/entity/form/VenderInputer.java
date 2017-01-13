package kh.com.kshrd.ipcam.entity.form;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by ppsc08 on 05-Jan-17.
 */
public class VenderInputer {

    @JsonProperty("LOGO")
    private String logo;

    @JsonProperty("NAME")
    private  String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}

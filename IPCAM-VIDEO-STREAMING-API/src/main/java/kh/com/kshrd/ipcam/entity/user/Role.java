package kh.com.kshrd.ipcam.entity.user;


import com.fasterxml.jackson.annotation.JsonProperty;
import kh.com.kshrd.ipcam.entity.camera.base.BaseEntity;

public class Role {
    @JsonProperty("ROLE_ID")
    private int role_id;

    @JsonProperty("ROLE_NAME")
    private String name;

    @JsonProperty("DESCRIPTION")
    private String description;

    public int getRole_id() {
        return role_id;
    }


    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
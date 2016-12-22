package kh.com.kshrd.ipcam.entity.form;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by rina on 12/21/16.
 */
public class ModelInputer  {

    @JsonProperty("NAME")
    private String name;
    @JsonProperty("VENDOR_ID")
    private int vender_id;

    @JsonProperty("PLUGIN_ID")
    private int plugin_id;

    @JsonProperty("IMAGE")
    private String image;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public int getVender_id() {
        return vender_id;
    }

    public void setVender_id(int vender_id) {
        this.vender_id = vender_id;
    }

    public int getPlugin_id() {
        return plugin_id;
    }

    public void setPlugin_id(int plugin_id) {
        this.plugin_id = plugin_id;
    }
}
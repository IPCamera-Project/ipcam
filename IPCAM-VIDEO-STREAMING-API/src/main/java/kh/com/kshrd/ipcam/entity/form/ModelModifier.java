package kh.com.kshrd.ipcam.entity.form;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by rina on 12/21/16.
 */
public class ModelModifier {
    @JsonProperty("MODEL_ID")
    private int model_id;
    @JsonProperty("NAME")
    private String name;
    @JsonProperty("VENDOR_ID")
    private int vender_id;

    @JsonProperty("PLUGIN_ID")
    private int plugin_id;

    @JsonProperty("IMAGE")
    private String image;

    @JsonProperty("STREAM_URL")
    private String stream_url;

    public String getStream_url() {return stream_url;}
    public void setStream_url(String stream_url) {this.stream_url = stream_url;}

    public int getModel_id() {
        return model_id;
    }
    public void setModel_id(int model_id) {
        this.model_id = model_id;
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public int getVender_id() {return vender_id;}
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
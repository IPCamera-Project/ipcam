package kh.com.kshrd.ipcam.entity.plugin;

import com.fasterxml.jackson.annotation.JsonProperty;
import kh.com.kshrd.ipcam.entity.camera.base.BaseEntity;

/**
 * Created by sophatvathana on 21/12/16.
 */
public class Plugin {

    @JsonProperty("PLUGIN_ID")
    private int plugin_id;

    @JsonProperty("PLUGIN_NAME")
    protected String plugin_name;

    @JsonProperty("PLUGIN_DESCRIPTION")
    protected String plugin_description;

    @JsonProperty("PLUGIN_VERSION")
    protected String plugin_version;

    @JsonProperty("PLUGIN_RELEASE")
    protected String plugin_release;

    @JsonProperty("PLUGIN_ACTIVE")
    protected boolean plugin_active;

    @JsonProperty("PLUGIN_VENDOR")
    protected String plugin_vendor;

    public int getPlugin_id() {
        return plugin_id;
    }

    public void setPlugin_id(int plugin_id) {
        this.plugin_id = plugin_id;
    }

    public String getPlugin_name() {
        return plugin_name;
    }

    public void setPlugin_name(String plugin_name) {
        this.plugin_name = plugin_name;
    }

    public String getPlugin_description() {
        return plugin_description;
    }

    public void setPlugin_description(String plugin_description) {
        this.plugin_description = plugin_description;
    }

    public String getPlugin_version() {
        return plugin_version;
    }

    public void setPlugin_version(String plugin_version) {
        this.plugin_version = plugin_version;
    }

    public String getPlugin_release() {
        return plugin_release;
    }

    public void setPlugin_release(String plugin_release) {
        this.plugin_release = plugin_release;
    }


    public boolean isPlugin_active() {
        return plugin_active;
    }

    public void setPlugin_active(boolean plugin_active) {
        this.plugin_active = plugin_active;
    }

    public String getPlugin_vendor() {
        return plugin_vendor;
    }

    public void setPlugin_vendor(String plugin_vendor) {
        this.plugin_vendor = plugin_vendor;
    }
}

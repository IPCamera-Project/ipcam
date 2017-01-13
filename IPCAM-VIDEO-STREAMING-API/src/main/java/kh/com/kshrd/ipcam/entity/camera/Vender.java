package kh.com.kshrd.ipcam.entity.camera;

import com.fasterxml.jackson.annotation.JsonProperty;

import kh.com.kshrd.ipcam.entity.camera.base.BaseEntity;

public class Vender {

	@JsonProperty("VENDER_ID")
	private int vender_id;

	@JsonProperty("LOGO")
	private String logo;

	@JsonProperty("NAME")
	private  String name;

	@JsonProperty("ACTIVE")
	private  int active;

	public int getVender_id() {
		return vender_id;
	}

	public void setVender_id(int vender_id) {
		this.vender_id = vender_id;
	}

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

	@Override
	public String toString() {
		return "Vender [logo=" + logo + "]";
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
}

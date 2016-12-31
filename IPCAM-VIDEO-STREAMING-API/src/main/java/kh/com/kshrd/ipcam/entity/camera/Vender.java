package kh.com.kshrd.ipcam.entity.camera;

import com.fasterxml.jackson.annotation.JsonProperty;

import kh.com.kshrd.ipcam.entity.camera.base.BaseEntity;

public class Vender extends BaseEntity{

	@JsonProperty("VENDER_ID")
	private int vender_id;

	@JsonProperty("LOGO")
	private String logo;

	@JsonProperty("name")
	private  String name;

	public int getVender_id() {
		return vender_id;
	}

	public void setVender_id(int vender_id) {
		this.vender_id = vender_id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
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
	
}

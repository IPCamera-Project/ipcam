package kh.com.kshrd.ipcam.entity.camera.base;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class BaseEntity {
	@JsonProperty("ID")
	protected int id;
	
	@JsonProperty("NAME")
	protected String name;
	
	/*@ApiModelProperty(required = true, dataType="Timestamp")*/
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@JsonProperty("CREATE_DATE")
	protected Timestamp createDate;
	
	public BaseEntity() {}
	
	BaseEntity(int id, String name, Timestamp createDate){
		this.id = id;
		this.name = name;
		this.createDate = createDate;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "BaseEntity [id=" + id + ", name=" + name + ", createDate=" + createDate + "]";
	}
	
}

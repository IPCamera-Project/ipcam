package kh.com.kshrd.ipcam.entity.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class  User{

	@JsonProperty("USER_ID")
	private  int user_id;

	@JsonProperty("USERNAME")
	protected String username;

	@JsonProperty("EMAIL")
	private String email;

	@JsonProperty("PASSWORD")
	protected String password;

	@JsonProperty("IMAGE")
	protected String image;

	@JsonProperty("STATUS")
	protected boolean status;


	@JsonProperty("ROLE")
	private  Role role;


	@JsonProperty("USER_FACEBOOK_ID")
	private int user_facebook_id;

	public int getUser_facebook_id() {
		return user_facebook_id;
	}

	public void setUser_facebook_id(int user_facebook_id) {
		this.user_facebook_id = user_facebook_id;
	}


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

	public Role getRole() {return role;}

	public void setRole(Role role) {this.role = role;}
}
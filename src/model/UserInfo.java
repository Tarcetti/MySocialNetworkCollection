package model;

import java.io.Serializable;

public class UserInfo implements Serializable,IBasicPersistentObject{
	private String id;
	private String username;
	private String full_name;
	private String profile_picture;
	private String bio;
	private String website;
	
	public UserInfo(){
		id = "";
		username = "";
		full_name = "";
		profile_picture = "";
		bio = "";
		website = "";
	}
	
	public UserInfo(String id, String username, String full_name, String profile_picture, String bio, String website){
		this.id = id;
		this.username = username;
		this.full_name = full_name;
		this.profile_picture = profile_picture;
		this.bio = bio;
		this.website = website;
	}
	
	public String getId(){
		return id;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getFull_name(){
		return full_name;
	}
	
	public void setFull_name(String full_name){
		this.full_name = full_name;
	}
	
	public String getProfile_picture(){
		return profile_picture;
	}
	
	public void setProfile_picture(String profile_picture){
		this.profile_picture = profile_picture;
	}
	
	public String getBio(){
		return bio;
	}
	
	public void setBio(String bio){
		this.bio = bio;
	}
	
	public String getWebsite(){
		return website;
	}
	
	public void setWebsite(String website){
		this.website = website;
	}
}

package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MediaObject implements Serializable,IBasicPersistentObject{
	private String id;
	private String type;
	private ArrayList<String> users;	
	private String filter;
	private ArrayList<String> tags;	
	private int commentsCount;
	private int likesCount;
	private String link;
	private String userdata;
	private String creationTime;
	private String image_link;
	
	
	public MediaObject(){
		id = "";
		type = "";
		users = null;
		filter = "";
		tags = null;
		commentsCount = 0;
		likesCount = 0;
		link = "";
		userdata = "";
		creationTime = "";
		image_link = "";
	}
	
	public MediaObject(String id, String type, ArrayList<String> users, String filter, ArrayList<String> tags, int commentsCount, 
			int likesCount, String link, String userdata, String creationTime, String image_link){
		this.id = id;
		this.type = type;
		this.users = users;
		this.filter = filter;
		this.tags = tags;
		this.commentsCount = commentsCount;
		this.likesCount = likesCount;
		this.link = link;
		this.userdata = userdata;
		this.creationTime = creationTime;
		this.image_link = image_link;
	}
	
	public String getId(){
		return id;
	}
	
	public void setId(String id){
		this.id = id;
	}

	public String getType(){
		return type;
	}
	
	public void setType(String type){
		this.type = type;
	}

	public List<String> getUsers(){
		return users;
	}
	
	public void setUsers(ArrayList<String> users){
		this.users = users;
	}

	public String getFilter(){
		return filter;
	}
	
	public void setFilter(String filter){
		this.filter = filter;
	}

	public List<String> getTags(){
		return tags;
	}
	
	public void setTags(ArrayList<String> tags){
		this.tags = tags;
	}

	public int getCommentsCount(){
		return commentsCount;
	}
	
	public void setCommentsCount(int commentsCount){
		this.commentsCount = commentsCount;
	}
	
	public int getLikesCount(){
		return likesCount;
	}
	
	public void setLikesCount(int likesCount){
		this.likesCount = likesCount;
	}
	

	public String getLink(){
		return link;
	}
	
	public void setLink(String link){
		this.link = link;
	}
	
	public String getUserdata(){
		return userdata;
	}
	
	public void setUserdata(String userData){
		this.userdata = userdata;
	}
	

	public String getCreationTime(){
		return creationTime;
	}
	
	public void setCreationTime(String creationTime){
		this.creationTime = creationTime;
	}

	public String getImage_link(){
		return image_link;
	}
	
	public void setImage_link(String image_link){
		this.image_link = image_link;
	}
	
}

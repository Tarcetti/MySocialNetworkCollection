package model;
import java.io.Serializable;

public class Interest implements Serializable, IBasicPersistentObject{
	private long id;
	private String socialNetwork;
	private String hashtag;
	private String userToFollow;	
	
	public Interest(){
		socialNetwork = "";
		hashtag = "";
		userToFollow = "";
		id = 0;
	}	
	
	public Interest(String socialNetwork, String hashtag, String userToFollow){
		this.socialNetwork = socialNetwork;
		this.hashtag = hashtag;
		this.userToFollow = userToFollow;
		this.id = 0;
	}
	
	public long getId(){
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}	
	
	public String getSocialNetwork(){
		return socialNetwork;
	}
	
	public void setSocialNetwork(String socialNetwork){
		this.socialNetwork = socialNetwork;
	}
	
	public String getHashtag(){
		return hashtag;
	}
	
	public void setHashtag(String hashtag){
		this.hashtag = hashtag;
	}
	
	public String getUserToFollow(){
		return userToFollow;
	}
	
	public void setUserToFollow(String userToFollow){
		this.userToFollow = userToFollow;
	}
	
}

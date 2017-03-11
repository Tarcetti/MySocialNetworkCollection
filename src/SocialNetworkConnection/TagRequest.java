package SocialNetworkConnection;

import java.util.ArrayList;

import model.MediaObject;

public abstract class TagRequest extends GetMediaObjectRequest {
	private String tagUrl;
	private AccessToken accessToken;		
		
	public TagRequest(String tagUrl, AccessToken accessToken){
		this.tagUrl = tagUrl;
		this.accessToken = accessToken;
	}
	
	public String getTagUrl(){
		return tagUrl;
	}
	
	public void setTagUrl(String tagUrl){
		this.tagUrl = tagUrl;
	}
	
	public AccessToken getAccessToken(){
		return accessToken;
	}
	
	public void setAccessToken(AccessToken accessToken){
		this.accessToken = accessToken;
	}
	
	public ArrayList<MediaObject> getMediaFromTag(String tag){
		ArrayList<MediaObject> returnList = new ArrayList<MediaObject>();
		String httpurl = getRequestUrl(tag)+getParameters();
		this.setRequestUrl(httpurl);
		StringBuffer response = this.doGetRequest();
		System.out.println(response.toString());
        returnList = getMediaObjectListFromResponse(response.toString());	        
   	    return returnList;
	}	
	
	

}

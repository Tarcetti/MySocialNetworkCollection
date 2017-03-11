package SocialNetworkConnection;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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

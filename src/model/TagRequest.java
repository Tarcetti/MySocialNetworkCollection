package model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class TagRequest extends Request {
	private String tagUrl;
	private AccessToken accessToken;
	
	public abstract String getRequestUrl(String tag);
	public abstract String getParameters();
	public abstract ArrayList<MediaObject> getMediaObjectListFromResponse(String response); 
	
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
	    try {
	    	    
	        String httpurl = getRequestUrl(tag)+getParameters();

	        URL url = new URL(httpurl);	        
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setDoOutput(true);
	        conn.setInstanceFollowRedirects(false); 
	        conn.setRequestMethod("GET");

	        String urlParameters = getParameters();

	        conn.setRequestProperty("Content-Type", "text/plain"); 
	        conn.setRequestProperty("charset", "utf-8");

	        BufferedReader in = new BufferedReader(new InputStreamReader(
	                conn.getInputStream()));
	        
	        String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			System.out.println(response.toString());
	        returnList = getMediaObjectListFromResponse(response.toString());	        
	        
	  }	catch(Exception e){
		  System.out.println(e.getMessage());
	  }
 	  return returnList;
	}	
	
	

}

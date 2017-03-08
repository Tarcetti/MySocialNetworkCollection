package model;

import org.json.simple.parser.ParseException;

import java.util.Collection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class AccessTokenInstagram extends AccessToken {
	private String username;
	private String bio;
	private String full_name;
	private String profile_picture;
	private String website;
	private String id;
	private String access_token;	
	
	public void readStream(String stream){
		JSONParser parser = new JSONParser();
		try{			
			Object obj = parser.parse(stream);			
			JSONObject jsonObject = (JSONObject)obj;
			Collection values = jsonObject.values();
			access_token = jsonObject.get("access_token").toString();
			JSONObject user = (JSONObject)jsonObject.get("user");
			username = user.get("username").toString();
			bio = user.get("bio").toString();
			full_name = user.get("full_name").toString();
			profile_picture = user.get("profile_picture").toString();
			website = user.get("website").toString();
			id = user.get("id").toString();
			System.out.println("username: "+username);
			System.out.println("bio: "+bio);
			System.out.println("full_name: "+full_name);
			System.out.println("profile_picture: "+profile_picture);
			System.out.println("website: "+website);
			System.out.println("id: "+id);
			System.out.println("access_token: "+access_token);
		}catch(ParseException pe){
			System.out.print("Error en : "+pe.getMessage());		    
			System.out.print(pe.getPosition());			
		    System.out.println(pe);			
		}
	}
	
	public String getUsername(){
		return username;
	}
	
	public String getBio(){
		return bio;
	}

	public String getFull_name(){
		return full_name;
	}

	public String getProfile_picture(){
		return profile_picture;
	}

	public String getWebsite(){
		return website;
	}

	public String getId(){
		return id;
	}

	public String getAccess_token(){
		return access_token;
	}
	
}

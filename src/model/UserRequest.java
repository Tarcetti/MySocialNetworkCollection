package model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class UserRequest extends GetMediaObjectRequest {
	private String userUrl;
	private AccessToken accessToken;	
	
	public abstract ArrayList<MediaObject> getMediaFromUser(String username);
	
	public abstract ArrayList<UserInfo> getUserInfoFromName(String userName);	
	
	public UserRequest(String userUrl, AccessToken accessToken){
		this.userUrl = userUrl;
		this.accessToken = accessToken;
	}
	
	public String getUserUrl(){
		return userUrl;
	}
	
	public void setUserUrl(String userUrl){
		this.userUrl = userUrl;
	}
	
	public AccessToken getAccessToken(){
		return accessToken;
	}
	
	public void setAccessToken(AccessToken accessToken){
		this.accessToken = accessToken;
	}		

}

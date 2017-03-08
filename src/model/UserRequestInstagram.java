package model;

import java.util.ArrayList;
import java.util.Collection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class UserRequestInstagram extends UserRequest {

	public UserRequestInstagram(String userUrl, AccessToken accessToken) {
		super(userUrl, accessToken);
	}

	@Override
	public String getRequestUrl(String userId) {
		return getUserUrl()+"/users/"+userId+"/media/recent?";
	}

	@Override
	public String getParameters() {
		AccessTokenInstagram accessToken = (AccessTokenInstagram) getAccessToken();
		return "access_token="+accessToken.getAccess_token();
	}

	@Override
	public ArrayList<MediaObject> getMediaObjectListFromResponse(String response) {
		return InstagramUtilsSingleton.getInstance().getMediaObjectFromResponse(response);
	}

	@Override
	public ArrayList<MediaObject> getMediaFromUser(String username){
		ArrayList<MediaObject> returnList = new ArrayList<MediaObject>();
		ArrayList<UserInfo> users = getUserInfoFromName(username);
        for(UserInfo user : users) 
        { 
        	ArrayList<MediaObject> innerMediaList = new ArrayList<MediaObject>();
    		String httpurl = getRequestUrl(user.getId())+getParameters();
    		this.setRequestUrl(httpurl);
    		StringBuffer response = this.doGetRequest();
    		System.out.println(response.toString());
    		innerMediaList = getMediaObjectListFromResponse(response.toString());
    		returnList.addAll(innerMediaList);             
        } 
   	    return returnList;
	}			
	
	@Override
	public ArrayList<UserInfo> getUserInfoFromName(String userName){
		ArrayList<UserInfo> list = new ArrayList<UserInfo>();
		AccessTokenInstagram accessToken = (AccessTokenInstagram)getAccessToken();
		String httpurl = getUserUrl()+"/users/"+"search?q="+userName+"&access_token="+accessToken.getAccess_token();
		this.setRequestUrl(httpurl);
		StringBuffer response = this.doGetRequest();
		System.out.println(response.toString());
        list = InstagramUtilsSingleton.getInstance().getUserInfoFromResponse(response.toString());	        		
		return list;
	}
	
		
}

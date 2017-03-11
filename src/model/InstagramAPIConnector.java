package model;

import java.util.ArrayList;

public class InstagramAPIConnector extends SocialNetworkAPIConnector {
	
	public InstagramAPIConnector(){
		this.setSocialNetwork("Instagram");
		this.setAccessRequestUrl("https://api.instagram.com/oauth");
		this.setOperationRequestUrl("https://api.instagram.com/v1");
		this.setClientId("eb502a88f7154f20a4b7a93240e050eb");
		this.setClientSecret("137a4d1405374563a8852e0ddf997b35");
	}
	
	public void AccessRequest(){
		//AccessRequestInstagram accessRequest = new AccessRequestInstagram(AccessRequestInstagram(requesturl,authurl,clientId,secret,grantType,redirect_uri,code,scope);
		String authurl = this.getAccessRequestUrl()+"/authorize/"; 
		String requesturl = this.getAccessRequestUrl()+"/oauth/access_token";
		String clientId = this.getClientId();
		String secret = this.getClientSecret();
		String grantType = "authorization_code";
		String redirect_uri = "http://localhost";
		String code = "code";				
		String scope = "public_content";
		AccessRequestInstagram accessRequest = new AccessRequestInstagram(requesturl,authurl,clientId,secret,grantType,redirect_uri,code,scope);
		accessRequest.getAuthorization();
		AccessToken accessToken = accessRequest.getAccessTokenContent();		
		this.setAccessToken(accessToken);		
	}
	
	protected ArrayList<MediaObject> getMediaFromTagInternal(String tag){
		TagRequestInstagram tagRequest = new TagRequestInstagram(this.getOperationRequestUrl(),this.getAccessToken());
		ArrayList<MediaObject>list = tagRequest.getMediaFromTag(tag);    	
		return list;
    }
    
	protected ArrayList<MediaObject> getMediaFromUserInternal(String userName){
		UserRequestInstagram userRequest = new UserRequestInstagram(this.getOperationRequestUrl(),this.getAccessToken());
		ArrayList<MediaObject>list = userRequest.getMediaFromUser(userName);    	
		return list;    	
    }
    
	protected ArrayList<UserInfo> getUsersInfoInternal(String userName){
		UserRequestInstagram userRequest = new UserRequestInstagram(this.getOperationRequestUrl(),this.getAccessToken());
		ArrayList<UserInfo>list = userRequest.getUserInfoFromName(userName);    	
		return list;    	
    }

}

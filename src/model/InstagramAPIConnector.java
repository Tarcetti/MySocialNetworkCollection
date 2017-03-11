package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class InstagramAPIConnector extends SocialNetworkAPIConnector {
	Properties properties;
	public InstagramAPIConnector(){
		InputStream input = null;
		properties = new Properties();
		try{
			input = new FileInputStream("configuration.properties");
			properties.load(input);
			this.setSocialNetwork("Instagram");
			this.setAccessRequestUrl(properties.getProperty("accessRequestUrlInstagram"));
			this.setOperationRequestUrl(properties.getProperty("operationRequestUrlInstagram"));
			this.setClientId(properties.getProperty("clientIdInstagram"));
			this.setClientSecret(properties.getProperty("clientSecretInstagram"));								
		} catch(IOException ex){
			ex.printStackTrace();
		} finally{
			if(input != null){
				try{
					input.close();
				} catch (IOException e){
					e.printStackTrace();
				}
			}
		}
	}
	
	public void AccessRequest(){		
		String authurl = this.getAccessRequestUrl()+properties.getProperty("authUrlInstagramEnding"); 
		String requesturl = this.getAccessRequestUrl()+properties.getProperty("authUrlInstagramAccessEnding");
		String clientId = this.getClientId();
		String secret = this.getClientSecret();
		String grantType = properties.getProperty("grantTypeInstagram");
		String redirect_uri = properties.getProperty("redirectUriInstagram");
		String code = properties.getProperty("codeInstagram");				
		String scope = properties.getProperty("scopeInstagram");
		if (InstagramUtilsSingleton.getInstance().getAccessToken() == null){
			AccessRequestInstagram accessRequest = new AccessRequestInstagram(requesturl,authurl,clientId,secret,grantType,redirect_uri,code,scope);
			accessRequest.getAuthorization();
			AccessToken accessToken = accessRequest.getAccessTokenContent();		
			this.setAccessToken(accessToken);
			InstagramUtilsSingleton.getInstance().setAccessToken((AccessTokenInstagram)accessToken);		
		}else{
			this.setAccessToken(InstagramUtilsSingleton.getInstance().getAccessToken());
		}
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

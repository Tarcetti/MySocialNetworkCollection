package model;

import java.util.ArrayList;

public abstract class SocialNetworkAPIConnector {
	private String socialNetwork;
	private String clientId;
	private String clientSecret = null;	
	private String accessRequestUrl;	
    private String operationRequestUrl = null;            
    private AccessToken accessToken = null;    
    private static SocialNetworkAPIConnector instance = null;
        
    public abstract void AccessRequest();
        
    protected abstract ArrayList<MediaObject> getMediaFromTagInternal(String tag);
    
    protected abstract ArrayList<MediaObject> getMediaFromUserInternal(String userName);
    
    protected abstract ArrayList<UserInfo> getUsersInfoInternal(String userName);
    
    public void getMediaFromTag(String tag){
    	ArrayList<MediaObject> list = getMediaFromTagInternal(tag);
		MediaObjectDAO modao = new MediaObjectDAO();
        for(MediaObject mo : list) 
        { 
            modao.saveBasicPersistentObject(mo); 
        }        
        System.out.println("Se han encontrado "+list.size()+" elementos con el tag "+tag+".");  
    }
    
    public void getMediaFromUser(String userName){
    	ArrayList<MediaObject> list = getMediaFromUserInternal(userName);
		MediaObjectDAO modao = new MediaObjectDAO();
        for(MediaObject mo : list) 
        { 
            modao.saveBasicPersistentObject(mo); 
        }        
        System.out.println("Se han encontrado "+list.size()+" elementos con nombre de usuario "+userName+".");  
    }
    
    public void getUserInfo(String userName){
    	ArrayList<UserInfo> list = getUsersInfoInternal(userName);		
		UserInfoDAO uidao = new UserInfoDAO();
        for(UserInfo user : list) 
        { 
        	uidao.saveBasicPersistentObject(user); 
        }        
        System.out.println("Se han encontrado "+list.size()+" usuarios con el nombre de usuario "+userName+".");  
    }    
    
    
	public String getSocialNetwork() {
		return socialNetwork;
	}
	public void setSocialNetwork(String socialNetwork) {
		this.socialNetwork = socialNetwork;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getAccessRequestUrl() {
		return accessRequestUrl;
	}
	public void setAccessRequestUrl(String accessRequestUrl) {
		this.accessRequestUrl = accessRequestUrl;
	}
	public String getOperationRequestUrl() {
		return operationRequestUrl;
	}
	public void setOperationRequestUrl(String operationRequestUrl) {
		this.operationRequestUrl = operationRequestUrl;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public AccessToken getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(AccessToken accessToken) {
		this.accessToken = accessToken;
	}           
	
    

}

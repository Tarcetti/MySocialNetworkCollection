package SocialNetworkConnection;

public class AccessRequestInstagram extends AccessRequest {
	String client_id;
	String client_secret;
	String grant_type;
	String redirect_uri;	
	String scope;
	
	public AccessRequestInstagram(String requestUrl, String authorizationUrl,String clientId, String clientSecret, String grantType, String redirectUri, String code, String scope){
		super(requestUrl,authorizationUrl);
		client_id = clientId;
		client_secret = clientSecret;
		grant_type = grantType;
		redirect_uri = redirectUri;
		authorizationCode = code;
		this.scope = scope;
	}
	
	public String getClientId(){
		return client_id;
	}
	
	public void setClientId(String clientId){
		this.client_id = clientId;
	}
	
	public String getClientSecret(){
		return client_secret;		
	}
	
	public void setClientSecret(String clientSecret){
		this.client_secret = clientSecret;
	}
	
	public String getGrantType(){
		return grant_type;
	}
	
	public void setGrantType(String grantType){
		this.grant_type = grantType;
	}
	
	public String getRedirectUri(){
		return redirect_uri;
	}
	
	public void setRedirectUri(String redirectUri){
		this.redirect_uri = redirectUri;
	}

	public String getScope(){
		return scope;
	}
	
	public void setScope(String scope){
		this.scope = scope;
	}
	
	
	public String getStringRequest(){
		String url = "";
		url = "client_id=" + client_id
                + "&client_secret=" + client_secret
                + "&grant_type=authorization_code"
                + "&redirect_uri=" + redirect_uri
                + "&code="+authorizationCode;
		return url;
	}

	public String getStringAuthorization(){
		String url = "";
		url = "client_id=" + client_id
                + "&redirect_uri=" + redirect_uri                
                + "&response_type="+authorizationCode
				+ "&scope="+scope;
		return url;
	}
	
	public AccessToken getAccessToken(String accessTokenString){
		AccessTokenInstagram accessToken = new AccessTokenInstagram(accessTokenString);		
		return accessToken;
	}
	
	
}

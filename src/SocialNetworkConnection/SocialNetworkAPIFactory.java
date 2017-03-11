package SocialNetworkConnection;

public class SocialNetworkAPIFactory {

	public SocialNetworkAPIConnector getSocialNetworkAPIConnector(String socialNetwork){
		SocialNetworkAPIConnector api = null;
		if (socialNetwork.toUpperCase().equals("INSTAGRAM")){
			api = new InstagramAPIConnector();
		}
		return api;
	}
}

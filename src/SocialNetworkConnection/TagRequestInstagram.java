package SocialNetworkConnection;

import java.util.ArrayList;

import model.MediaObject;

public class TagRequestInstagram extends TagRequest {

	public TagRequestInstagram(String tagUrl, AccessToken accessToken) {
		super(tagUrl, accessToken);
	}

	@Override
	public String getRequestUrl(String tag) {
		return getTagUrl()+"/tags/"+tag+"/media/recent?";
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

}

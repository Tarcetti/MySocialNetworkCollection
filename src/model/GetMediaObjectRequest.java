package model;

import java.util.ArrayList;

public abstract class GetMediaObjectRequest extends Request {

	public abstract String getRequestUrl(String tag);
	
	public abstract String getParameters();		
	
	public abstract ArrayList<MediaObject> getMediaObjectListFromResponse(String response);

}

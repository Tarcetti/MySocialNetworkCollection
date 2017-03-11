package DataBaseConnection;

import model.MediaObject;

public class MediaObjectDAO extends BasicPersistentObjectDAO{

	protected String getFromTable(){
		return "MediaObject";
	}
	
	protected Object getDaoClass(){
		return MediaObject.class;
	}

}

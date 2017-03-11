package DataBaseConnection;

import model.Interest;

public class InterestDAO extends BasicPersistentObjectDAO{

	protected String getFromTable(){
		return "Interest";
	}
	
	protected Object getDaoClass(){
		return Interest.class;
	}

}

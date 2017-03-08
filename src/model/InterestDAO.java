package model;


public class InterestDAO extends BasicPersistentObjectDAO{

	protected String getFromTable(){
		return "Interest";
	}
	
	protected Object getDaoClass(){
		return Interest.class;
	}

}

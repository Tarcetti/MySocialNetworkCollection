package model;

import DataBaseConnection.InterestDAO;

public abstract class InterestRegistrator {
	
	public abstract String getSocialNetwork();	
	
	public void RegisterInterestTag(String tag){
		Interest interest = new Interest(getSocialNetwork(), tag, "");
		InterestDAO dao = new InterestDAO();
		dao.saveBasicPersistentObject(interest);
	}	
	
	public void RegisterInterestUser(String user){
		Interest interest = new Interest(getSocialNetwork(), "", user);
		InterestDAO dao = new InterestDAO();
		dao.saveBasicPersistentObject(interest);		
	}
	
	
}

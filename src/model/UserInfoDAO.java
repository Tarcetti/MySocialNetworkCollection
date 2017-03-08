package model;


public class UserInfoDAO extends BasicPersistentObjectDAO{

	protected String getFromTable(){
		return "UserInfo";
	}

	protected Object getDaoClass(){
		return UserInfo.class;
	}
	
}

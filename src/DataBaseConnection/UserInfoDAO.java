package DataBaseConnection;

import model.UserInfo;

public class UserInfoDAO extends BasicPersistentObjectDAO{

	protected String getFromTable(){
		return "UserInfo";
	}

	protected Object getDaoClass(){
		return UserInfo.class;
	}
	
}

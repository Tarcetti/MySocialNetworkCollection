package model;

import java.util.ArrayList;
import java.util.List;

public class InterestDownloader {
	
	public void downloadInterests(){
		InterestDAO dao = new InterestDAO();
		List<IBasicPersistentObject> list = dao.getListBasicPersistentObject();
        for(IBasicPersistentObject interest : list) 
        { 
             interestAnalyzer((Interest)interest);
        }       		
	}
	
	private void interestAnalyzer(Interest interest){
		SocialNetworkAPIFactory factory = new SocialNetworkAPIFactory();
		SocialNetworkAPIConnector connector = factory.getSocialNetworkAPIConnector(interest.getSocialNetwork());
		connector.AccessRequest();
		if (!interest.getUserToFollow().isEmpty()){
			connector.getUserInfo(interest.getUserToFollow());
			connector.getMediaFromUser(interest.getUserToFollow());
		}
		if (!interest.getHashtag().isEmpty()){
			connector.getMediaFromTag(interest.getHashtag());			
		}
		
	}
}

package model;

import SocialNetworkConnection.InstagramInterestRegistrator;



public class Main {

	public static void  main(String[] args){
		InterestRegistrator registrator = new InstagramInterestRegistrator();
		registrator.RegisterInterestUser("ines");
		registrator.RegisterInterestTag("simpsons");
		registrator.RegisterInterestTag("delicious");
		InterestDownloader downloader = new InterestDownloader();
		downloader.downloadInterests();
	}
	
}


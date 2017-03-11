package model;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



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


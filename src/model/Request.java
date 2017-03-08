package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public abstract class Request {
	protected String requestUrl;
		
	public void setRequestUrl(String requestUrl){
		this.requestUrl = requestUrl;
	}
	
	public String getRequestUrl(){
		return requestUrl;
	}

	public StringBuffer doGetRequest(){
		StringBuffer response = new StringBuffer();
	    try {    	    
	        URL url = new URL(requestUrl);	        
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setDoOutput(true);
	        conn.setInstanceFollowRedirects(false); 
	        conn.setRequestMethod("GET");

	        conn.setRequestProperty("Content-Type", "text/plain"); 
	        conn.setRequestProperty("charset", "utf-8");

	        BufferedReader in = new BufferedReader(new InputStreamReader(
	                conn.getInputStream()));
	        
	        String inputLine;			
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
							        	        
	        
	    }	catch(Exception e){
	    	System.out.println(e.getMessage());
	    }
	    return response;
	}
	
	
}

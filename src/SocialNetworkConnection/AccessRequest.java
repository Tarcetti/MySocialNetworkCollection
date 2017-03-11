package SocialNetworkConnection;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public abstract class AccessRequest extends Request {
	
	private String authorizationUrl;	
	protected String authorizationCode;
	
	public AccessRequest(String requestUrl,String authorizationUrl){
		this.requestUrl = requestUrl;
		this.authorizationUrl = authorizationUrl;
	}
		
	public void setAuthorizationUrl(String authorizationUrl){
		this.authorizationUrl = authorizationUrl;
	}
	
	public String getAuthorizationUrl(){
		return authorizationUrl;
	}
	
	public String getAuthorizationCode(){
		return authorizationCode;
	}
	
	public void setAuthorizationCode(String code){
		authorizationCode = code;
	}
	
	
	
	public abstract String getStringRequest();
	
	public abstract String getStringAuthorization();	
	
	public abstract AccessToken getAccessToken(String accessTokenString);
	
	public void getAuthorization(){
	    try {

	        String httpurl = authorizationUrl+"?"+getStringAuthorization();

	        URL url = new URL(httpurl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");

	        String urlParameters = getStringRequest();

	        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
	        conn.setRequestProperty("charset", "utf-8");
	        conn.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));

	        conn.setDoOutput(true);
	        DataOutputStream wr = new DataOutputStream(conn.getOutputStream ());
	        wr.writeBytes(urlParameters);
	        wr.flush();
	        wr.close();

	        BufferedReader in = new BufferedReader(new InputStreamReader(
	                conn.getInputStream()));
	        System.out.println(conn.getURL());
	        HttpResponse response;
	        HttpGet get = new HttpGet(httpurl);	        
	        HttpClient client = HttpClients.createDefault();
	        response = client.execute(get);	        
	        Desktop.getDesktop().browse(new URI(conn.getURL().toString()));
	        	        
	        System.out.println("Introduzca el código: ");	        
	        Scanner scanner = new Scanner(System.in);	        
	        authorizationCode = scanner.nextLine();
	        System.out.println("el código es "+authorizationCode);	        	      
	        	         	      
	        
	  }	catch(Exception e){
		  System.out.println(e.getMessage());
	  }
	}

	public AccessToken getAccessTokenContent(){
		AccessToken token = null;
	    try {

	        String httpurl = requestUrl+"?"+getStringRequest();

	        URL url = new URL(httpurl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");

	        String urlParameters = getStringRequest();

	        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
	        conn.setRequestProperty("charset", "utf-8");
	        conn.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));

	        conn.setDoOutput(true);
	        DataOutputStream wr = new DataOutputStream(conn.getOutputStream ());
	        wr.writeBytes(urlParameters);
	        wr.flush();
	        wr.close();

	        BufferedReader in = new BufferedReader(new InputStreamReader(
	                conn.getInputStream()));
	        
	        String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//print result
			System.out.println(response.toString());
	        token = getAccessToken(response.toString());
	        
	        
	  }	catch(Exception e){
		  System.out.println(e.getMessage());
	  }
 	  return token;
	}	
	
	
}

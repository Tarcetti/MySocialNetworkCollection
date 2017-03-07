package model;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;



public class Main {

	public static void  main(String[] args){
		//basicConnection();
		//hibernateConnection();
		testWebRequest();
	}	
	
	private static void basicConnection(){
        Connection conn = null;
        String urlDatabase =  "jdbc:postgresql://localhost:5432/SocialNetworkCollections"; 
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(urlDatabase,  "postgres", "1");
        } catch (Exception e) {
            System.out.println("Ocurrio un error : "+e.getMessage());
        }
        System.out.println("La conexión se realizo sin problemas! =) ");		
	}
	
	private static void hibernateConnection(){	  
		InterestDAO contactosDAO = new InterestDAO(); 
	    Interest contactoRecuperado = null;  
	    long idAEliminar = 0;  

        //Creamos tes instancias de Contacto 
        Interest contacto1 = new Interest("Contacto 1", "contacto1@contacto.com", "12345678"); 
        Interest contacto2 = new Interest("Contacto 2", "contacto2@contacto.com", "87654321"); 
        Interest contacto3 = new Interest("Contacto 3", "contacto3@contacto.com", "45612378");  
        Interest contacto4 = new Interest("Contacto 4", "contacto4@contacto.com", "44444444");        
        
        //Guardamos las tres instancias, guardamos el id del contacto1 para usarlo posteriormente 
        idAEliminar = contactosDAO.saveInterest(contacto1); 
        contactosDAO.saveInterest(contacto2); 
        contactosDAO.saveInterest(contacto3);
        contactosDAO.saveInterest(contacto4);

        //Modificamos el contacto 2 y lo actualizamos 
        contacto2.setSocialNetwork("Nuevo Contacto 2"); 
        contactosDAO.updateInterest(contacto2);  

        //Recuperamos el contacto1 de la base de datos 
        contactoRecuperado = contactosDAO.getInterest(idAEliminar); 
        System.out.println("Recuperamos a " + contactoRecuperado.getSocialNetwork());  

        //Eliminamos al contactoRecuperado (que es el contacto3) 
        contactosDAO.deleteInterest(contactoRecuperado);  

        //Obtenemos la lista de contactos que quedan en la base de datos y la mostramos 
        List<Interest> listaContactos = contactosDAO.getListInterest();  
        System.out.println("Hay " + listaContactos.size() + "contactos en la base de datos");  

        for(Interest c : listaContactos) 
        { 
            System.out.println("-> " + c.getSocialNetwork()); 
        } 
    }	
	
	private static void testWebRequest(){
/*		try {
			URL url = new URL("https://api.instagram.com/oauth/authorize/?client_id=eb502a88f7154f20a4b7a93240e050eb&redirect_uri=http://your-redirect-uri&response_type=code");			
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String strTemp = "";
			while (null != (strTemp = br.readLine())) {
				System.out.println(strTemp);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}*/
		String authurl = "https://api.instagram.com/oauth/authorize/";
		String requesturl = "https://api.instagram.com/oauth/access_token";
		String clientId = "eb502a88f7154f20a4b7a93240e050eb";
		String secret = "137a4d1405374563a8852e0ddf997b35";
		String grantType = "authorization_code";
		String redirect_uri = "http://localhost";
		String code = "code";				
		String scope = "public_content";
		AccessRequestInstagram ar = new AccessRequestInstagram(requesturl,authurl,clientId,secret,grantType,redirect_uri,code,scope);
		ar.getAuthorization();
		ar.getAccessTokenContent();
	}

}


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
        Interest contacto5 = new Interest("Contacto 5", "contacto4@contacto.com", "44444445");     
        
        //Guardamos las tres instancias, guardamos el id del contacto1 para usarlo posteriormente 
        idAEliminar = (Long) contactosDAO.saveBasicPersistentObject(contacto1); 
        contactosDAO.saveBasicPersistentObject(contacto2); 
        contactosDAO.saveBasicPersistentObject(contacto3);
        contactosDAO.saveBasicPersistentObject(contacto4);
        contactosDAO.saveBasicPersistentObject(contacto5);

        //Modificamos el contacto 2 y lo actualizamos 
        contacto2.setSocialNetwork("Nuevo Contacto 2"); 
        contactosDAO.updateBasicPersistentObject(contacto2);  

        //Recuperamos el contacto1 de la base de datos 
        contactoRecuperado = (Interest)contactosDAO.getBasicPersistentObject(idAEliminar); 
        System.out.println("Recuperamos a " + contactoRecuperado.getSocialNetwork());  

        //Eliminamos al contactoRecuperado (que es el contacto3) 
        contactosDAO.deleteBasicPersistentObject(contactoRecuperado);  

        //Obtenemos la lista de contactos que quedan en la base de datos y la mostramos 
        List<IBasicPersistentObject> listaContactos = contactosDAO.getListBasicPersistentObject();  
        System.out.println("Hay " + listaContactos.size() + " Interest en la base de datos");  

        for(IBasicPersistentObject c : listaContactos) 
        { 
            System.out.println("-> " + ((Interest)c).getSocialNetwork()); 
        } 
        
        UserInfo user1 = new UserInfo("1", "Lucas", "Lucas T", "jt", "bio empty", "www.notengo.com");
        UserInfo user2 = new UserInfo("2", "Maria", "Maria I", "in", "bioe empty", "www.notiene.com");
        UserInfoDAO uidao = new UserInfoDAO();
        String iduser1 = (String)uidao.saveBasicPersistentObject(user1);
        System.out.println("Id user1: "+iduser1);
        uidao.saveBasicPersistentObject(user2);

        listaContactos = uidao.getListBasicPersistentObject();  
        System.out.println("Hay " + listaContactos.size() + " UserInfo en la base de datos");  

        for(IBasicPersistentObject c : listaContactos) 
        { 
            System.out.println("-> " + ((UserInfo)c).getFull_name()); 
        } 
        
        
        ArrayList<String> users = new ArrayList<String>();
        users.add("Lucas");
        users.add("Ricky");
        ArrayList<String> tags = new ArrayList<String>();
        tags.add("simpsons");
        tags.add("redbee");
        ArrayList<String> tags2 = new ArrayList<String>();
        tags2.add("redbee");
        tags2.add("goku");
  
        MediaObject mo = new MediaObject("1","image",users,"NoFilter",tags,0,1,"www.nolink.com","1 Lucas","yesterday","emptylink");
        MediaObject mo2 = new MediaObject("2","image2",users,"NoFilter2",tags2,2,3,"www.nolin1k.com","2 Lucas","today","emptylink");
        MediaObjectDAO modao = new MediaObjectDAO();
        String idmo = (String)modao.saveBasicPersistentObject(mo);
        System.out.println("Id mo1: "+idmo);
        modao.saveBasicPersistentObject(mo2);
        
        listaContactos = modao.getListBasicPersistentObject();  
        System.out.println("Hay " + listaContactos.size() + " MediaObject en la base de datos");  

        for(IBasicPersistentObject c : listaContactos) 
        { 
            System.out.println("-> " + ((MediaObject)c).getTags()); 
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
		AccessToken at = ar.getAccessTokenContent();
		TagRequestInstagram tri = new TagRequestInstagram("https://api.instagram.com/v1",at);
		ArrayList<MediaObject>list = tri.getMediaFromTag("simpsons");
		MediaObjectDAO modao = new MediaObjectDAO();
        for(MediaObject mo : list) 
        { 
            modao.saveBasicPersistentObject(mo); 
        }        
        System.out.println("Se han grabado "+list.size()+" MediaObject en la base de datos");
        
        UserRequestInstagram uri = new UserRequestInstagram("https://api.instagram.com/v1",at);
        ArrayList<UserInfo> ulist = uri.getUserInfoFromName("tesei");
        UserInfoDAO uidao = new UserInfoDAO();
        for(UserInfo ui : ulist) 
        { 
            uidao.saveBasicPersistentObject(ui); 
        }        		
        System.out.println("Se han grabado "+list.size()+" UserInfo en la base de datos");
        
        list = uri.getMediaFromUser("tesei");
        for(MediaObject mo : list) 
        { 
            modao.saveBasicPersistentObject(mo); 
        }       
        
        
	}

}


package model;

import java.util.ArrayList;
import java.util.Collection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TagRequestInstagram extends TagRequest {

	public TagRequestInstagram(String tagUrl, AccessToken accessToken) {
		super(tagUrl, accessToken);
	}

	@Override
	public String getRequestUrl(String tag) {
		return getTagUrl()+"/tags/"+tag+"/media/recent?";
	}

	@Override
	public String getParameters() {
		AccessTokenInstagram accessToken = (AccessTokenInstagram) getAccessToken();
		return "access_token="+accessToken.getAccess_token();
	}

	@Override
	public ArrayList<MediaObject> getMediaObjectListFromResponse(String response) {
		ArrayList<MediaObject> list = new ArrayList<MediaObject>();
		JSONParser parser = new JSONParser();
		try{			
			Object obj = parser.parse(response);	
			JSONObject jsonBasicObject = (JSONObject)obj;
			JSONArray jsonArray = (JSONArray)jsonBasicObject.get("data");
			for (int i = 0; i < jsonArray.size();i++){
				JSONObject jsonObject = (JSONObject)jsonArray.get(i);
				String id = jsonObject.get("id").toString();
				String type = jsonObject.get("type").toString();
				String filter = jsonObject.get("filter").toString();
				String link = jsonObject.get("link").toString();				
				String creationTime = jsonObject.get("created_time").toString();
				JSONObject images = (JSONObject)jsonObject.get("images");
				JSONObject stdRes = (JSONObject)images.get("standard_resolution");
				String image_link = stdRes.get("url").toString();
				JSONObject user = (JSONObject)jsonObject.get("user");
				String userdata = user.get("id").toString()+" "+user.get("full_name").toString();
				JSONObject comments = (JSONObject)jsonObject.get("comments");
				int commentsCount = Integer.parseInt(comments.get("count").toString());
				JSONObject likes = (JSONObject)jsonObject.get("likes");
				int likesCount = Integer.parseInt(comments.get("count").toString());
				JSONArray tags = (JSONArray)jsonObject.get("tags");
				ArrayList<String> tagsList = new ArrayList<String>();
				for(int j = 0;j<tags.size();j++){
					String tagName = tags.get(j).toString();
					tagsList.add(tagName);
				}
				JSONArray users = (JSONArray)jsonObject.get("users_in_photo");
				ArrayList<String> usersList = new ArrayList<String>();
				for(int j = 0;j<users.size();j++){
					String username = users.get(j).toString();
					usersList.add(username);
				}
				MediaObject mo = new MediaObject(id,type,usersList,filter,tagsList,commentsCount,likesCount,link,userdata,creationTime,image_link);
				list.add(mo);				
			}
		}catch(ParseException pe){
			System.out.print("Error en : "+pe.getMessage());		    
			System.out.print(pe.getPosition());			
		    System.out.println(pe);			
		}
		
		return list;
			
	}

}

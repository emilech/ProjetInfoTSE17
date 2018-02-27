package useCase.alice;
import requete.Requete;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class infoComics {
	
	//Iron Fist 
	public String GetComics(String nom,String annee, String chap,String var) throws UnsupportedEncodingException{
		String categorie = VerifieComics(nom,annee,chap,var);
		String midUrl = "";
		String finUrl = "&ts=1&apikey=3560ed6e3f1152119172d09d56535ffe&hash=0d7c00977c36d82b6858c8fe6b3fe53b";
		String resultat= new String();
		String contenu;
		try {
			contenu = Requete.getInstance().getInfo(categorie, midUrl, finUrl);
			JSONObject obj = new JSONObject(contenu);
			System.out.println(contenu);
			
			obj = obj.getJSONObject("data");
			if(obj.get("total") != (Object) 0){
				JSONArray itemsList = obj.getJSONArray("results");
				JSONObject obj2 = new JSONObject();
				String titre = new String();
				String description = new String();
				String date = new String();
				String midimg = new String();
				for(int i = 0 ; i<itemsList.length();i++ ){
					
					obj2 = itemsList.getJSONObject(i);
					titre =  obj2.get("title").toString();
					description = obj2.get("description").toString();
					date = obj2.getJSONArray("dates").getJSONObject(0).get("date").toString();
					midimg = obj2.getJSONArray("images").getJSONObject(0).get("path").toString();
					System.out.println(titre+"\n");
					System.out.println(description+"\n");
					System.out.println(date+"\n");
					String creator ="";
					String img = "<IMG SRC=" +midimg+"/portrait_xlarge.jpg ";
					String finimg = "ALT='Thumbnails' TITLE='Test'>";
					img += finimg;
					for(int j=0; j<obj2.getJSONObject("creators").getInt("available");j++){
						creator = (obj2.getJSONObject("creators").getJSONArray("items").getJSONObject(j).get("name").toString()) + "\n";
						creator = creator + "\n" +(obj2.getJSONObject("creators").getJSONArray("items").getJSONObject(j).get("role").toString());
					}
					if(description.equals("null")){
						resultat += "<b> "+titre +"</b><br><br><u> Description :</u><br><p>Pas de Description<p><br><br><u> Date :</u><br>" +date +"<br><br>"+img+"<br>";
					}else if (date.equals("null")){
						resultat += "<b> "+titre +"</b><br><br><u> Description :</u><br>"+description + "<br><br><u> Date :</u><br><p> Pas de Date <p><br><br>"+img+"<br>";
					}else
					resultat += "<b> "+titre +"</b><br><br><u> Description :</u><br>"+description + "<br><u> Date :</u><br>" +date +"<br><br>"+img+"<br><br>";
					resultat += "<hr>";
				}
				//System.out.println(obj2);
				//System.out.println(obj2.get("title"));
				//System.out.println(obj2.get("id"));
				//System.out.println((obj2.getJSONArray("urls").getJSONObject(0)).get("url"));
				//System.out.println((obj2.getJSONArray("urls").getJSONObject(1)).get("url"));
				
				//return resultat;
			}
			else{
				System.out.println("Le personnage n'a pas été trouvé");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultat = "Erreur sur la requete";
			System.out.println("Erreur sur la requete");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erreur sur le Json");
		}
		
		return resultat;
	}
	public static String VerifieComics(String titre,String annee, String chap, String var) throws UnsupportedEncodingException{
		int a,b,c =0;
		String url="";
		if(titre!=""){
			if(annee.equals("")){
				a=1;
			}else{
				a=0;
			}
			if(chap.equals("")){
				b=4;
			}
			else{
				b=2;
			}
			if(var!=""){
			
			}else{
				
			}
			switch(a+b){
			case 2:System.out.println("2"); 
					url= "/v1/public/comics?title="+URLEncoder.encode(titre, "UTF-8")+"&startYear="+annee+"&issueNumber="+chap;
					break;
			case 3: System.out.println("3");
					url= "/v1/public/comics?title="+URLEncoder.encode(titre, "UTF-8")+"&issueNumber="+chap;
					break;
			case 4: System.out.println("4");
					url= "/v1/public/comics?title="+URLEncoder.encode(titre, "UTF-8")+"&startYear="+annee;
					break;
			case 5: System.out.println("5");
					url= "/v1/public/comics?title="+URLEncoder.encode(titre, "UTF-8");
					break;
			}
		}else url= "";
		return url;
	}
	

}

package useCase.alice;
import requete.Requete;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class PersonnageMarvel {
	//private String nomPerso;
	
	
	public void getInfo(String nomPerso)  {
		
		String categorie = "/v1/public/characters?name=";
		String midUrl = nomPerso;
		String finUrl = "&ts=1&apikey=3560ed6e3f1152119172d09d56535ffe&hash=0d7c00977c36d82b6858c8fe6b3fe53b";
		
		String contenu;
		try {
			contenu = Requete.getInstance().getInfo(categorie, midUrl, finUrl);
			JSONObject obj = new JSONObject(contenu);
			System.out.println(contenu);
			
			obj = obj.getJSONObject("data");
			if(obj.get("total").equals(1)){
				JSONArray itemsList = obj.getJSONArray("results");
				JSONObject obj2 = new JSONObject();
				obj2 = itemsList.getJSONObject(0);
				System.out.println(obj2.get("name"));
				System.out.println(obj2.get("description"));
				//JSONObject descript = new JSONObject();
				//descript = itemsList.getJSONObject(0);
				//System.out.println(descript.get("name"));
				System.out.println("il y "+obj2.getJSONObject("comics").getInt("returned") + " Comics renvoyée");
				System.out.println("il y "+obj2.getJSONObject("comics").getInt("available") + " Comics disponible");
				for(int i = 0 ; i<obj2.getJSONObject("comics").getJSONArray("items").length();i++ ){
					String titre = new String();
					titre = (String) obj2.getJSONObject("comics").getJSONArray("items").getJSONObject(i).get("name");
					System.out.println("Titre du Comic numéro " + i + " " + titre);
				}
				System.out.println("il y "+obj2.getJSONObject("series").getInt("returned") + " séries renvoyées");
				System.out.println("il y "+obj2.getJSONObject("series").getInt("available") + " séries disponible");
				for(int i = 0 ; i<obj2.getJSONObject("series").getJSONArray("items").length();i++ ){
					String titre = new String();
					titre = (String) obj2.getJSONObject("comics").getJSONArray("items").getJSONObject(i).get("name");
					System.out.println("Titre de la série numéro " + i + " " + titre);
				}
				System.out.println("il y "+obj2.getJSONObject("stories").getInt("returned") + " histoires renvoyées");
				System.out.println("il y "+obj2.getJSONObject("stories").getInt("available") + " histoires disponible");
				for(int i = 0 ; i<obj2.getJSONObject("stories").getJSONArray("items").length();i++ ){
					String titre = new String();
					titre = (String) obj2.getJSONObject("stories").getJSONArray("items").getJSONObject(i).get("name");
		
					System.out.println("Titre de l'histoire numéro " + i + " " + titre );
				}
				
			}
			else{
				System.out.println("Le personnage n'a pas été trouvé");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erreur sur la requete");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erreur sur le Json");
		}
		
		
	}
}

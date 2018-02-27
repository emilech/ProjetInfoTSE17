package useCase.alice;
import requete.Requete;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
/**Classe qui correspond a Alice 1 elle doit interroger l'api est afficher les informations d'un personnage **/
public class PersonnageMarvel {
	//private String nomPerso;
	
	/** Fonction qui appelle l'api et qui recherche les informations du Super héro passé en parametre (sera modifié)**/
public String getInfo(String nomPerso)  {
		
	String categorie = "/v1/public/characters?name=";
	String midUrl = nomPerso;
	String finUrl = "&ts=1&apikey=3560ed6e3f1152119172d09d56535ffe&hash=0d7c00977c36d82b6858c8fe6b3fe53b";
		
	String contenu;
	String resultat = new String();
	
	try {
		contenu = Requete.getInstance().getInfo(categorie, midUrl, finUrl);
		JSONObject obj = new JSONObject(contenu);
			
		System.out.println(contenu);
		//resultat+=contenu+"<br>";
					
		obj = obj.getJSONObject("data");
		if(obj.get("total").equals(1)){
			JSONArray itemsList = obj.getJSONArray("results");
			JSONObject obj2 = new JSONObject();
			obj2 = itemsList.getJSONObject(0);
			System.out.println(obj2.get("name"));
			System.out.println(obj2.get("description"));
			resultat += "<html><body>";
			resultat += "<img style = \"float : right;\" src =" + obj2.getJSONObject("thumbnail").getString("path")+ "/portrait_xlarge.jpg ALT='Thumbnails' TITLE='Test' ><br>";
			resultat += "<h1><u>"+ obj2.get("name").toString()+"</u></h1>"+"<br>" +"<b><u><p>Description :</p></u></b> "+"<br><br>"+ obj2.get("description").toString()+"<br><br>";
				
			//JSONObject descript = new JSONObject();
			//descript = itemsList.getJSONObject(0);
			//System.out.println(descript.get("name"));
			System.out.println("il y "+obj2.getJSONObject("comics").getInt("returned") + " Comics renvoyée");
			System.out.println("il y "+obj2.getJSONObject("comics").getInt("available") + " Comics disponible");
			resultat += "<p><b><u> Liens Utiles :</u></b></p><br><br>";
			String url1 = obj2.getJSONArray("urls").getJSONObject(0).getString("url");
			resultat += "<a href  =\"" + url1 +"\"> Wikidata</a><br><br>";
			resultat += "<a href='" + "https://en.wikipedia.org/wiki/"+nomPerso.replaceAll(" ","_")+"'>"+"https://en.wikipedia.org/wiki/"+nomPerso.replaceAll(" ","_") +"</a>.<br><br>";
			
			//resultat += " il y a " + obj2.getJSONObject("comics").getInt("returned") + " Comics renvoyee"+"<br>";
			resultat += "<br><u><p> il y a " + obj2.getJSONObject("comics").getInt("available")+ " Comics disponible</p></u>"+"<br>";
			
			for(int i = 0 ; i<obj2.getJSONObject("comics").getJSONArray("items").length();i++ ){
				String titre = new String();
				titre = (String) obj2.getJSONObject("comics").getJSONArray("items").getJSONObject(i).get("name");
				System.out.println("Titre du Comic numéro " + i + " " + titre);
				resultat += " Titre du Comic numero" + i +" " + titre + "<br>";
			}
			
			//System.out.println("il y "+obj2.getJSONObject("series").getInt("returned") + " séries renvoyées");
			System.out.println("il y "+obj2.getJSONObject("series").getInt("available") + " séries disponible");
			
			//resultat += "il y "+obj2.getJSONObject("series").getInt("returned") + " séries renvoyées"+"<br>";
			resultat += "<br> <u><p>il y "+obj2.getJSONObject("series").getInt("available") + " séries disponible"+"</p></u><br>";
			
			for(int i = 0 ; i<obj2.getJSONObject("series").getJSONArray("items").length();i++ ){
				String titre = new String();
				titre = (String) obj2.getJSONObject("comics").getJSONArray("items").getJSONObject(i).get("name");
				System.out.println("Titre de la série numéro " + i + " " + titre);
				resultat += " Titre de la série numéro " + i + " " + titre+"<br>";
			}
			
			System.out.println("il y "+obj2.getJSONObject("stories").getInt("returned") + " histoires renvoyées");
			System.out.println("il y "+obj2.getJSONObject("stories").getInt("available") + " histoires disponible");
			
			//resultat += " il y "+obj2.getJSONObject("stories").getInt("returned") + " histoires renvoyées"+"<br>";
			resultat += "<br><u><p> il y "+obj2.getJSONObject("stories").getInt("available") + " histoires disponible"+"</u></p><br><br>";
			
			for(int i = 0 ; i<obj2.getJSONObject("stories").getJSONArray("items").length();i++ ){
				String titre = new String();
				titre = (String) obj2.getJSONObject("stories").getJSONArray("items").getJSONObject(i).get("name");
		
				System.out.println("Titre de l'histoire numéro " + i + " " + titre );
				resultat += " Titre de l'histoire numéro " + i + " " + titre+"<br>";
			}
			resultat += "<br><br><br><br>";
			resultat += "</body></html>";
			/*resultat += "<ul class='accordion'> <li class='slide-01'><div><h2>Slide one</h2><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse id lobortis massa. Nunc viverra velit leo, sit amet elementum mi. Fusce posuere nunc a mi tempus malesuada. Curabitur facilisis rhoncus eros eget placerat. Aliquam semper mauris sit amet justo tempor nec lacinia magna molestie. Etiam placerat congue dolor vitae adipiscing. Aliquam ac erat lorem, ut iaculis justo. Etiam mattis dignissim gravida. Aliquam nec justo ante, non semper mi. Nulla consectetur interdum massa, vel porta enim vulputate sed. Maecenas elit quam, egestas eget placerat non, fringilla vel eros. Nam vehicula elementum nulla sed consequat. Phasellus eu erat enim. Praesent at magna non massa dapibus scelerisque in eu lorem.</p></div>"
					+ "</li><li class='slide-02'><div><h2>Slide two</h2><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse id lobortis massa. Nunc viverra velit leo, sit amet elementum mi. Fusce posuere nunc a mi tempus malesuada. Curabitur facilisis rhoncus eros eget placerat. Aliquam semper mauris sit amet justo tempor nec lacinia magna molestie. Etiam placerat congue dolor vitae adipiscing. Aliquam ac erat lorem, ut iaculis justo. Etiam mattis dignissim gravida. Aliquam nec justo ante, non semper mi. Nulla consectetur interdum massa, vel porta enim vulputate sed. Maecenas elit quam, egestas eget placerat non, fringilla vel eros. Nam vehicula elementum nulla sed consequat. Phasellus eu erat enim. Praesent at magna non massa dapibus scelerisque in eu lorem.</p>"
					+ "</div></li> <li class='slide-03'><div><h2>Slide three</h2><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse id lobortis massa. Nunc viverra velit leo, sit amet elementum mi. Fusce posuere nunc a mi tempus malesuada. Curabitur facilisis rhoncus eros eget placerat. Aliquam semper mauris sit amet justo tempor nec lacinia magna molestie. Etiam placerat congue dolor vitae adipiscing. Aliquam ac erat lorem, ut iaculis justo. Etiam mattis dignissim gravida. Aliquam nec justo ante, non semper mi. Nulla consectetur interdum massa, vel porta enim vulputate sed. Maecenas elit quam, egestas eget placerat non, fringilla vel eros. Nam vehicula elementum nulla sed consequat. Phasellus eu erat enim. Praesent at magna non massa dapibus scelerisque in eu lorem.</p></div>"
					+"</li> </ul>";*/
				
		}else{
				System.out.println("Le personnage n'a pas été trouvé");
				resultat = "Le personnage n'a pas été trouvé";
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
	return resultat;
	
	}
}

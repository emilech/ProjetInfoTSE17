package requete;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.zip.GZIPInputStream;
/**Classe qui réalise la requete Get **/
public class Requete extends Authenticator {
	
private static Requete requete = null;
	
	public Requete(){}
	/** Permet de vérifier si un objet Requete est déjà créé pour ne pas en créer trop **/
	public static Requete getInstance(){
		
		if (requete == null){
			requete = new Requete();
		}
		
		return requete;
	}
	/** Fonction inutile a nettoyer **/
	 public PasswordAuthentication getPasswordAuthentication () {
         return new PasswordAuthentication ("user", "password".toCharArray());
     }
	 /** Fonction qui réalise la requete GET **/
    public String getInfo(String categorie, String infoVoulue, String finURL) throws IOException {

        String debutUrl = "https://gateway.marvel.com";
        String source = "";

        String queryParam = URLEncoder.encode(infoVoulue, "UTF-8");
        
        String url = debutUrl + categorie + queryParam + finURL;

        System.out.println(url);
        URL oracle = new URL(url);
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader((yc.getInputStream())));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            source += inputLine;
        }

        in.close();
        return source;
    }

}

package fr.smartberry.parser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Parser {

	//Variable d'instances
	@Autowired
	EtatT etatT;
	
	//Constructeur
	public Parser() {}
	
	//Accesseurs
	public EtatT getEtatT() {
		return etatT;
	}
	
	public void setEtatT(EtatT etatT) {
		this.etatT = etatT;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Parser [etatT=");
		builder.append(etatT);
		builder.append("]");
		return builder.toString();
	}

	// Methodes
	public static JSONObject toJson(EtatT etat) { // Transforme à partir d'un Etat un json type {"idArduino":"45626" "1":1, "2":2, "3":3}

		JSONObject json = new JSONObject();
		json.put("idArduino", etat.getNameArduino());
		json.put("action","writeValues");

		// ajout des paramètres suivant -> "valeur entrée/sortie de l'arduino" : valeur de l'état du dispositif
		for (HashMap.Entry<Integer, Double> entry : etat.getInOutValues().entrySet()) {
			json.put(entry.getKey(), entry.getValue());
		}
		
		return json;
	}

	public EtatT fromJson(String jsonRecu) { // on construit un objet EtatT à partir d'un json reçu en paramètre, et on le retourne
		
		Map<Integer, Double> listeEtatRecu = new HashMap<>();

		JSONParser parser = new JSONParser();
		Object obj = null;
		try {
			obj = parser.parse(jsonRecu);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		JSONObject jsonObject = (JSONObject) obj;

		// remplissage des valeurs dans la variable HashMap instancié auparavant mais si la clé est "idArduino" on met la valeur a l'attribut EtatT->idArduino
		for (Iterator iterator = jsonObject.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			if (key.equals("idArduino")) {
				etatT.setNameArduino((jsonObject.get(key).toString()));
			} else if (!key.equals("action")) {
				listeEtatRecu.put(Integer.parseInt(key), Double.parseDouble(jsonObject.get(key).toString()));
			}
		}

		// on set la valeur de l'attribut EtatT->InOutValues
		etatT.setInOutValues(listeEtatRecu);

		return etatT;
	}
}

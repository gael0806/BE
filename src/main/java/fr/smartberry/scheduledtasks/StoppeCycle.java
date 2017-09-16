package fr.smartberry.scheduledtasks;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import fr.smartberry.parser.Parser;
import fr.smartberry.sockets.SocketUdpThread;
import fr.smartberry.parser.EtatT;

@Component
public class StoppeCycle implements Job {

	// Variables de classe

	// Variables d'instances

	// Constructeur
	public StoppeCycle() {
	}

	// Accesseurs

	// Methodes
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		// Recuperation des params de la tache
		final JobDataMap map = context.getJobDetail().getJobDataMap();
		String idArduino = map.getString("idArduino");
		String ipArduino = map.getString("ipArduino");
		int numPin = map.getInt("pin");

		// Création de l'etatT
		EtatT myEt = new EtatT();
		myEt.setNameArduino(idArduino);
		Map<Integer, Double> inOutValues = new HashMap<>();
		inOutValues.put(numPin, 0d);		//mise a zero
		myEt.setInOutValues(inOutValues);

		// EtatT to json
		JSONObject json = Parser.toJson(myEt);

		System.out.println("envoi du writeValues-----------------" + json.toJSONString() + "-----------------------------------------------------------------------");
		SocketUdpThread.envoyerClient(json, ipArduino, 3600);
	}

}

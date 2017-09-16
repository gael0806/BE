package fr.smartberry.scheduledtasks;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.smartberry.parser.EtatT;
import fr.smartberry.parser.Parser;
import fr.smartberry.sockets.SocketUdpThread;

@Component
@Scope(value = "prototype")
@DisallowConcurrentExecution
public class DemandeDonnees implements Job {

	// Variables de classe

	// Variables d'instances
	@Autowired
	EtatT etatT;
	@Autowired
	Parser parser;

	// Constructeur
	public DemandeDonnees() {
	}

	// Accesseurs

	// Methodes
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		// Recuperation des paramètres de la tache
		final JobDataMap map = context.getJobDetail().getJobDataMap();
		String idArduino = map.getString("idArduino");
		String ipArduino = map.getString("ipArduino");

		System.out.println("envoi du sendValues");
		SocketUdpThread.envoyerClient(idArduino, ipArduino, 3600);

		try { // attente reception message
			Thread.sleep(850);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (!SocketUdpThread.getMessage().equals("reponse non reçue")) {
			etatT = parser.fromJson(SocketUdpThread.getMessage());
			etatT.exportToDispositifs();
		}

	}

}

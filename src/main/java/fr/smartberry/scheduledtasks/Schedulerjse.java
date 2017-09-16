package fr.smartberry.scheduledtasks;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import fr.smartberry.entities.Arduino;
import fr.smartberry.entities.Cycle;
import fr.smartberry.entities.Dispositif;
import fr.smartberry.entities.Maison;
import fr.smartberry.entities.Pin;
import fr.smartberry.entities.Scenario;

@Component
public class Schedulerjse implements Runnable {

	// Variables de classe
	private static volatile Maison home;
	private List<Arduino> arduinos;

	// Variables d'instances
	private final SchedulerFactory factory = new StdSchedulerFactory(); // fabrique du programmateur
	private org.quartz.Scheduler scheduler = null;

	// pour le check toutes les period secondes
	private int period = 5;

	// pour les cycles
	private volatile Map<Arduino, Cycle> myCycles = new HashMap<>();

	// Constructeur
	public Schedulerjse() {
	}

	// Accesseurs
	
	public org.quartz.Scheduler getScheduler() {
		return scheduler;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public static Maison getHome() {
		return home;
	}

	public static void setHome(Maison home) {
		Schedulerjse.home = home;
	}

	public Map<Arduino, Cycle> getMyCycles() {
		return myCycles;
	}

	public void setMyCycles(Map<Arduino, Cycle> myCycles) {
		this.myCycles = myCycles;
	}

	public List<Arduino> getArduinos() {
		return arduinos;
	}

	public void setArduinos(List<Arduino> arduinos) {
		this.arduinos = arduinos;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Scheduler [arduinos=");
		builder.append(arduinos);
		builder.append(", period=");
		builder.append(period);
		builder.append(", myCycles=");
		builder.append(myCycles);
		builder.append("]");
		return builder.toString();
	}

	// Methodes
	@Override
	public void run() {
		try {
			// creation du programmateur
			scheduler = factory.getScheduler();
			scheduler.start();
			
			//for (Arduino arduino : arduinos) {
				// definition des taches SendValues
				final JobDetail sendValues = JobBuilder
			          .newJob(DemandeDonnees.class)
			          //.usingJobData("idArduino", arduino.getName())
			          //.usingJobData("ipArduino", arduino.getIp())
			          .build();
				
				// paramètrage du trigger temporel pour SendValues
			    final Trigger svTrigger = TriggerBuilder
			           .newTrigger()
			           .startNow()
			           .withSchedule(SimpleScheduleBuilder.simpleSchedule()
			                .withIntervalInSeconds(period)
			                .repeatForever())
			           .build();

				scheduler.scheduleJob(sendValues, svTrigger);
			//}
			
			
			// définition des taches pour les cycles
			/*if (myCycles != null) {
				for (HashMap.Entry<Arduino, Cycle> entry : myCycles.entrySet()) {

					// Creation des taches et Params des taches
					final JobDetail mySartingJob = JobBuilder
					          .newJob(DemarreCycle.class)
					          .usingJobData("idArduino", entry.getKey().getName())
					          .usingJobData("ipArduino", entry.getKey().getIp())
					          .usingJobData("pin", getNumPinFromCycle(entry.getValue()))
					          .build();
					
					final JobDetail myEndingJob = JobBuilder
					          .newJob(StoppeCycle.class)
					          .usingJobData("idArduino", entry.getKey().getName())
					          .usingJobData("ipArduino", entry.getKey().getIp())
					          .usingJobData("pin", getNumPinFromCycle(entry.getValue()))
					          .build();

					// Creation du trigger temporel
					Trigger startTrigger = createStartingTrigger(entry.getValue());
					Trigger EndTrigger = createEndingTrigger(entry.getValue());
					
					scheduler.scheduleJob(mySartingJob, startTrigger);
					scheduler.scheduleJob(myEndingJob, EndTrigger);
				}
			}*/
			
			
			System.in.read();
			
			if (scheduler != null) {
				scheduler.shutdown();
			}
		} catch (final SchedulerException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public Trigger createStartingTrigger(Cycle c) {

		LocalTime h = c.getHeure().toLocalTime();
		String minutes = String.valueOf(h.getMinute());
		String heures = String.valueOf(h.getHour());
		String periodicity = String.valueOf(c.getPeriodicity());
		String cronExpression = "0 " + minutes + " " + heures + " "+LocalDate.now().getDayOfMonth()+"/" + periodicity + " * ?"; // ex: "0 30 12 1/5 * ?" tous les 5jrs à 12h30
		//String cronExpression = "1/" + periodicity + " " + minutes + " " + heures + " * * ?"; //en secondes pour les tests "+LocalDate.now().getDayOfMonth()+"
		//String cronExpression = "0/"+periodicity+" * * * * ?";	//toutes les 5s
		
		Trigger cronTrigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(cronExpression).inTimeZone(TimeZone.getTimeZone("Europe/Paris"))).build();

		return cronTrigger;

	}

	public Trigger createEndingTrigger(Cycle c) {

		LocalTime h = c.getHeure().toLocalTime();
		String minutes = String.valueOf(h.getMinute() + c.getDuree());	//ajout de la duree
		String heures = String.valueOf(h.getHour());
		String periodicity = String.valueOf(c.getPeriodicity());
		String cronExpression = "0 " + minutes + " " + heures + " "+LocalDate.now().getDayOfMonth()+"/" + periodicity + " * ?"; // ex: "0 30 12 1/5 * ?" tous les 5jrs à 12h30
				
		Trigger cronTrigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(cronExpression).inTimeZone(TimeZone.getTimeZone("Europe/Paris"))).build();

		return cronTrigger;

	}
	
	public void loadMapArdCy() {
		Map<Arduino, Cycle> myMapArdCy = new HashMap<>();

		for (int i = 0; i < home.getZones().size(); i++) { // Pour chaque zone
			List<Dispositif> mesDispositifs = home.getZones().get(i).getDispositifs(); // liste des dispositifs par zone
			
			for (int j = 0; j < mesDispositifs.size(); j++) { // Pour chaque dispositif
				List<Scenario> mesScenari = mesDispositifs.get(j).getScenari(); // liste des scenari

				if (mesScenari != null) {

					for (int k = 0; k < mesScenari.size(); k++) { // Pour chaque scenario
						if (mesScenari.get(k).isSelectedScenar()) { // si le scenario est selectionné

							for (int l = 0; l < mesScenari.get(k).getCycles().size(); l++) { // Pour chaque cycle

								for (Arduino ard : arduinos) { // pour chaque arduino
									List<Pin> myPins = ard.getMyPins(); // liste des pins

									for (Pin pin : myPins) { // pour chaque pin
										if (pin.getId() == mesDispositifs.get(j).getPin().getId()) { // verif que l'on a la bonne
																								// pin

											myMapArdCy.put(ard, mesScenari.get(k).getCycles().get(l)); // ajout à ma map
										}

									}

								}

							}

						}
					}
				}

			}
		}

		myCycles = myMapArdCy;

	}

	public int getNumPinFromCycle(Cycle c) {
		int NumPin = -1;

		for (int i = 0; i < home.getZones().size(); i++) { // Pour chaque zone
			List<Dispositif> mesDispositifs = home.getZones().get(i).getDispositifs(); // liste des dispositifs par zone

			for (int j = 0; j < mesDispositifs.size(); j++) { // Pour chaque dispositif
				List<Scenario> mesScenari = mesDispositifs.get(j).getScenari(); // liste des scenari

				if (mesScenari != null) {
					for (int k = 0; k < mesScenari.size(); k++) { // Pour chaque scenario

						if (mesScenari.get(k).isSelectedScenar()) { // si le scenario est selectionné

							for (int l = 0; l < mesScenari.get(k).getCycles().size(); l++) { // Pour chaque cycle

								if (c.getId() == mesScenari.get(k).getCycles().get(l).getId()) { // verif que l'on a le
																									// bon cycle

									for (Arduino ard : arduinos) { // pour chaque arduino
										List<Pin> myPins = ard.getMyPins(); // liste des pins

										for (Pin pin : myPins) { // pour chaque pin
											if (pin.getId() == mesDispositifs.get(j).getPin().getId()) { // verif que l'on a
																									// la bonne pin

												NumPin = pin.getNumInOut(); // retour du num de la pin
											}
										}
									}
								}
							}
						}
					}
				}
				
			}
		}
		return NumPin;
	}

	public static void updateTaches() {
		
	}
}

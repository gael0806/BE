package fr.smartberry.scheduledtasks;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;

import fr.smartberry.entities.Arduino;
import fr.smartberry.service.interfaces.IArduinoService;

public class UpdateScenarii implements Job {

	//Variables de classe

	//Variables d'instances
	@Autowired
	Scheduler scheduler;
	@Autowired
	private IArduinoService ardService;
	
	//Constructeur
	public UpdateScenarii() {
		super();
	}

	

	//Accesseurs

	//Methodes
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		List<Arduino> ards = ardService.findAll();
		try {
			System.out.println(scheduler.getCurrentlyExecutingJobs());
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package fr.smartberry.scheduledtasks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import fr.smartberry.entities.Arduino;
import fr.smartberry.service.interfaces.IArduinoService;

@Configuration
public class QuartzConfig {

	// Variable d'instances
	@Autowired
	private IArduinoService ardService;
	@Autowired
	private ApplicationContext applicationContext;
	
	private int period = 15;

	
	//constructeur
	public QuartzConfig() {}
	
	
	
	// Methodes
	/*@Bean
	public List<JobDetail> sendValues() {
		List<Arduino> ards = ardService.findAll();
		List<JobDetail> jobs = new ArrayList<>();
		
		for (Arduino ard : ards) {
			JobDetail job = JobBuilder.newJob().ofType(DemandeDonnees.class).storeDurably()
				.usingJobData("idArduino", ard.getName()).usingJobData("ipArduino", ard.getIp())
				.build();
			jobs.add(job);
		}
		return jobs;
	}*/

	/*@Bean
	public List<Trigger> trigger(List<JobDetail> jobs) {
		List<Trigger> triggers = new ArrayList<>();
		
		for (JobDetail job : jobs) {
			Trigger trigger = TriggerBuilder.newTrigger().forJob(job)
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(period).repeatForever())
				.build();
			triggers.add(trigger);
		}
		
		return triggers;
	}*/

	/*@Bean
	public Scheduler scheduler(List<Trigger> triggers, List<JobDetail> jobs) {
		StdSchedulerFactory factory = new StdSchedulerFactory();
		Scheduler scheduler = null;
		try {
			factory.initialize(new ClassPathResource("quartz.properties").getInputStream());
			scheduler = factory.getScheduler();
			
			for (int i = 0 ; i < jobs.size(); i++ ){
				scheduler.scheduleJob(jobs.get(i), triggers.get(i));
			}
			
			scheduler.start();

		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return scheduler;
	}*/
	@Bean
	public JobDetailFactoryBean procesoMQJob() {
		JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
		jobDetailFactory.setJobClass(DemandeDonnees.class);
		return jobDetailFactory;
	}
	
	@Bean
	public CronTriggerFactoryBean procesoMQTrigger() {
		String cronExpression = "0/"+period+" * * * * ?";	//toutes les period s
		
		CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
		cronTriggerFactoryBean.setJobDetail(procesoMQJob().getObject());
		cronTriggerFactoryBean.setCronExpression(cronExpression);
		return cronTriggerFactoryBean;
	}
	
	@Bean
	public SchedulerFactoryBean quartzScheduler(JobDetailFactoryBean jdfb, CronTriggerFactoryBean ctfb ) {
		SchedulerFactoryBean quartzScheduler = new SchedulerFactoryBean();

		quartzScheduler.setOverwriteExistingJobs(true);

		// custom job factory of spring with DI support for @Autowired!
		AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
		jobFactory.setApplicationContext(applicationContext);
		quartzScheduler.setJobFactory(jobFactory);
		
		quartzScheduler.setQuartzProperties(quartzProperties());

		//affectation des taches
		List<Arduino> ards = ardService.findAll();
		Trigger[] trigs = new Trigger[ards.size()];
		
		for ( int i=0; i< ards.size(); i++) {
			Map<String,String> ardsInfo = new HashMap<>();
			ardsInfo.put("idArduino", ards.get(i).getName());
			ardsInfo.put("ipArduino", ards.get(i).getIp());
			
			jdfb.setJobDataAsMap(ardsInfo);
			ctfb.setJobDetail(jdfb.getObject());
			
			trigs[i] = ctfb.getObject();
		}
		
		quartzScheduler.setTriggers(trigs);
		quartzScheduler.start();
		
		return quartzScheduler;
	}
	
	@Bean
	public Properties quartzProperties() {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
		Properties properties = null;
		try {
			propertiesFactoryBean.afterPropertiesSet();
			properties = propertiesFactoryBean.getObject();

		} catch (IOException e) {
			System.out.println("Cannot load quartz.properties.");
		}

		return properties;
	}

}

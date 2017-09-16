package fr.smartberry.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.smartberry.dao.interfaces.IScenarioDAO;
import fr.smartberry.entities.Cycle;
import fr.smartberry.entities.Scenario;
import fr.smartberry.service.interfaces.ICycleService;
import fr.smartberry.service.interfaces.IScenarioService;

@Service
public class ScenarioService implements IScenarioService{

	//variables d'instances
	@Autowired
	IScenarioDAO scenarioDao;
	@Autowired
	ICycleService cycleService;
	
	//constructeurs
	public ScenarioService() {
	}

	//methodes
		@Override
	public void save(Scenario scenario) {
		scenarioDao.save(scenario);
	}
	
	@Override
	public List<Scenario> findAll() {
		return scenarioDao.findAll();
	}

	@Override
	public Scenario findById(int id) {
		Scenario scenar = new Scenario();
		scenar.setId(id);
		scenar.setName(scenarioDao.findById(id).getName());
		scenar.setDescription(scenarioDao.findById(id).getDescription());
		scenar.setSelectedScenar(scenarioDao.findById(id).isSelectedScenar());
		//Cycles
		List<Cycle> cycles = cycleService.findAll();
		for (Cycle cycle : cycles) {
			if (cycle.getScenario().getId() == id) {
				scenar.addCycle(cycle);
			}
		}
		
		return scenar;
	}

	@Override
	public void update(Scenario scenario) {
		scenarioDao.save(scenario);
	}

	@Override
	public void delete(Scenario scenario) {
		scenarioDao.delete(scenario);
	}

	@Override
	public void saveList(List<Scenario> scenari) {
		scenarioDao.save(scenari);
	}
}

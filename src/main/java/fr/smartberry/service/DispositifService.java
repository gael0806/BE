package fr.smartberry.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.smartberry.dao.interfaces.IDispositifDAO;
import fr.smartberry.entities.Cycle;
import fr.smartberry.entities.Dispositif;
import fr.smartberry.entities.Pin;
import fr.smartberry.entities.Scenario;
import fr.smartberry.service.interfaces.IDispositifService;
import fr.smartberry.service.interfaces.IPinService;
import fr.smartberry.service.interfaces.IScenarioService;
import fr.smartberry.service.interfaces.ITypeService;

@Service
public class DispositifService implements IDispositifService{

	//variables d'instances
	@Autowired
	IDispositifDAO dispositifDao;
	@Autowired
	IPinService pinService;
	@Autowired
	ITypeService typeService;
	@Autowired
	IScenarioService scenarService;
	
	//constructeurs
	public DispositifService() {
	}

	//methodes
		@Override
	public void save(Dispositif dispositif) {
		pinService.save(dispositif.getPin());
		dispositifDao.save(dispositif);
	}
	
	@Override
	public List<Dispositif> findAll() {
		return dispositifDao.findAll();
	}

	@Override
	public Dispositif findById(int id) {
		Dispositif disp = new Dispositif();
		disp.setId(id);
		disp.setName(dispositifDao.findById(id).getName());
		disp.setDescription(dispositifDao.findById(id).getDescription());
		disp.setEtat(dispositifDao.findById(id).getEtat());
		disp.setPin(dispositifDao.findById(id).getPin());
		disp.setType(dispositifDao.findById(id).getType());
		disp.setZone(dispositifDao.findById(id).getZone());
		disp.setScenari(dispositifDao.findById(id).getScenari());
		return disp;
	}

	@Override
	public void update(Dispositif dispositif) {
		dispositifDao.save(dispositif);
	}

	@Override
	public void delete(Dispositif dispositif) {
		System.out.println("------suppression du dispo: "+ dispositif);
		dispositifDao.delete(dispositif);
	}

	@Override
	public void saveList(List<Dispositif> dispositifs) {
		for (Dispositif disp : dispositifs) {
			pinService.save(disp.getPin());
		}
		
		dispositifDao.save(dispositifs);
		
	}

	@Override
	public Dispositif findByPin(Pin pin) {//
		
		Dispositif disp = new Dispositif();
		disp.setId(dispositifDao.findByPin(pin).getId());
		disp.setName(dispositifDao.findByPin(pin).getName());
		disp.setDescription(dispositifDao.findByPin(pin).getDescription());
		disp.setEtat(dispositifDao.findByPin(pin).getEtat());
		disp.setPin(dispositifDao.findByPin(pin).getPin());
		disp.setType(dispositifDao.findByPin(pin).getType());
		disp.setZone(dispositifDao.findByPin(pin).getZone());
		//creation des scenarii
		/*List<Scenario> scenars = scenarService.findAll();
		for (Scenario scenar : scenars) {
			for (Dispositif dispo : scenar.getDispositifs()) {
				if (dispo.getId() == disp.getId()) {
					disp.addScenario(scenar);
				}
			}
		}
		disp.setScenari(dispositifDao.findById(disp.getId()).getScenari());*/
		return disp;
	}
}

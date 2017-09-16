package fr.smartberry.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.smartberry.dao.interfaces.IArduinoDAO;
import fr.smartberry.entities.Arduino;
import fr.smartberry.entities.Pin;
import fr.smartberry.service.interfaces.IArduinoService;
import fr.smartberry.service.interfaces.IPinService;

@Service
public class ArduinoService implements IArduinoService{

	//variables d'instances
	@Autowired
	private IArduinoDAO arduinoDao;
	@Autowired
	private IPinService pinService;
	
	//constructeurs
	public ArduinoService() {
	}

	//methodes
	@Override
	public void save(Arduino arduino) {
		arduinoDao.save(arduino);
	}
	
	@Override
	public List<Arduino> findAll() {
		List<Arduino> ards = new ArrayList<Arduino>();
		List<Arduino> ardDeDao = arduinoDao.findAll();
		for (Arduino arduino : ardDeDao) {
			Arduino a = new Arduino();
			a.setId(arduino.getId());
			a.setName(arduino.getName());
			a.setIp(arduino.getIp());
			//Pins
			List<Pin> pins = pinService.findAll();
			for (Pin pin : pins) {
				Pin p = new Pin();
				p.setId(pin.getId());
				p.setNumInOut(pin.getNumInOut());
				p.setDispositif(pin.getDispositif());
				a.addPin(p);
			}
			ards.add(a);
		}
		return ards;
	}

	@Override
	public Arduino findById(int id) {
		return arduinoDao.findById(id);
	}

	@Override
	public void update(Arduino arduino) {
		arduinoDao.save(arduino);
	}

	@Override
	public void delete(Arduino arduino) {
		arduinoDao.delete(arduino);
	}

	@Override
	public void saveList(List<Arduino> arduinos) {
		arduinoDao.save(arduinos);
		
	}
	
	@Override
	public Arduino findByName(String name) {
		return arduinoDao.findByName(name);
	}
}

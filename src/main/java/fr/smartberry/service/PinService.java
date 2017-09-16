package fr.smartberry.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.smartberry.dao.interfaces.IPinDAO;
import fr.smartberry.entities.Arduino;
import fr.smartberry.entities.Pin;
import fr.smartberry.service.interfaces.IArduinoService;
import fr.smartberry.service.interfaces.IPinService;

@Service
public class PinService implements IPinService {

	// variables d'instances
	@Autowired
	IPinDAO pinDao;
	@Autowired
	IArduinoService arduinoService;

	// constructeurs
	public PinService() {
	}

	// methodes
	@Override
	public void save(Pin pin) {
		arduinoService.save(pin.getArduino());
		pinDao.save(pin);
	}

	@Override
	public List<Pin> findAll() {
		return pinDao.findAll();
	}

	@Override
	public Pin findById(int id) {
		return pinDao.findById(id);
	}

	@Override
	public void update(Pin pin) {
		pinDao.save(pin);
	}

	@Override
	public void delete(Pin pin) {
		pinDao.delete(pin);
	}

	@Override
	public void saveList(List<Pin> pins) {
		for (Pin pin : pins) {
			arduinoService.save(pin.getArduino());
		}
		pinDao.save(pins);

	}

	@Override
	public Pin findByArduino(Arduino a) {
		return pinDao.findByArduino(a);
	}

	
	@Override
	public Pin findByArduinoAndNum_in_out(Arduino a, int num_in_out) {
		return pinDao.findByArduinoAndNumInOut(a, num_in_out);
	}

	
}

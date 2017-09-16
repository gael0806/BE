package fr.smartberry.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.smartberry.dao.interfaces.ICycleDAO;
import fr.smartberry.entities.Cycle;
import fr.smartberry.service.interfaces.ICycleService;

@Service
public class CycleService implements ICycleService{

	//variables d'instances
	@Autowired
	ICycleDAO cycleDao;
	
	//constructeurs
	public CycleService() {
	}

	//methodes
		@Override
	public void save(Cycle cycle) {
		cycleDao.save(cycle);
	}
	
	@Override
	public List<Cycle> findAll() {
		return cycleDao.findAll();
	}

	@Override
	public Cycle findById(int id) {
		return cycleDao.findById(id);
	}

	@Override
	public void update(Cycle cycle) {
		cycleDao.save(cycle);
	}

	@Override
	public void delete(Cycle cycle) {
		cycleDao.delete(cycle);
	}

	@Override
	public void saveList(List<Cycle> cycles) {
		cycleDao.save(cycles);
		
	}
}

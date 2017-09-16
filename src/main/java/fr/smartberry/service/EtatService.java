package fr.smartberry.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.smartberry.dao.interfaces.IEtatDAO;
import fr.smartberry.entities.Etat;
import fr.smartberry.service.interfaces.IEtatService;

@Service
public class EtatService implements IEtatService{

	//variables d'instances
	@Autowired
	IEtatDAO etatDao;
	
	//constructeurs
	public EtatService() {
	}

	//methodes
		@Override
	public void save(Etat etat) {
		etatDao.save(etat);
	}
	
	@Override
	public List<Etat> findAll() {
		return etatDao.findAll();
	}

	@Override
	public Etat findById(int id) {
		return etatDao.findById(id);
	}

	@Override
	public void update(Etat etat) {
		etatDao.save(etat);
	}

	@Override
	public void delete(Etat etat) {
		etatDao.delete(etat);
	}

	@Override
	public void saveList(List<Etat> etats) {
		etatDao.save(etats);
		
	}
}

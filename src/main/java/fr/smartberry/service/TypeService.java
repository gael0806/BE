package fr.smartberry.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.smartberry.dao.interfaces.ITypeDAO;
import fr.smartberry.entities.Type;
import fr.smartberry.service.interfaces.ITypeService;

@Service
public class TypeService implements ITypeService{

	//variables d'instances
	@Autowired
	ITypeDAO typeDao;
	
	//constructeurs
	public TypeService() {
	}

	//methodes
		@Override
	public void save(Type type) {
		typeDao.save(type);
	}
	
	@Override
	public List<Type> findAll() {
		return typeDao.findAll();
	}

	@Override
	public Type findById(int id) {
		return typeDao.findById(id);
	}

	@Override
	public void update(Type type) {
		typeDao.save(type);
	}

	@Override
	public void delete(Type type) {
		typeDao.delete(type);
	}

	@Override
	public void saveList(List<Type> types) {
		typeDao.save(types);
		
	}
}

package fr.smartberry.dao.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.smartberry.entities.Arduino;

public interface IArduinoDAO extends JpaRepository<Arduino, Integer> {

	public Arduino save(Arduino arduino);	
	
	public Arduino findById(Integer id);
	
	public Arduino findByName(String name);
	
	public List<Arduino> findAll();
	
	public void delete(Arduino arduino);
}
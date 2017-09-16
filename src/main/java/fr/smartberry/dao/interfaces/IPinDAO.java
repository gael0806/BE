package fr.smartberry.dao.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.smartberry.entities.Arduino;
import fr.smartberry.entities.Pin;

public interface IPinDAO extends JpaRepository<Pin, Integer> {

	public Pin save(Pin pin);	
	
	public Pin findById(Integer id);
	
	public List<Pin> findAll();
	
	public void delete(Pin pin);
	
	public Pin findByArduino(Arduino a);
	
	public Pin findByArduinoAndNumInOut(Arduino a, int num_in_out);
}
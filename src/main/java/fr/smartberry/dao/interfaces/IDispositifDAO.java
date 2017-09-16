package fr.smartberry.dao.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.smartberry.entities.Dispositif;
import fr.smartberry.entities.Pin;

public interface IDispositifDAO extends JpaRepository<Dispositif, Integer> {

	public Dispositif findById(Integer id);
	
	public Dispositif findByPin(Pin pin);
}
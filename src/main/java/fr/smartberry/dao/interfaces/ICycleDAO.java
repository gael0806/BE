package fr.smartberry.dao.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.smartberry.entities.Cycle;

public interface ICycleDAO extends JpaRepository<Cycle, Integer> {

	public Cycle save(Cycle cycle);	
	
	public Cycle findById(Integer id);
	
	public List<Cycle> findAll();
	
	public void delete(Cycle cycle);
}
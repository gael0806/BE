package fr.smartberry.dao.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.smartberry.entities.Scenario;

public interface IScenarioDAO extends JpaRepository<Scenario, Integer> {

	public Scenario save(Scenario scenario);	
	
	public Scenario findById(Integer id);
	
	public List<Scenario> findAll();
	
	public void delete(Scenario scenario);
}
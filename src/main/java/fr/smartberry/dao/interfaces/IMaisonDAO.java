package fr.smartberry.dao.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.smartberry.entities.Maison;

public interface IMaisonDAO extends JpaRepository<Maison, Integer> {

	public Maison save(Maison maison);	
	
	public Maison findById(Integer id);
	
	public List<Maison> findAll();
	
}
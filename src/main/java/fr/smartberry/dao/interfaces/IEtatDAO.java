package fr.smartberry.dao.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.smartberry.entities.Etat;

public interface IEtatDAO extends JpaRepository<Etat, Integer> {

	public Etat save(Etat etat);	
	
	public Etat findById(Integer id);
	
	public List<Etat> findAll();
	
	public void delete(Etat etat);
}
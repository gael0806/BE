package fr.smartberry.dao.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.smartberry.entities.Type;

public interface ITypeDAO extends JpaRepository<Type, Integer> {

	public Type save(Type type);	
	
	public Type findById(Integer id);
	
	public List<Type> findAll();
	
	public void delete(Type type);
}
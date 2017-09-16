package fr.smartberry.service.interfaces;

import java.util.List;

public interface IAbstractService<T> {

	//methodes
	public void save(T entity);

	public List<T> findAll();

	public T findById(int id);

	public void update(T entity);

	public void delete(T entity);

	void saveList(List<T> entities);
}
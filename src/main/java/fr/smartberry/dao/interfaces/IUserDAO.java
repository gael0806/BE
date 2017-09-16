package fr.smartberry.dao.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.smartberry.entities.User;

public interface IUserDAO extends JpaRepository<User, Integer> {

	public User save(User user);	
	
	public User findById(Integer id);
	
	public List<User> findAll();
	
	public void delete(User user);
	
	public User findByLoginAndPassword(String login, String password);
}
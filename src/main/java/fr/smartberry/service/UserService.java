package fr.smartberry.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.smartberry.dao.interfaces.IUserDAO;
import fr.smartberry.entities.Maison;
import fr.smartberry.entities.User;
import fr.smartberry.service.interfaces.IMaisonService;
import fr.smartberry.service.interfaces.IUserService;

@Service
public class UserService implements IUserService{

	//variables d'instances
	@Autowired
	IUserDAO userDao;
	@Autowired
	IMaisonService maisonService;
	
	//constructeurs
	public UserService() {
	}

	//methodes
	@Override
	public void save(User user) {
		Maison home = maisonService.findById(1);
		if (user.getMaison() == null) {
			user.setMaison(home);
		}
		userDao.save(user);
		System.out.println("saved user: "+ user);
	}
	
	@Override
	public List<User> findAll() {
		
		return userDao.findAll();
	}

	@Override
	public User findById(int id) {
		User user = new User();
		user.setId(id);
		user.setLogin(userDao.findById(id).getLogin());
		user.setName(userDao.findById(id).getName());
		user.setPassword(userDao.findById(id).getPassword());
		user.setAdmin(userDao.findById(id).getAdmin());
		return user;
	}

	@Override
	public void update(User user) {
		Maison home = maisonService.findById(1);
		if (user.getMaison() == null) {
			user.setMaison(home);
		}
		userDao.save(user);
	}

	@Override
	public void delete(User user) {
		Maison home = maisonService.findById(1);
		if (user.getMaison() == null) {
			user.setMaison(home);
		}
		userDao.delete(user);
	}

	@Override
	public void saveList(List<User> users) {
		for (User user : users) {
			if (user.getMaison() == null) {
				Maison home = maisonService.findById(1);
				user.setMaison(home);
			}
		}
		userDao.save(users);
		
	}

	@Override
	public User findByLoginAndPassword(String login, String password) {
		return userDao.findByLoginAndPassword(login, password);
	}
	
}

package fr.smartberry.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.smartberry.dao.interfaces.IMaisonDAO;
import fr.smartberry.entities.Maison;
import fr.smartberry.service.interfaces.IMaisonService;
import fr.smartberry.service.interfaces.IUserService;

@Service
public class MaisonService implements IMaisonService{

	//variables d'instances
	@Autowired
	IMaisonDAO maisonDao;
	@Autowired
	IUserService userService;
	
	//constructeurs
	public MaisonService() {
	}

	//methodes
		@Override
	public void save(Maison maison) {
		maisonDao.save(maison);
	}
	
	@Override
	public List<Maison> findAll() {
		return maisonDao.findAll();
	}

	@Override
	public Maison findById(int id) {
			Maison home = maisonDao.findById(id);
			
			/*List<User> users = userService.findAll();
			if (users!= null) {
				for (User user : users) {
					if (user.getMaison().getId() == home.getId()) {
						List<User> homeUsers = new ArrayList<>();
						homeUsers.add(user);
						home.setUsers(homeUsers);
					}
				}
				
			}*/
		return home;
	}

	@Override
	public void update(Maison maison) {
		maisonDao.save(maison);
	}

	@Override
	public void delete(Maison maison) {
		maisonDao.delete(maison);
	}

	@Override
	public void saveList(List<Maison> maisons) {
		maisonDao.save(maisons);
		
	}
}

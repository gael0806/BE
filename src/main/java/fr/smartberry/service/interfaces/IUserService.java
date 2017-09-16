package fr.smartberry.service.interfaces;

import fr.smartberry.entities.User;

public interface IUserService extends IAbstractService<User>{

	public User findByLoginAndPassword(String login, String password);
}

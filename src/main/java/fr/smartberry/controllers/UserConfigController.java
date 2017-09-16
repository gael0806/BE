package fr.smartberry.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import fr.smartberry.entities.User;
import fr.smartberry.service.interfaces.IMaisonService;
import fr.smartberry.service.interfaces.IUserService;


@RestController
@RequestMapping(value = "/api")
public class UserConfigController {
	@Autowired
	IUserService userService;
	@Autowired
	IMaisonService maisonService;

	@RequestMapping(value = "/configuration/utilisateur", method = RequestMethod.GET, produces = "application/json")
	public List<User> userReq() {
		System.out.println("Réception de la requete User Config");
		List<User> myUsers = userService.findAll();

		return myUsers;
	}

	@ResponseStatus(value = HttpStatus.CREATED)
	@RequestMapping(value = "/configuration/utilisateur/create", method = RequestMethod.POST, produces = "application/json")
	public void createUser(@RequestBody User user) {
		
		System.out.println("affichage de la maison: "+user);
		if(user.getId()== null) {
			userService.save(user);
			System.out.println("Enregistrement de l'utilisateur: " + user + " sur la base de données");
		}else {
			userService.update(user);
			System.out.println("Mise à jour de l'utilisateur: " + user + " sur la base de données");
		}
	}
	
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	@RequestMapping(value = "/configuration/utilisateur/delete", method = RequestMethod.POST, produces = "application/json")
	public void deleteUser(@RequestBody User user) {
		
			System.out.println("affichage du user: "+user);
			userService.delete(user);
	}


}

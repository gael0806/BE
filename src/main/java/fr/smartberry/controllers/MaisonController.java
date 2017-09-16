package fr.smartberry.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.smartberry.entities.User;
import fr.smartberry.service.interfaces.IMaisonService;
import fr.smartberry.service.interfaces.IUserService;

@RestController
@RequestMapping(value = "/api")
public class MaisonController {
	@Autowired
	IUserService userService;
	@Autowired
	IMaisonService maisonService;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public String greeting() {
		return "{ \"test\":\"test\"}";
	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public User login(@RequestBody Map<String, String> loginMdp) {

		
		// controle du login
		User user = userService.findByLoginAndPassword(loginMdp.get("login"), loginMdp.get("password"));
		System.out.println("Controller Post");

		if (user != null) {
			System.out.println("renvoiuser" + user.getLogin() + user.getPassword());
			return user;
		} else {
			return null;
		}
	}
}

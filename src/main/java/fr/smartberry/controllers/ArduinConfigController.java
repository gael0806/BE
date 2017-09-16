package fr.smartberry.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import fr.smartberry.entities.Arduino;
import fr.smartberry.service.interfaces.IArduinoService;

@RestController
@RequestMapping(value = "/api")
public class ArduinConfigController {
	
	@Autowired
	IArduinoService arduinoService;
	
	@RequestMapping(value = "/configuration/materiel", method = RequestMethod.GET, produces = "application/json")
	public List<Arduino> zoneReq() {
		System.out.println("Réception de la requet materiel Config");

		List<Arduino> myArduinos= arduinoService.findAll(); 
		return myArduinos;
		}
		
	@ResponseStatus(value = HttpStatus.CREATED)
	@RequestMapping(value = "/configuration/materiel/create", method = RequestMethod.POST, produces = "application/json")
	public void createUser(@RequestBody Arduino arduino) {
		
		if(arduinoService.findById(arduino.getId()) == null) {
			arduinoService.save(arduino);
			System.out.println("Enregistrement de l'arduino: " + arduino + " dans la base de données");
		}else {
			arduinoService.update(arduino);
			System.out.println("Mise à jour de l'arduino: " + arduino + " dans la base de données");
		}
		}
	
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	@RequestMapping(value = "/configuration/materiel/delete", method = RequestMethod.POST, produces = "application/json")
	public void deleteUser(@RequestBody Arduino arduino) {
		
			System.out.println("Supprssion de l'arduino: "+ arduino);
			arduinoService.delete(arduino);
	}
}

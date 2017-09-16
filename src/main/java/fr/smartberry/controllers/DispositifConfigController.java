package fr.smartberry.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.smartberry.entities.Arduino;
import fr.smartberry.entities.Dispositif;
import fr.smartberry.entities.Pin;
import fr.smartberry.service.interfaces.IArduinoService;
import fr.smartberry.service.interfaces.IDispositifService;
import fr.smartberry.service.interfaces.IPinService;
import fr.smartberry.service.interfaces.ITypeService;
import fr.smartberry.service.interfaces.IZoneService;

@RestController
@RequestMapping(value = "/api")
public class DispositifConfigController {
	@Autowired
	IDispositifService dispositifService;
	@Autowired
	IZoneService zoneService;
	@Autowired
	ITypeService typeService;
	@Autowired
	IPinService pinService;
	@Autowired
	IArduinoService ardService;

	@RequestMapping(value = "/configuration/zone/dispositif/{zoneid}", method = RequestMethod.GET, produces = "application/json")
	public List<Dispositif> dispositifReq(@PathVariable int zoneid) {

		System.out.println("Réception de la requet dispositif Config");
		System.out.println("Id reçu: " + zoneid);
		if (zoneid != 0) {
			return zoneService.findById(zoneid).getDispositifs();
		} else {
			return new ArrayList<Dispositif>();
		}
		
	}

	@ResponseStatus(value = HttpStatus.CREATED)
	@RequestMapping(value = "/configuration/zone/dispositif/create/{zoneid}", method = RequestMethod.POST, produces = "application/json")
		public void createDispositifs(@RequestBody List<Map<String, String>> dispoData, @PathVariable int zoneid) {
		System.out.println("--------// Liste de dispositifs reçus //-------------");
		System.out.println("------- zone id: "+zoneid);

		 System.out.println("-------- donnée de dispositifs reçus " + dispoData);

		for (Map<String,String> maps : dispoData) {
			
			Dispositif disp = new Dispositif();
			if (maps.get("id").equals("0")) {
				disp.setZone(zoneService.findById(zoneid));
				System.out.println("Nouveau dispo:  " + disp);
				disp.setName(maps.get("name"));
				disp.setDescription(maps.get("description"));
				disp.setEtat(Double.parseDouble(maps.get("etat")));
				disp.setType(typeService.findById(Integer.parseInt(maps.get("type"))));
				Arduino ard = ardService.findById(Integer.parseInt(maps.get("arduinoNbr")));
				System.out.println("disp" + disp);
				System.out.println("ard= " + ard);
				Pin pin = new Pin();
				pin.setNumInOut(Integer.parseInt(maps.get("pin")));
				pin.setArduino(ard);
				pinService.save(pin);
				disp.setPin(pin);
				dispositifService.save(disp);
				
				System.out.println("---------- Enregistrement des dispos: "+ disp);
				
			} else {
				disp = dispositifService.findById(Integer.parseInt(maps.get("id")));
				disp.setZone(zoneService.findById(zoneid));
				System.out.println("Nouveau dispo:  " + disp);
				disp.setName(maps.get("name"));
				disp.setDescription(maps.get("description"));
				disp.setEtat(Double.parseDouble(maps.get("etat")));
				disp.setType(typeService.findById(Integer.parseInt(maps.get("type"))));
				Arduino ard = ardService.findById(Integer.parseInt(maps.get("arduinoNbr")));
				System.out.println("disp" + disp);
				System.out.println("ard= " + ard);
				Pin pin = new Pin();
				pin.setNumInOut(Integer.parseInt(maps.get("pin")));
				pin.setArduino(ard);
				pinService.save(pin);
				dispositifService.update(disp);
				
				System.out.println("------------- Mise à jour des dispos: "+ disp);
			}
		}
	}

	@ResponseStatus(value = HttpStatus.ACCEPTED)
	@RequestMapping(value = "/configuration/zone/dispositif/delete", method = RequestMethod.POST, produces = "application/json")
	public void deleteUser(@RequestBody Map<String, String> dispositif) {
		
			System.out.println("affichage du dispositif: "+ dispositif);
			
			Dispositif disp = new Dispositif();
			disp = dispositifService.findById(Integer.parseInt(dispositif.get("id")));
			dispositifService.delete(disp);
	}
	
}

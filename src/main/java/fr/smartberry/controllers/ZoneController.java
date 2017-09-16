package fr.smartberry.controllers;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import fr.smartberry.entities.Zone;
import fr.smartberry.service.interfaces.IZoneService;

@RestController
@RequestMapping(value = "/api")
public class ZoneController {
	
	@Autowired
	IZoneService zoneService;
	
	
	@RequestMapping(value = "/configuration/zone", method = RequestMethod.GET, produces = "application/json")
	public List<Zone> zoneReq() {
		
		List<Zone> myZones = zoneService.findAll();
		return myZones;
	}
		
	@ResponseStatus(value = HttpStatus.CREATED)
	@RequestMapping(value = "/configuration/zone/create", method = RequestMethod.POST, produces = "application/json")
	public void createUser(@RequestBody Zone zone) {

		 //System.out.println(zone.toString());
		
	//	zoneService.save(zone);
		
		if (zone.getId() == 0) {
			zoneService.save(zone);
			System.out.println(" ---------  Enregistrement de la zone: " + zone + " dans la base de données");
		}else {
			zoneService.update(zone);
			System.out.println(" ----------- Mise à jour de la zone: " + zone + " dans la base de données");
		}

	}
	
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	@RequestMapping(value = "/configuration/zone/delete", method = RequestMethod.POST, produces = "application/json")
	public void deleteZone(@RequestBody Zone zone) {
		
			if(zone.getId()!=0) {
			Zone zoneToDelete = zoneService.findById(zone.getId());
			System.out.println("------------ Suppression de la zone: "+zoneToDelete);
			zoneService.delete(zoneToDelete);}
	}
	
	

	
	

}

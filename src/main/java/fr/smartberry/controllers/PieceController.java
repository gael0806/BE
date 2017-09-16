package fr.smartberry.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.smartberry.entities.Zone;
import fr.smartberry.service.interfaces.IZoneService;

@RestController
@RequestMapping(value = "/api")
public class PieceController {

	@Autowired
	IZoneService zoneService;

	@RequestMapping(value = "/accueil", method = RequestMethod.GET, produces = "application/json")
	public List<Zone> greeting() {
		List<Zone> zones = new ArrayList<>();
		List<Zone> pieces = new ArrayList<>();

		zones = zoneService.findAll();

		for (Zone zone : zones) {
			if (!zone.isGarden()) {
				pieces.add(zone);
			}
		}
		return pieces;
	}

}

package fr.smartberry.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.smartberry.sockets.SocketUdpThread;
import fr.smartberry.entities.Scenario;
import fr.smartberry.entities.Zone;
import fr.smartberry.parser.EtatT;
import fr.smartberry.parser.Parser;
import fr.smartberry.service.interfaces.IArduinoService;
import fr.smartberry.service.interfaces.IDispositifService;
import fr.smartberry.service.interfaces.IMaisonService;
import fr.smartberry.service.interfaces.IPinService;
import fr.smartberry.service.interfaces.IScenarioService;
import fr.smartberry.service.interfaces.ITypeService;
import fr.smartberry.service.interfaces.IUserService;
import fr.smartberry.service.interfaces.IZoneService;

@RestController
@RequestMapping(value = "/api")
public class GardenController {

	// variables d'instances
	@Autowired
	IZoneService zoneService;
	@Autowired
	IMaisonService maisonService;
	@Autowired
	ITypeService typeService;
	@Autowired
	IDispositifService dispService;
	@Autowired
	IUserService userService;
	@Autowired
	IPinService pinService;
	@Autowired
	IArduinoService arduinoService;
	@Autowired
	IScenarioService scenarioService;
	
	// Methodes
	@RequestMapping(value = "/jardin", method = RequestMethod.GET)
	public List<Zone> GardenGet() {
		System.out.println("gardenGet");
		
		List<Zone> allZones = zoneService.findAll();
		List<Zone> myZones = new ArrayList<>();
		for (Zone zone : allZones) {
			if (zone.isGarden()) {
				myZones.add(zone);
			}
		}

		return myZones;
	}

	@RequestMapping(value = "/jardin", method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public String GardenPost(@RequestBody Map<String, String> zoneData) {
		System.out.println("GardenPost()");
		String rep = "{\"reponse\":\"message reçu\"}";

		// affichage message reçu
		StringBuilder builder = new StringBuilder();
		builder.append("id= ").append(zoneData.get("id")).append(" ,action=").append(zoneData.get("action"))
				.append(" ,scenario=").append(zoneData.get("scenario")).append(" ,selected=")
				.append(zoneData.get("selected"));
		System.out.println(builder.toString());

		// preparation de l'envoi à l'arduino
		// ordre de commande manuelle
		EtatT eT = new EtatT();
		int idDisp = Integer.parseInt(zoneData.get("id"));
		eT.setNameArduino(dispService.findById(idDisp).getPin().getArduino().getName());
		eT.setAction("writeValues");
		Map<Integer, Double> in_out_values = new HashMap<Integer, Double>();
		in_out_values.put(dispService.findById(idDisp).getPin().getNumInOut(),
				Double.parseDouble(zoneData.get("action")));
		eT.setInOutValues(in_out_values);

		// Envoi du message
		JSONObject json = Parser.toJson(eT);
		SocketUdpThread.envoyerClient(json, dispService.findById(idDisp).getPin().getArduino().getIp(), 3600);

		// scenario
		if (zoneData.get("scenario") != null) {
			Scenario scenar = scenarioService.findById(Integer.parseInt(zoneData.get("scenario")));
			System.out.println("selected= " + zoneData.get("selected"));
			System.out.println(scenar);
		}

		return rep;
	}
}

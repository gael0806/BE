package fr.smartberry.controllers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.smartberry.sockets.SocketUdpThread;
import fr.smartberry.entities.Zone;
import fr.smartberry.service.interfaces.IZoneService;

@RestController
@RequestMapping(value = "/api")
public class DispositifController {

	@Autowired
	IZoneService zoneService;

	private Zone zone;

	@RequestMapping(value = "/piece/{id}", method = RequestMethod.GET, produces = "application/json")
	public Zone greeting(@PathVariable("id") Integer id) {
		zone = new Zone();
		zone = zoneService.findById(id);
		return zone;

	}

	@RequestMapping(value = "/piece", method = RequestMethod.POST)
	public void test(@RequestBody String s) {
		System.out.println(s);

		JSONParser parser = new JSONParser();
		JSONObject j = null;
		try {
			j = (JSONObject) parser.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		JSONObject jso = new JSONObject();
		jso.put("idArduino", ((JSONObject) ((JSONObject) j.get("pin")).get("arduino")).get("name"));
		jso.put("action", "writeValues");
		jso.put(((JSONObject) j.get("pin")).get("numInOut"), j.get("etat"));
		System.out.println("jso" + jso);

		// envoi du message
		SocketUdpThread.envoyerClient(jso, (String) ((JSONObject) ((JSONObject) j.get("pin")).get("arduino")).get("ip"),
				3600);

	}

}

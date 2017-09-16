package fr.smartberry.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.smartberry.dao.interfaces.IZoneDAO;
import fr.smartberry.entities.Arduino;
import fr.smartberry.entities.Dispositif;
import fr.smartberry.entities.Pin;
import fr.smartberry.entities.Scenario;
import fr.smartberry.entities.Type;
import fr.smartberry.entities.Zone;
import fr.smartberry.service.interfaces.IDispositifService;
import fr.smartberry.service.interfaces.IMaisonService;
import fr.smartberry.service.interfaces.IPinService;
import fr.smartberry.service.interfaces.IZoneService;

@Service
public class ZoneService implements IZoneService {

	// variables d'instances
	@Autowired
	IZoneDAO zoneDao;
	@Autowired
	IDispositifService dispService;
	@Autowired
	IMaisonService maisonService;
	@Autowired
	IPinService pinService;

	// constructeurs
	public ZoneService() {
	}

	// methodes
	@Override
	public void save(Zone zone) {
		if (zone.getMaison() == null) {
			zone.setMaison(maisonService.findById(1));
		}
		if (zone.getDispositifs() == null) {
			zoneDao.save(zone);
		} else {
			zoneDao.save(zone);
			dispService.saveList(zone.getDispositifs());
		}
	}

	@Override
	public List<Zone> findAll() {
		List<Zone> zones = new ArrayList<>();
		List<Zone> zonesDeDao = zoneDao.findAll();

		for (Zone zone : zonesDeDao) {
			Zone z = new Zone();
			z = findById(zone.getId());
			zones.add(z);
		}
		return zones;
	}

	@Override
	public void update(Zone zone) {
		if (zone.getMaison() == null) {
			zone.setMaison(maisonService.findById(1));
		}
		zoneDao.save(zone);

	}

	@Override
	public void delete(Zone zone) {
		if (zone.getDispositifs() != null) {
			System.out.println("list des dispositifs à supprimer: "+zone.getDispositifs());
			for (Dispositif disp : zone.getDispositifs()) {
				System.out.println("Dispositif à supprimer: "+disp);
				dispService.delete(disp);
			}
		}
		zoneDao.delete(zone);

	}

	@Override
	public Zone findById(int id) {
		Zone zone = new Zone();
		zone.setId(id);
		zone.setName(zoneDao.findById(id).getName());
		zone.setDescription(zoneDao.findById(id).getDescription());
		zone.setGarden(zoneDao.findById(id).isGarden());
		// dispositifs
		List<Dispositif> disps = new ArrayList<Dispositif>();
		disps = dispService.findAll();
		for (Dispositif dispositif : disps) {
			if (dispositif.getZone().getId() == id) {
				Dispositif d = new Dispositif();
				d.setId(dispositif.getId());
				d.setName(dispositif.getName());
				d.setDescription(dispositif.getDescription());
				d.setEtat(dispositif.getEtat());
				// reconstruction du type
				Type t = new Type();
				t.setId(dispositif.getType().getId());
				t.setName(dispositif.getType().getName());
				t.setLogo_activated(dispositif.getType().getLogo_activated());
				t.setLogo_disactivated(dispositif.getType().getLogo_disactivated());
				d.setType(t);
				// Construction de l'arduino
				Arduino a = new Arduino();
				a.setId(pinService.findById(dispositif.getPin().getId()).getArduino().getId());
				a.setName(pinService.findById(dispositif.getPin().getId()).getArduino().getName());
				a.setIp(pinService.findById(dispositif.getPin().getId()).getArduino().getIp());
				// Construction de la pin
				Pin p = new Pin();
				p.setId(dispositif.getPin().getId());
				p.setNumInOut(dispositif.getPin().getNumInOut());
				p.setArduino(a);
				d.setPin(p);
				//construction de scenari
				for (Scenario scenar : dispositif.getScenari()) {
				Scenario s = new Scenario();
				s.setId(scenar.getId());
				s.setName(scenar.getName());
				s.setDescription(scenar.getDescription());
				s.setSelectedScenar(scenar.isSelectedScenar());
				s.setCycles(scenar.getCycles());
				d.addScenario(s);
				}
				zone.addDisp(d);
			}
		}
		zone.setMaison(zoneDao.findById(id).getMaison());

		return zone;
	}

	@Override
	public void saveList(List<Zone> zones) {
		zoneDao.save(zones);

	}

}

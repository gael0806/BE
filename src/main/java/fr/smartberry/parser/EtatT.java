package fr.smartberry.parser;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.smartberry.entities.Pin;
import fr.smartberry.service.interfaces.IArduinoService;
import fr.smartberry.service.interfaces.IDispositifService;
import fr.smartberry.entities.Arduino;
import fr.smartberry.entities.Dispositif;
import fr.smartberry.entities.Maison;

@Component
@Scope(value="prototype")
public class EtatT {

	// variables d'instance
	@Autowired
	IArduinoService arduinoService;
	@Autowired
	IDispositifService dispService;
	
	private String nameArduino;
	private String action;
	private Map<Integer, Double> inOutValues; // inOutValues <Numéro Pin arduino,Etat du dispositif>
	
	
	// constructeurs
	public EtatT() {}

	// accessseurs
	public String getNameArduino() {
		return nameArduino;
	}

	public void setNameArduino(String nameArduino) {
		this.nameArduino = nameArduino;
	}

	public Map<Integer, Double> getInOutValues() {
		return inOutValues;
	}

	public void setInOutValues(Map<Integer, Double> inOutValues) {
		this.inOutValues = inOutValues;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EtatT [nameArduino=");
		builder.append(nameArduino);
		builder.append(", action=");
		builder.append(action);
		builder.append(", inOutValues=");
		builder.append(inOutValues);
		builder.append("]");
		return builder.toString();
	}

	// methodes
	public void importFromDispositifs(Maison myHouse) {	//à recoder

/*		inOutValues = new HashMap<Integer, Double>();

		for (int i = 0; i < myHouse.getZones().size(); i++) { // Pour chaque zone
			List<Dispositif> mesDispositifs = myHouse.getZones().get(i).getDispositifs(); // liste des dispositifs par
																							// zone

			for (int j = 0; j < mesDispositifs.size(); j++) { // Pour chaque dispositif

				Dispositif monDispositif = mesDispositifs.get(j);
				int maPinID = monDispositif.getPin().getId(); // recup de l'ID de la pin

				for (int k = 0; k < arduinos.size(); k++) { // pour chaque arduino
					List<Pin> pins = arduinos.get(k).getMyPins(); // liste des pins par arduinos

					for (Pin pin : pins) { // pour chaque pin
						if (pin.getId() == maPinID) {

							inOutValues.put(pin.getNumInOut(), monDispositif.getEtat()); // ajout du numero de pin et de la valeur d'état du dispositif à la liste

						}

					}

				}
			}
		}*/

	}

	public void exportToDispositifs() {
		
		Arduino ard = arduinoService.findByName(nameArduino);
		List<Pin> pins = ard.getMyPins(); // liste des pins par arduinos
		for (Pin pin : pins) { // pour chaque pin

			for (Entry<Integer, Double> entry : inOutValues.entrySet()) { // liste des etats et des numeros de pin

				if (pin.getNumInOut() == entry.getKey()) {
					
					Double etat = entry.getValue(); // etat du dispositif
					Dispositif disp = dispService.findByPin(pin);
					disp.setEtat(etat);
					dispService.update(disp);
				}
			}
		}
	}

}
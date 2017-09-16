package fr.smartberry.service.interfaces;

import fr.smartberry.entities.Arduino;
import fr.smartberry.entities.Pin;

public interface IPinService extends IAbstractService<Pin>{

	public Pin findByArduino(Arduino a);

	public Pin findByArduinoAndNum_in_out(Arduino a, int num_in_out);
}

package fr.smartberry.service.interfaces;

import fr.smartberry.entities.Arduino;

public interface IArduinoService extends IAbstractService<Arduino>{

	public Arduino findByName(String name);
}

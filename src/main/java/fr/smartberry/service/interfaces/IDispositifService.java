package fr.smartberry.service.interfaces;

import fr.smartberry.entities.Dispositif;
import fr.smartberry.entities.Pin;


public interface IDispositifService extends IAbstractService<Dispositif>{

	public Dispositif findByPin(Pin pin);
}

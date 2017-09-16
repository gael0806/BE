package fr.smartberry.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name = "dispositif")
public class Dispositif {
	
	//Variables de classe
	
	//Variable d'instances
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "current_state")
	private Double etat = 0d;
	@ManyToOne
	@JoinColumn(name = "zone_id", nullable = false)
	private Zone zone;
	@ManyToOne
	@JoinColumn(name = "type_id", nullable = false)
	private Type type;
	@OneToOne
	@JoinColumn(name = "pin_id", nullable = false)
	private Pin pin;
	@ManyToMany
	@JoinTable(name = "dispositif_scenario", joinColumns = @JoinColumn(name = "dispositif_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "scenario_id", referencedColumnName = "id"))
	private List<Scenario> scenari = new ArrayList<>();

	
	//constructeurs
	public Dispositif() {
	}

	public Dispositif(int id, String name, String description, Type type, Pin pin, Double etat, Zone zone, List<Scenario> scenari) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.pin = pin;
		this.etat = etat;
		this.zone = zone;
		this.scenari = scenari;
	}

	
	//accesseurs
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Pin getPin() {
		return pin;
	}

	public void setPin(Pin pin) {
		this.pin = pin;
	}

	public Double getEtat() {
		return etat;
	}

	public void setEtat(Double etat2) {
		this.etat = etat2;
	}
	
	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public List<Scenario> getScenari() {
		return scenari;
	}

	public void setScenari(List<Scenario> scenari) {
		this.scenari = scenari;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Dispositif [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", etat=");
		builder.append(etat);
		builder.append(", zone=");
		if (zone!= null) {
			builder.append(zone.getId());
		} else {
			builder.append(zone);
		}
		builder.append(", type=");
		if (type!= null) {
			builder.append(type.getId());
		} else {
			builder.append(type);
		}
		builder.append(", pin=");
		if (pin!= null) {
			builder.append(pin.getNumInOut());
		} else {
			builder.append(pin);
		}
		builder.append(", scenari=");
		builder.append(scenari);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((etat == null) ? 0 : etat.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pin == null) ? 0 : pin.hashCode());
		result = prime * result + ((scenari == null) ? 0 : scenari.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((zone == null) ? 0 : zone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dispositif other = (Dispositif) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (etat == null) {
			if (other.etat != null)
				return false;
		} else if (!etat.equals(other.etat))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pin == null) {
			if (other.pin != null)
				return false;
		} else if (!pin.equals(other.pin))
			return false;
		if (scenari == null) {
			if (other.scenari != null)
				return false;
		} else if (!scenari.equals(other.scenari))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (zone == null) {
			if (other.zone != null)
				return false;
		} else if (!zone.equals(other.zone))
			return false;
		return true;
	}

	//Methodes
	public void addScenario(Scenario s) {
		this.scenari.add(s);
	}
	
	public void removecenario(Scenario s) {
		this.scenari.remove(s);
	}

}

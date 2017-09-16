package fr.smartberry.entities;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "cycle")
public class Cycle {

	// Variables de classe

	// Variables d'instances
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "heure", nullable = false)
	private Time heure;
	@Column(name = "duree", nullable = false)
	private int duree = 1;
	@Column(name = "periodicity", nullable = false)
	private int periodicity;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "scenario_id", nullable = false)
	private Scenario scenario;

	// Constructeurs
	public Cycle() {
	}

	public Cycle(int id, Time heure, int duree) {
		this.id = id;
		this.heure = heure;
		this.duree = duree;
	}

	// Accesseurs
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Time getHeure() {
		return heure;
	}

	public void setHeure(Time heure) {
		this.heure = heure;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public int getPeriodicity() {
		return periodicity;
	}

	public void setPeriodicity(int periodicity) {
		this.periodicity = periodicity;
	}

	public Scenario getScenario() {
		return scenario;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cycle [id=");
		builder.append(id);
		builder.append(", heure=");
		builder.append(heure);
		builder.append(", duree=");
		builder.append(duree);
		builder.append(", periodicity=");
		builder.append(periodicity);
		builder.append(", scenario=");
		if (scenario != null) {
			builder.append(scenario.getId());
		} else {
			builder.append(scenario);
		}
		
		builder.append("]");
		return builder.toString();
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + duree;
		result = prime * result + ((heure == null) ? 0 : heure.hashCode());
		result = prime * result + id;
		result = prime * result + periodicity;
		result = prime * result + ((scenario == null) ? 0 : scenario.hashCode());
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
		Cycle other = (Cycle) obj;
		if (duree != other.duree)
			return false;
		if (heure == null) {
			if (other.heure != null)
				return false;
		} else if (!heure.equals(other.heure))
			return false;
		if (id != other.id)
			return false;
		if (periodicity != other.periodicity)
			return false;
		if (scenario == null) {
			if (other.scenario != null)
				return false;
		} else if (!scenario.equals(other.scenario))
			return false;
		return true;
	}

	//Methodes
}

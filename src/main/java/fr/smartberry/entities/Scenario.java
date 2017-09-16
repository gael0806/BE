package fr.smartberry.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity(name = "scenario")
public class Scenario {
	
	
	//Variables de classe
	
	//variables d'instances
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "selected_scenar")
	private boolean selectedScenar = false;
	@OneToMany(mappedBy = "scenario", fetch = FetchType.EAGER)
	private List<Cycle> cycles = new ArrayList<>();
	@ManyToMany(mappedBy = "scenari")
	private List<Dispositif> dispositifs = new ArrayList<>();

	
	//constructeurs
	public Scenario() {
	}

	public Scenario(int id, String name, String description, List<Cycle> cycles,boolean sS) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.cycles = cycles;
		this.selectedScenar = sS;
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

	public List<Cycle> getCycles() {
		return cycles;
	}

	public void setCycles(List<Cycle> cycles) {
		this.cycles = cycles;
	}

	public boolean isSelectedScenar() {
		return selectedScenar;
	}

	public void setSelectedScenar(boolean selectedScenar) {
		this.selectedScenar = selectedScenar;
	}

	public List<Dispositif> getDispositifs() {
		return dispositifs;
	}

	public void setDispositifs(List<Dispositif> dispositifs) {
		this.dispositifs = dispositifs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Scenario [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", selectedScenar=");
		builder.append(selectedScenar);
		builder.append(", cycles=");
		builder.append(cycles);
		builder.append(", dispositifs=");
		builder.append(dispositifs);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cycles == null) ? 0 : cycles.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((dispositifs == null) ? 0 : dispositifs.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (selectedScenar ? 1231 : 1237);
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
		Scenario other = (Scenario) obj;
		if (cycles == null) {
			if (other.cycles != null)
				return false;
		} else if (!cycles.equals(other.cycles))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (dispositifs == null) {
			if (other.dispositifs != null)
				return false;
		} else if (!dispositifs.equals(other.dispositifs))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (selectedScenar != other.selectedScenar)
			return false;
		return true;
	}

	//Methodes
	public void addCycle(Cycle c) {
		this.cycles.add(c);
	}

	public void addDispositif(Dispositif d) {
		this.dispositifs.add(d);
	}

}

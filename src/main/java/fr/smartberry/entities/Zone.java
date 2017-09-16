package fr.smartberry.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.repository.Query;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "zone")
public class Zone {

	// Variable de classe

	// Variable d'instances
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "is_garden")
	private boolean isGarden = false;
	@ManyToOne
	@JoinColumn(name = "house_id",nullable=false)
	@JsonIgnore
	private Maison maison;
	@OneToMany(mappedBy = "zone", fetch = FetchType.LAZY)
	private List<Dispositif> dispositifs = new ArrayList<>();

	// constructeurs
	public Zone() {
	}

	public Zone(int id, String name, String description, Maison maison, List<Dispositif> dispositifs) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.maison = maison;
		this.dispositifs = dispositifs;
	}

	// Accesseurs
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

	public List<Dispositif> getDispositifs() {
		return dispositifs;
	}

	public void setDispositifs(List<Dispositif> dispositifs) {
		this.dispositifs = dispositifs;
	}

	public Maison getMaison() {
		return maison;
	}

	public void setMaison(Maison maison) {
		this.maison = maison;
	}

	
	public boolean isGarden() {
		return isGarden;
	}

	
	public void setGarden(boolean isGarden) {
		this.isGarden = isGarden;
	}
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Zone [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", isGarden=");
		builder.append(isGarden);
		builder.append(", maison=");
		if (maison != null) {
			builder.append(maison.getId());
		} else {
			builder.append(maison);
		}
		builder.append(", dispositifs=");
		builder.append(dispositifs);
		builder.append("]");
		return builder.toString();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((dispositifs == null) ? 0 : dispositifs.hashCode());
		result = prime * result + id;
		result = prime * result + (isGarden ? 1231 : 1237);
		result = prime * result + ((maison == null) ? 0 : maison.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Zone other = (Zone) obj;
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
		if (isGarden != other.isGarden)
			return false;
		if (maison == null) {
			if (other.maison != null)
				return false;
		} else if (!maison.equals(other.maison))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	// Methodes
	public void addDisp(Dispositif d) {
		this.dispositifs.add(d);
	}
}

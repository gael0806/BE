package fr.smartberry.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "type")
public class Type {

	// Variables de classe

	// Variables d'instances
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "logo_activated", nullable = false)
	private String logo_activated = "";
	@Column(name = "logo_disactivated", nullable = false)
	private String logo_disactivated = "";
	@OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Dispositif> dispositifs;

	// Constructeur
	public Type() {
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

	public String getLogo_activated() {
		return logo_activated;
	}

	public void setLogo_activated(String logo_activated) {
		this.logo_activated = logo_activated;
	}

	public String getLogo_disactivated() {
		return logo_disactivated;
	}

	public void setLogo_disactivated(String logo_disactivated) {
		this.logo_disactivated = logo_disactivated;
	}

	public List<Dispositif> getDispositif() {
		return dispositifs;
	}

	public void setDispositif(List<Dispositif> dispositif) {
		this.dispositifs = dispositif;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Type [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", logo_activated=");
		builder.append(logo_activated);
		builder.append(", logo_disactivated=");
		builder.append(logo_disactivated);
		builder.append(", dispositif=");
		builder.append(dispositifs);
		builder.append("]");
		return builder.toString();
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dispositifs == null) ? 0 : dispositifs.hashCode());
		result = prime * result + id;
		result = prime * result + ((logo_activated == null) ? 0 : logo_activated.hashCode());
		result = prime * result + ((logo_disactivated == null) ? 0 : logo_disactivated.hashCode());
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
		Type other = (Type) obj;
		if (dispositifs == null) {
			if (other.dispositifs != null)
				return false;
		} else if (!dispositifs.equals(other.dispositifs))
			return false;
		if (id != other.id)
			return false;
		if (logo_activated == null) {
			if (other.logo_activated != null)
				return false;
		} else if (!logo_activated.equals(other.logo_activated))
			return false;
		if (logo_disactivated == null) {
			if (other.logo_disactivated != null)
				return false;
		} else if (!logo_disactivated.equals(other.logo_disactivated))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
	// Methodes
}

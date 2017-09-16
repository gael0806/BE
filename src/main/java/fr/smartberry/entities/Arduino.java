package fr.smartberry.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "arduino")
public class Arduino {
	
	//Variables de classe-----------------------------------------

	//Variables d'instances---------------------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	@Column(name = "name", nullable = false, length=80)
	private String name;
	@Column(name = "adresse_ip", nullable = false, length=15)
	private String ip;
	@OneToMany(mappedBy = "arduino", fetch = FetchType.EAGER)
	List<Pin> myPins = new ArrayList<>();
	
	
	
	//Constructeurs-----------------------------------------------
	public Arduino() {
	}
	
	public Arduino(int id, String name, String ip) {
		this.id = id;
		this.ip = ip;
		this.name = name;
	}

	//Accesseurs--------------------------------------------------
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public List<Pin> getMyPins() {
		return myPins;
	}

	public void setMyPins(List<Pin> myPins) {
		this.myPins = myPins;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Arduino [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", ip=");
		builder.append(ip);
		builder.append(", myPins=");
		builder.append(myPins);
		builder.append("]");
		return builder.toString();
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((myPins == null) ? 0 : myPins.hashCode());
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
		Arduino other = (Arduino) obj;
		if (id != other.id)
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (myPins == null) {
			if (other.myPins != null)
				return false;
		} else if (!myPins.equals(other.myPins))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	//Methodes----------------------------------------------------
	public void addPin(Pin p) {
		this.myPins.add(p);
	}
}

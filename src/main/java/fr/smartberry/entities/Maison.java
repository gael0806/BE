package fr.smartberry.entities;

import java.io.Serializable;
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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "house")
public class Maison implements Serializable{
	
	//Variables de classe
	private static final long serialVersionUID = 1L;
	
	
	//Variables d'instances
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "description", length = 500)
	private String description;
	@OneToMany(mappedBy = "maison", fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<User> users = new ArrayList<>();
	@OneToMany(mappedBy = "maison", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Zone> zones;

	//constructeurs
	public Maison() {
	}

	public Maison(int id, String name, String description, List<User> users, List<Zone> zones) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.users = users;
		this.zones = zones;
	}

	
	//Accesseurs
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Zone> getZones() {
		return zones;
	}

	public void setZones(List<Zone> zones) {
		this.zones = zones;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Maison [id=");
		builder.append(this.getId());
		builder.append(", name=");
		builder.append(this.getName());
		builder.append(", description=");
		builder.append(this.getDescription());
		builder.append(", users=");
		builder.append(this.getUsers());
		builder.append(", zones=");
		builder.append(this.getZones());
		builder.append("]");
		return builder.toString();
	}
	
	@Override
	public int hashCode() {
		if (this.getId() != null) {
			return (this.getClass().getName() + "-" + this.getId()).hashCode();
		}
		return super.hashCode();
	}

	@Override
	public boolean equals(Object pObj) {
		if (pObj == null) {
			return false;
		}
		if (pObj == this) {
			return true;
		}
		if (pObj instanceof Maison) {
			return ((Maison) pObj).getId() == this.getId() || ((Maison) pObj).getId().equals(this.getId());
		}
		return false;
	}

	
	//Methodes
	public void addUser(User user) {
		this.users.add(user);
	}

	public void addZone(Zone zone) {
		this.zones.add(zone);
	}
}

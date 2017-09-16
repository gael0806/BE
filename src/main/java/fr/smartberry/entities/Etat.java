package fr.smartberry.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "etat")
public class Etat {

	//Variables de classe

	//Variables d'instances
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "date", nullable = false)
	private Date date;
	@Column(name = "state")
	private Double state;
	@ManyToOne
	@JoinColumn(name = "dispositif_id", nullable = false)
	private Dispositif dispositif;
	
	//Constructeur
	public Etat() {
	}

	
	//Accesseurs
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Double getState() {
		return state;
	}
	
	public void setState(Double state) {
		this.state = state;
	}
	
	public Dispositif getDispositif() {
		return dispositif;
	}
	
	public void setDispositif(Dispositif dispositif) {
		this.dispositif = dispositif;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Etat [id=");
		builder.append(id);
		builder.append(", date=");
		builder.append(date);
		builder.append(", state=");
		builder.append(state);
		builder.append(", dispositif=");
		if (dispositif != null) {
			builder.append(dispositif.getId());
		} else {
			builder.append(dispositif);
		}
		
		builder.append("]");
		return builder.toString();
	}


	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((dispositif == null) ? 0 : dispositif.hashCode());
		result = prime * result + id;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		Etat other = (Etat) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (dispositif == null) {
			if (other.dispositif != null)
				return false;
		} else if (!dispositif.equals(other.dispositif))
			return false;
		if (id != other.id)
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}


	//Methodes
}

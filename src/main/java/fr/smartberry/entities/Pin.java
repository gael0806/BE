package fr.smartberry.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "pin")
public class Pin {

	//Variables de classe
	
	//Variables d'instances
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "num_in_out", nullable = false)
	private int numInOut;
	@ManyToOne
	@JoinColumn(name = "arduino_id", nullable = false)
	private Arduino arduino;
	@OneToOne(mappedBy = "pin")
	@JsonIgnore
	private Dispositif dispositif;

	// Constructeurs
	public Pin() {
	}

	// accesseurs
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumInOut() {
		return numInOut;
	}

	public void setNumInOut(int numInOut) {
		this.numInOut = numInOut;
	}

	public Arduino getArduino() {
		return arduino;
	}

	public void setArduino(Arduino arduino) {
		this.arduino = arduino;
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
		builder.append("Pin [id=");
		builder.append(id);
		builder.append(", numInOut=");
		builder.append(numInOut);
		builder.append(", arduino=");
		if (arduino != null) {
			builder.append(arduino.getId());
		} else {
			builder.append(arduino);
		}
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
		result = prime * result + ((arduino == null) ? 0 : arduino.hashCode());
		result = prime * result + ((dispositif == null) ? 0 : dispositif.hashCode());
		result = prime * result + id;
		result = prime * result + numInOut;
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
		Pin other = (Pin) obj;
		if (arduino == null) {
			if (other.arduino != null)
				return false;
		} else if (!arduino.equals(other.arduino))
			return false;
		if (dispositif == null) {
			if (other.dispositif != null)
				return false;
		} else if (!dispositif.equals(other.dispositif))
			return false;
		if (id != other.id)
			return false;
		if (numInOut != other.numInOut)
			return false;
		return true;
	}



	// methodes
}

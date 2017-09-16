package fr.smartberry.dao.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.smartberry.entities.Zone;


public interface IZoneDAO extends JpaRepository<Zone, Integer> {

	public Zone findById(Integer id);
}
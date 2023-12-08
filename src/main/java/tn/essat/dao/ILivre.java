package tn.essat.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.essat.model.Livre;

@Repository
public interface ILivre extends JpaRepository<Livre, Integer>{
	List<Livre> findByTitre(String s);
	
	@Query("select l from Livre l where l.cat.id=:x")
	public List<Livre> getAllLivresByCatID(@Param("x") int id);
	
	
	}

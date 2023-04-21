package com.crimerecord.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crimerecord.model.Criminals;

@Repository
public interface CriminalsRepository extends JpaRepository<Criminals, Integer> {
	
	//public Criminals findByName(String firstName);
	List<Criminals> findByState(String state);
	
	@Query(value = "SELECT * FROM criminals c WHERE c.state = state", nativeQuery = true)
	public Page<Criminals> search(String keyword, Pageable pageable);
//	
//	@Query(value = "SELECT * FROM criminals c WHERE c.state = state", nativeQuery = true)
//	public List<Criminals> search(String keyword);
	
}
